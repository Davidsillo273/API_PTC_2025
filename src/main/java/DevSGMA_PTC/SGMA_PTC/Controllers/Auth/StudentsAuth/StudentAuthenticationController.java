package DevSGMA_PTC.SGMA_PTC.Controllers.Auth.StudentsAuth;

import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Students.StudentDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Auth.StudentsAuth.StudentAuthenticationService;
import DevSGMA_PTC.SGMA_PTC.Utils.JWT.JWTUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador para la autenticación de estudiantes.
 * Proporciona endpoints para el inicio de sesión y la obtención de datos del estudiante autenticado.
 */
@RestController
@RequestMapping("/api/studentsAuth")
public class StudentAuthenticationController {

    /**
     * Servicio para la autenticación de estudiantes.
     */
    @Autowired
    private StudentAuthenticationService studentAuthenticationService;

    /**
     * Utilidad para la generación y validación de tokens JWT.
     */
    @Autowired
    private JWTUtils jwtUtils;

    /**
     * Endpoint para el inicio de sesión de estudiantes.
     * Valida las credenciales y genera un token JWT si son correctas.
     *
     * @param data     DTO con los datos de inicio de sesión del estudiante (email y password)
     * @param response HttpServletResponse para agregar la cookie del token
     * @return ResponseEntity con el resultado del inicio de sesión
     */
    @PostMapping("/studentLogin")
    private ResponseEntity<String> studentLogin(@RequestBody StudentDTO data, HttpServletResponse response) {
        System.out.println("Se está intentando iniciar sesión con: " + data.getEmail());

        // Validación de credenciales
        if (data.getEmail() == null || data.getEmail().isBlank() ||
                data.getPassword() == null || data.getPassword().isBlank()) {
            return ResponseEntity.status(401).body("Error: Credenciales incompletas");
        }

        // Verificación de credenciales y generación de token
        if (studentAuthenticationService.studentLogin(data.getEmail(), data.getPassword())) {
            addTokenCookie(response, data.getEmail()); // ← Pasar solo el correo
            return ResponseEntity.ok("Inicio de sesión exitoso");
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    /**
     * Genera el token JWT y lo guarda en una cookie segura.
     *
     * @param response HttpServletResponse para agregar la cookie
     * @param email    Correo institucional del estudiante
     */
    private void addTokenCookie(HttpServletResponse response, String email) {
        // Obtener el usuario completo de la base de datos
        Optional<StudentEntity> studentOpt = studentAuthenticationService.getStudents(email);

        if (studentOpt.isPresent()) {
            StudentEntity student = studentOpt.get();
            String token = jwtUtils.create(
                    String.valueOf(student.getStudentId()),
                    student.getEmail(),
                    null, // Rol no utilizado para estudiantes
                    null, // Nivel no utilizado para estudiantes
                    String.valueOf(student.getGradeId().getGradeGroup()) // Grupo del estudiante
            );

            Cookie cookie = new Cookie("authToken", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(86400);
            response.addCookie(cookie);
        }
    }

    /**
     * Obtiene los datos del estudiante actualmente autenticado.
     *
     * @param authentication Objeto de autenticación proporcionado por Spring Security
     * @return ResponseEntity con los datos del estudiante autenticado o mensaje de error
     */
    @GetMapping("/meStudent")
    public ResponseEntity<?> getCurrentStudent(Authentication authentication) {
        try {
            // Verifica si el usuario está autenticado
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of(
                                "authenticated", false,
                                "message", "No autenticado"
                        ));
            }

            // Maneja diferentes tipos de principal (UserDetails o nombre de usuario)
            String username;
            Collection<? extends GrantedAuthority> authorities;
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                username = userDetails.getUsername();
                authorities = userDetails.getAuthorities();
            } else {
                username = authentication.getName();
                authorities = authentication.getAuthorities();
            }

            // Busca el estudiante por su nombre de usuario (correo)
            Optional<StudentEntity> studentOpt = studentAuthenticationService.getStudents(username);

            if (studentOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "authenticated", false,
                                "message", "Estudiante no encontrado"
                        ));
            }

            StudentEntity student = studentOpt.get();

            // Devuelve los datos del estudiante autenticado
            return ResponseEntity.ok(Map.of(
                    "authenticated", true,
                    "student", Map.of(
                            "id", student.getStudentId(),
                            "studentCard", student.getStudentCard(),
                            "names", student.getFirstName(),
                            "lastNames", student.getLastName(),
                            "email", student.getEmail(),
                            "group", student.getGradeId().getGradeGroup(),
                            "authorities", authorities.stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList())
                    )
            ));
        } catch (Exception e) {
            // Manejo de errores inesperados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "authenticated", false,
                            "message", "Error obteniendo datos del estudiante"
                    ));
        }
    }

    @PostMapping("/logoutStudent")
    public ResponseEntity<String> logoutStudent(HttpServletRequest request, HttpServletResponse response) {
        // Crear cookie de expiración con SameSite=None
        String cookieValue = "authToken=; Path=/; HttpOnly; Secure; SameSite=None; MaxAge=0; Domain=sgma-66ec41075156.herokuapp.com";

        response.addHeader("Set-Cookie", cookieValue);
        response.addHeader("Access-Control-Expose-Headers", "Set-Cookie");

        // También agregar headers CORS para la respuesta
        String origin = request.getHeader("Origin");
        if (origin != null &&
                (origin.contains("localhost") || origin.contains("herokuapp.com"))) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
        return ResponseEntity.ok()
                .body("Logout exitoso");
    }
}
