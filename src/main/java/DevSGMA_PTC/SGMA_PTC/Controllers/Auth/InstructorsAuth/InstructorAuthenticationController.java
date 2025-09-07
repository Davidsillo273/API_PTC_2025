package DevSGMA_PTC.SGMA_PTC.Controllers.Auth.InstructorsAuth;

import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Instructors.InstructorDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Auth.InstructorsAuth.InstructorAuthenticationService;
import DevSGMA_PTC.SGMA_PTC.Utils.JWT.JWTUtils;
import jakarta.servlet.http.Cookie;
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
 * Controlador para la autenticación de instructores.
 * Proporciona endpoints para el inicio de sesión y la obtención de datos del instructor autenticado.
 */
@RestController
@RequestMapping("api/instructorAuth")
public class InstructorAuthenticationController {

    @Autowired
    private InstructorAuthenticationService instructorAuthenticationService; // Servicio de autenticación de instructores

    @Autowired
    private JWTUtils jwtUtils; // Utilidad para manejar tokens JWT

    /**
     * Endpoint para el inicio de sesión de instructores.
     * Valida las credenciales y genera un token JWT si son correctas.
     *
     * @param data DTO con los datos de inicio de sesión del instructor (email y password)
     * @param response HttpServletResponse para agregar la cookie del token
     * @return ResponseEntity con el resultado del inicio de sesión
     */
    @PostMapping("/instructorLogin")
    private ResponseEntity<String> instructorLogin( @RequestBody InstructorDTO data, HttpServletResponse response) {
        System.out.println("Se está intentando iniciar sesión con: " + data.getEmail());

        // Validación de credenciales
        if (data.getEmail() == null ||
                data.getEmail().isBlank() ||
                data.getPassword() == null ||
                data.getPassword().isBlank()) {
            return ResponseEntity.status(401).body("Error: Credenciales incompletas");
        }

        // Verificación de credenciales y generación de token
        if (instructorAuthenticationService.instructorLogin(data.getEmail(), data.getPassword())) {
            addTokenCookie(response, data.getEmail()); // ← Pasar solo el correo
            return ResponseEntity.ok("Inicio de sesión exitoso");
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    /**
     * Genera el token JWT y lo guarda en una cookie segura.
     *
     * @param response HttpServletResponse para agregar la cookie
     * @param email Correo institucional del instructor
     */
    private void addTokenCookie(HttpServletResponse response, String email) {

        // Obtener el instructor completo de la base de datos
        Optional<InstructorEntity> instructorOpt = instructorAuthenticationService.getInstructor(email);

        if (instructorOpt.isPresent()) {
            InstructorEntity instructor = instructorOpt.get();
            String token = jwtUtils.create(
                    String.valueOf(instructor.getInstructorId()),
                    instructor.getEmail(),
                    instructor.getRoleId().getRoleName(),
                    instructor.getLevelId().getLevelName(),
                    null  // ← Instructors no tienen grade, así que pasamos null
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
     * Obtiene los datos del instructor actualmente autenticado.
     *
     * @param authentication Objeto de autenticación proporcionado por Spring Security
     * @return ResponseEntity con los datos del instructor autenticado o mensaje de error
     */
    @GetMapping("/meInstructor")
    public ResponseEntity<?> getCurrentInstructor(Authentication authentication) {
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

            // Busca el instructor por su nombre de usuario (correo)
            Optional<InstructorEntity> instructorOpt = instructorAuthenticationService.getInstructor(username);

            if (username.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "authenticated", false,
                                "message", "Instructor no encontrado"
                        ));
            }

            InstructorEntity instructor = instructorOpt.get();

            // Devuelve los datos del instructor autenticado
            return ResponseEntity.ok(Map.of(
                    "authenticated", true,
                    "instructor", Map.of(
                            "id", instructor.getInstructorId(),
                            "names", instructor.getFirstName(),
                            "lastNames", instructor.getLastName(),
                            "email", instructor.getEmail(),
                            "role", instructor.getRoleId().getRoleName(),
                            "level", instructor.getLevelId().getLevelName(),
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
                            "message", "Error obteniendo datos del instructor"
                    ));
        }
    }
}
