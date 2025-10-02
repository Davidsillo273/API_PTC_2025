package DevSGMA_PTC.SGMA_PTC.Config.Security;

import DevSGMA_PTC.SGMA_PTC.Utils.JWT.JwtCookieAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtCookieAuthFilter jwtCookieAuthFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(JwtCookieAuthFilter jwtCookieAuthFilter, CorsConfigurationSource corsConfigurationSource) {
        this.jwtCookieAuthFilter = jwtCookieAuthFilter;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ← Permite preflight requests

                        // ENDPOINTS SIN AUTENTIFICACIÓN

                        // AUTH - STUDENTS
                        .requestMatchers(HttpMethod.POST, "/api/studentsAuth/studentLogin").permitAll()

                        // AUTH - INSTRUCTORS
                        .requestMatchers(HttpMethod.POST, "/api/instructorsAuth/instructorLogin").permitAll()

                        // GRADES
                        .requestMatchers(HttpMethod.GET, "/api/grades/getAllGrades").permitAll()

                        // ROLES
                        .requestMatchers(HttpMethod.GET, "/api/roles/getAllRoles").permitAll()


                        // ENDPOINTS CON AUTENTIFICACIÓN

                        // AUTH - STUDENTS
                        .requestMatchers("/api/studentsAuth/meStudent").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/studentsAuth/logoutStudent").authenticated()

                        // AUTH - INSTRUCTORS
                        .requestMatchers("/api/instructorsAuth/meInstructor").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/instructorsAuth/logoutInstructor").permitAll()

                        // STUDENTS
                        .requestMatchers(HttpMethod.PUT, "/api/students/updateStudent/*").authenticated()

                        // ENTRIES
                        .requestMatchers(HttpMethod.POST, "/api/entries/newEntry").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/entries/getAllEntries").authenticated()

                        // VEHICLES
                        .requestMatchers(HttpMethod.POST, "/api/vehicles/newVehicle").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/vehicles/getAllVehiclesByStudent/*").authenticated()

                        // MODULES
                        .requestMatchers(HttpMethod.GET, "/api/modules/getAllModules").authenticated()

                        // VEHICLE TYPES
                        .requestMatchers(HttpMethod.GET, "/api/vehicleTypes/getAllVehicleTypes").authenticated()

                        // LEVELS
                        .requestMatchers(HttpMethod.GET, "/api/levels/getAllLevels").authenticated()

                        // WORKORDERS
                        .requestMatchers(HttpMethod.POST, "/api/workOrders/newWorkOrder").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/workOrders/updateWorkOrder/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/workOrders/deleteWorkOrder/*").authenticated()


                        // AUTH POR ROL INSTRUCTOR
                        .requestMatchers(HttpMethod.GET, "/api/students/getAllVehicles")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora", "ROLE_Docente")

                        // AUTH POR ROL ADMIN

                        // MODULES
                        .requestMatchers(HttpMethod.POST, "/api/modules/newModules")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.PUT, "/api/modules/updateModules/*")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.DELETE, "/api/modules/deleteModules/*")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

                        // INSTRUCTORS
                        .requestMatchers(HttpMethod.GET, "/api/instructors/getAllInstructors")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.POST, "/api/instructors/newInstructor")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.PUT, "/api/instructors/updateInstructor/*")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.DELETE, "/api/instructors/deleteInstructor/*")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, "/api/instructors/getInstructorById/*")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

                        // STUDENTS
                        .requestMatchers(HttpMethod.POST, "/api/students/newStudent")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.DELETE, "/api/students/deleteStudent/*")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, "/api/students/getStudentById/*")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

                        // VEHICLES
                        .requestMatchers(HttpMethod.GET, "/api/vehicles/getAllVehicles")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.PATCH, "/api/vehicles/updateStatusVehicle/*")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

                        // WORKORDERS
                        .requestMatchers(HttpMethod.GET, "/api/workOrders/getAllWorkOrders")
                        .hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")


                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtCookieAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
