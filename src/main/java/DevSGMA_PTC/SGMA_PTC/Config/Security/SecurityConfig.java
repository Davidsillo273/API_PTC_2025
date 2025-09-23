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
                .cors(cors -> cors.configurationSource(corsConfigurationSource)) // ← Configura CORS aquí
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ← Permite preflight requests

                        //ENDPOINTS SIN AUTENTIFICACIÓN

                        //AUTH - STUDENTS
                        .requestMatchers(HttpMethod.POST,"/api/studentsAuth/studentLogin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/studentsAuth/logoutStudent").permitAll()

                        //AUTH - INSTRUCTORS
                        .requestMatchers(HttpMethod.POST,"/api/instructorsAuth/instructorLogin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/instructorAuth/logoutInstructor").permitAll()

                        //ENTRIES
                        .requestMatchers(HttpMethod.POST, "/api/entries/newEntry").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/entries/getAllEntries").permitAll()

                        //GRADES
                        .requestMatchers(HttpMethod.GET, "/api/grades/getAllGrades").permitAll()

                        //LEVELS
                        .requestMatchers(HttpMethod.GET, "/api/levels/getAllLevels").permitAll()

                        //MODULES
                        .requestMatchers(HttpMethod.GET, "/api/modules/getAllModules").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/modules/newModules").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/modules/updateModules/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/modules/deleteModules/{id}").permitAll()

                        //ROLES
                        .requestMatchers(HttpMethod.GET, "/api/roles/getAllRoles").permitAll()

                        //STUDENTS
                        .requestMatchers(HttpMethod.GET, "/api/students/getAllStudents").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/students/newStudent").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/students/updateStudent/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/students/deleteStudent/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/students/getStudentById/{id}").permitAll()

                        //VEHICLES
                        .requestMatchers(HttpMethod.POST, "/api/vehicles/newVehicle").permitAll()

                        //VEHICLE TYPES
                        .requestMatchers(HttpMethod.GET, "/api/vehicleTypes/getAllVehicleTypes").permitAll()

                        //WORKORDERS
                        .requestMatchers(HttpMethod.POST, "/api/workOrders/newWorkOrder").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/workOrders/updateWorkOrder/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/workOrders/deleteWorkOrder/{id}").permitAll()

                        //ENDPOINTS CON AUTENTIFICACIÓN

                        //AUTH - STUDENTS
                        .requestMatchers("/api/studentAuth/meStudent").authenticated()

                        //AUTH - INSTRUCTORS
                        .requestMatchers("/api/instructorAuth/meInstructor").authenticated()

                        //INSTRUCTORS
                        .requestMatchers(HttpMethod.GET, "/api/instructors/getAllInstructors").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.POST, "/api/instructors/newInstructor").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.PUT, "/api/instructors/updateInstructor/{id}").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/instructors/deleteInstructor/{id}").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, "/api/instructors/getInstructorById/{id}").hasAuthority("ROLE_admin")

                        //VEHICLES
                        .requestMatchers(HttpMethod.GET, "/api/vehicles/getAllVehicles").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.PATCH, "/api/vehicles/updateStatusVehicle/{id}").hasAuthority("ROLE_admin")

                        //WORKORDERS
                        .requestMatchers(HttpMethod.GET, "/api/workOrders/getAllWorkOrders").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, "/api/workOrders/getWorkOrderById/{id}").hasAuthority("ROLE_admin")

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
