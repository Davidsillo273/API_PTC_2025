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

        // Variables de los endpoints
        String instructorAuth = "/api/instructorsAuth";
        String studentAuth = "/api/studentsAuth";
        String grades = "/api/grades";
        String students = "/api/students";
        String entries = "/api/entries";
        String vehicles = "/api/vehicles";
        String modules = "/api/modules";
        String vehicleTypes = "/api/vehicleTypes";
        String levels = "/api/levels";
        String workOrders = "/api/workOrders";
        String instructors = "/api/instructors";
        String roles = "/api/roles";

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ← Permite preflight requests

                        // ENDPOINTS SIN AUTENTIFICACIÓN

                        //Student Auth
                        .requestMatchers(HttpMethod.POST, studentAuth + "/studentLogin").permitAll()

                        //Instructor Auth
                        .requestMatchers(HttpMethod.POST, instructorAuth + "/instructorLogin").permitAll()

                        //Grades
                        .requestMatchers(HttpMethod.GET, grades + "/getAllGrades").permitAll()

                        // Students
                        .requestMatchers(HttpMethod.POST, students + "/newStudent").permitAll()

                        // ENDPOINTS CON AUTENTIFICACIÓN

                        // Student Auth
                        .requestMatchers(studentAuth + "/meStudent").authenticated()
                        .requestMatchers(HttpMethod.POST, studentAuth + "/logoutStudent").authenticated()

                        // Instructor Auth
                        .requestMatchers(instructorAuth + "/meInstructor").authenticated()
                        .requestMatchers(HttpMethod.POST, instructorAuth + "/logoutInstructor").authenticated()

                        // Students
                        .requestMatchers(HttpMethod.PUT, students + "/updateStudent/*").authenticated()

                        // Entries
                        .requestMatchers(HttpMethod.POST, entries + "/newEntry").authenticated()
                        .requestMatchers(HttpMethod.GET, entries + "/getAllEntries").authenticated()

                        // Vehicles
                        .requestMatchers(HttpMethod.POST, vehicles + "/newVehicle").authenticated()
                        .requestMatchers(HttpMethod.POST, vehicles + "/getAllVehiclesByStudent/*").authenticated()

                        // Modules
                        .requestMatchers(HttpMethod.GET, modules + "/getAllModules").authenticated()

                        // Vehicle Types
                        .requestMatchers(HttpMethod.GET, vehicleTypes + "/getAllVehicleTypes").authenticated()

                        // Levels
                        .requestMatchers(HttpMethod.GET, levels + "/getAllLevels").authenticated()

                        // Work Orders
                        .requestMatchers(HttpMethod.POST, workOrders + "/newWorkOrder").authenticated()
                        .requestMatchers(HttpMethod.PUT, workOrders + "/updateWorkOrder/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, workOrders + "/deleteWorkOrder/*").authenticated()

                        // AUTH POR ROL ANIMADOR Y COORDINADORA

                        // Modules
                        .requestMatchers(HttpMethod.POST, modules + "/newModules").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.PUT, modules + "/updateModules/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.DELETE, modules + "/deleteModules/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

                        // Instructors
                        .requestMatchers(HttpMethod.GET, instructors + "/getAllInstructors").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.POST, instructors + "/newInstructor").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.PUT, instructors + "/updateInstructor/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.PUT, instructors + "/update/{id}/password").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora","ROLE_Docente")
                        .requestMatchers(HttpMethod.DELETE, instructors + "/deleteInstructor/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, instructors + "/getInstructorById/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

                        // Roles
                        .requestMatchers(HttpMethod.GET, roles + "/getAllRoles").hasAuthority("ROLE_Animador")

                        // Students
                        .requestMatchers(HttpMethod.GET, students + "/getAllStudents").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.DELETE, students + "/deleteStudent/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, students + "/getStudentById/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

                        //Vehicles
                        .requestMatchers(HttpMethod.GET, vehicles + "/getAllVehicles").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, vehicles + "/getVehicleByPlateNumber/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, vehicles + "/getVehicleByCirculationCardNumber/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, vehicles + "/getVehicleByOwnerPhone/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, vehicles + "/getVehiclesByStudentId/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.PATCH, vehicles + "/updateStatusVehicle/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

                        // WorkOrders
                        .requestMatchers(HttpMethod.GET, workOrders + "/getAllWorkOrders").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, workOrders + "/getWorkOrdersByStudentIdAndStatus2/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")
                        .requestMatchers(HttpMethod.GET, workOrders + "/getWorkOrdersByStudentIdAndStatus2/*").hasAnyAuthority("ROLE_Animador", "ROLE_Coordinadora")

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
