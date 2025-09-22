package DevSGMA_PTC.SGMA_PTC.Config.Security;
//
//import DevSGMA_PTC.SGMA_PTC.Utils.JWT.JwtCookieAuthFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final JwtCookieAuthFilter jwtCookieAuthFilter;
//    private final CorsConfigurationSource corsConfigurationSource; // Inyecta CorsConfigurationSource
//
//    public SecurityConfig(JwtCookieAuthFilter jwtCookieAuthFilter, CorsConfigurationSource corsConfigurationSource) {
//        this.jwtCookieAuthFilter = jwtCookieAuthFilter;
//        this.corsConfigurationSource = corsConfigurationSource;
//    }
//
//
//    // Configuración de seguridad HTTP
//    //Endpoints públicos configurados en el filtro JwtCookieAuthFilter
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        //Aqui van todos los endPoints públicos que no requieren de un JWT
////        http
////                .csrf(csrf -> csrf.disable())  // Nuevo estilo lambda
////                .authorizeHttpRequests(auth -> auth  // Cambia authorizeRequests por authorizeHttpRequests
////                        .requestMatchers(HttpMethod.POST,
////                                "/api/auth/login",
////                                "/api/auth/logout",
////                                "/api/studentsAuth/studentLogin",
////                                "/api/instructorAuth/instructorLogin") // ← agrega esta línea
////                        .permitAll()
////                        .requestMatchers("/api/auth/me").authenticated()
//////                        .requestMatchers("/api/test/admin-only").hasRole("Administrador")
//////                        .requestMatchers("/api/test/cliente-only").hasRole("Cliente")
////                        .anyRequest().authenticated())
////                .sessionManagement(sess -> sess
////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .addFilterBefore(jwtCookieAuthFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
//
////Todos los endpoints permitidos (solo para desarrollo, en producción se deben proteger los endpoints)
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                    .anyRequest().permitAll() // Permite todas las rutas
//            )
//            .sessionManagement(sess -> sess
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtCookieAuthFilter, UsernamePasswordAuthenticationFilter.class);
//    return http.build();
//}
//
//    // Exponer el AuthenticationManager como bean
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}

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
                        .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/logout").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/grades/getAllGrades").permitAll()
                        .requestMatchers("/api/auth/me").authenticated()


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
