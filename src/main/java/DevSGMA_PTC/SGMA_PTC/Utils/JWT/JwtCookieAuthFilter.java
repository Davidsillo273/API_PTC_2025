package DevSGMA_PTC.SGMA_PTC.Utils.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
public class JwtCookieAuthFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtCookieAuthFilter.class);
    private static final String AUTH_COOKIE_NAME = "authToken";
    private final JWTUtils jwtUtils;

    @Autowired
    public JwtCookieAuthFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // CORREGIDO: Mejor lógica para endpoints públicos
        if (isPublicEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = extractTokenFromCookies(request);

            if (token == null || token.isBlank()) {

                // Para endpoints no públicos, requerimos token
                if (!isPublicEndpoint(request)) {
                    sendError(response, "Token no encontrado", HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtUtils.parseToken(token);

            // EXTRAER EL ROL REAL del token
            String role = jwtUtils.extractRole(token);

            // EXTRAER EL LEVEL REAL del token
            String level = jwtUtils.extractLevel(token);

            // CORREGIDO - Crear authorities correctamente
            Collection<? extends GrantedAuthority> authorities =
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

            // CREAR AUTENTICACIÓN CON AUTHORITIES CORRECTOS
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            claims.getSubject(), // username
                            null, // credentials
                            authorities // ← ROLES REALES
                    );

            // ESTABLECER AUTENTICACIÓN EN CONTEXTO
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            log.warn("Token expirado: {}", e.getMessage());
            sendError(response, "Token expirado", HttpServletResponse.SC_UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            log.warn("Token malformado: {}", e.getMessage());
            sendError(response, "Token inválido", HttpServletResponse.SC_FORBIDDEN);
        } catch (Exception e) {
            log.error("Error de autenticación", e);
            sendError(response, "Error de autenticación", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String extractTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;

        return Arrays.stream(cookies)
                .filter(c -> AUTH_COOKIE_NAME.equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

    private void sendError(HttpServletResponse response, String message, int status) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status);
        response.getWriter().write(String.format(
                "{\"error\": \"%s\", \"status\": %d}", message, status));
    }

    //  TODOS los endpoints públicos de SecurityConfig
    private boolean isPublicEndpoint(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

// Endpoints públicos
        return
                // AUTH - STUDENTS
                (path.equals("/api/studentsAuth/studentLogin") && "POST".equals(method)) ||
                        (path.equals("/api/studentsAuth/logoutStudent") && "POST".equals(method)) ||
                        (path.equals("/api/studentsAuth/meStudent") && "GET".equals(method)) ||

                        // AUTH - INSTRUCTORS
                        (path.equals("/api/instructorsAuth/instructorLogin") && "POST".equals(method)) ||
                        (path.equals("/api/instructorsAuth/logoutInstructor") && "POST".equals(method)) ||
                        (path.equals("/api/studentsAuth/meInstructor") && "GET".equals(method)) ||

                        // ENTRIES
                        (path.equals("/api/entries/newEntry") && "POST".equals(method)) ||
                        (path.equals("/api/entries/getAllEntries") && "GET".equals(method)) ||

                        // GRADES
                        (path.equals("/api/grades/getAllGrades") && "GET".equals(method)) ||

                        // INSTRUCTORS
                        (path.equals("/api/instructors/getAllInstructors") && "GET".equals(method)) ||
                        (path.equals("/api/instructors/newInstructor") && "POST".equals(method)) ||
                        (path.startsWith("/api/instructors/updateInstructor/") && "PUT".equals(method)) ||
                        (path.startsWith("/api/instructor/deleteInstructor/") && "DELETE".equals(method)) ||
                        (path.startsWith("/api/instructor/getInstructorById/") && "GET".equals(method)) ||
                        (path.startsWith("/api/instructor/update/") && path.endsWith("/password") && "PUT".equals(method)) ||

                        // LEVELS
                        (path.equals("/api/levels/getAllLevels") && "GET".equals(method)) ||

                        // MODULES
                        (path.equals("/api/modules/getAllModules") && "GET".equals(method)) ||
                        (path.equals("/api/modules/newModules") && "POST".equals(method)) ||
                        (path.startsWith("/api/modules/updateModules/") && "PUT".equals(method)) ||
                        (path.startsWith("/api/modules/deleteModules/") && "DELETE".equals(method)) ||

                        // ROLES
                        (path.equals("/api/roles/getAllRoles") && "GET".equals(method)) ||

                        // STUDENTS
                        (path.equals("/api/students/getAllStudents") && "GET".equals(method)) ||
                        (path.equals("/api/students/newStudent") && "POST".equals(method)) ||
                        (path.startsWith("/api/students/updateStudent/") && "PUT".equals(method)) ||
                        (path.startsWith("/api/students/deleteStudent/") && "DELETE".equals(method)) ||
                        (path.startsWith("/api/students/getStudentById/") && "GET".equals(method)) ||
                        (path.startsWith("/api/students/update/") && path.endsWith("/password") && "PUT".equals(method)) ||

                        // VEHICLES
                        (path.equals("/api/vehicles/newVehicle") && "POST".equals(method)) ||
                        (path.equals("/api/vehicles/getAllVehicles") && "GET".equals(method)) ||
                        (path.startsWith("/api/vehicles/getVehicleByPlateNumber/") && "GET".equals(method)) ||
                        (path.startsWith("/api/vehicles/getVehicleByCirculationCardNumber/") && "GET".equals(method)) ||
                        (path.startsWith("/api/vehicles/getVehicleByOwnerPhone/") && "GET".equals(method)) ||
                        (path.startsWith("/api/vehicles/updateStatusVehicle/") && "PUT".equals(method)) ||

                        // VEHICLE TYPES
                        (path.equals("/api/vehicleTypes/getAllVehicleTypes") && "GET".equals(method)) ||

                        // WORKORDERS
                        (path.equals("/api/workOrders/getAllWorkOrders") && "GET".equals(method)) ||
                        (path.equals("/api/workOrders/newWorkOrder") && "POST".equals(method)) ||
                        (path.startsWith("/api/workOrders/updateWorkOrder/") && "PUT".equals(method)) ||
                        (path.startsWith("/api/workOrders/deleteWorkOrder/") && "DELETE".equals(method)) ||
                        (path.startsWith("/api/workOrders/getWorkOrdersByStudentIdAndStatus2/") && "GET".equals(method)) ||
                        (path.startsWith("/api/workOrders/getWorkOrdersByInstructorIdAndStatus3/") && "GET".equals(method));

    }
}