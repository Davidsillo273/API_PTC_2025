//package DevSGMA_PTC.SGMA_PTC.Config.Cors;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Arrays;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        // Configuración esencial para el FrontEnd
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost"); // Para desarrollo
//        config.addAllowedOrigin("https://localhost"); // para la movil
//        config.addAllowedOrigin("https://sistemaweb-sgma.vercel.app"); // Para producción
//
//        // Métodos permitidos
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("PATCH");
//
//        // Cabeceras permitidas
//        config.addAllowedHeader("Origin");
//        config.addAllowedHeader("Content-Type");
//        config.addAllowedHeader("Accept");
//        config.addAllowedHeader("Authorization");
//        config.addAllowedHeader("X-Requested-With");
//        config.addAllowedHeader("Access-Control-Request-Method");
//        config.addAllowedHeader("Access-Control-Request-Headers");
//        config.addAllowedHeader("Cookie");
//        config.addAllowedHeader("Set-Cookie");
//
//        config.setExposedHeaders(Arrays.asList(
//                "Set-Cookie", "Cookie", "Authorization", "Content-Disposition"
//        ));
//
//        config.setMaxAge(3600L); // 1 hora
//
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    // También crea el CorsConfigurationSource para SecurityConfig
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedOrigin("http://localhost");
//        configuration.addAllowedOrigin("https://localhost");
//        configuration.addAllowedOrigin("https://*.herokuapp.com");
//        configuration.addAllowedOrigin("https://sistemaweb-sgma.vercel.app");
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//
//        configuration.addExposedHeader("Set-Cookie");
//        configuration.addExposedHeader("Cookie");
//        configuration.addExposedHeader("Authorization");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}

package DevSGMA_PTC.SGMA_PTC.Config.Cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    // ✅ ELIMINA UNO DE LOS BEANS - ELIGE ESTE (más completo)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // ✅ ORÍGENES PERMITIDOS COMPLETOS
        configuration.setAllowedOriginPatterns(List.of(
                "http://localhost",           // Todos los puertos de localhost HTTP
                "https://localhost:",          // Todos los puertos de localhost HTTPS
                "http://127.0.0.1:*",          // Localhost por IP
                "https://sistemaweb-sgma.vercel.app",
                "https://sisweb-sgma.netlify.app/",
                "https://*.vercel.app",
                "https://sgma-66ec41075156.herokuapp.com", // ← ¡IMPORTANTE! Tu backend
                "https://*.herokuapp.com"
        ));

        // ✅ MÉTODOS PERMITIDOS
        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));

        // ✅ CABECERAS PERMITIDAS
        configuration.setAllowedHeaders(Arrays.asList(
                "Origin", "Content-Type", "Accept", "Authorization",
                "X-Requested-With", "Access-Control-Request-Method",
                "Access-Control-Request-Headers", "Cookie", "Set-Cookie"
        ));

        // ✅ CABECERAS EXPUESTAS (CRÍTICO para cookies)
        configuration.setExposedHeaders(Arrays.asList(
                "Set-Cookie", "Authorization", "Content-Disposition"
        ));

        // ✅ CONFIGURACIÓN CRÍTICA
        configuration.setAllowCredentials(true);  // Permite cookies
        configuration.setMaxAge(3600L);           // 1 hora

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // ✅ ELIMINA EL BEAN CorsFilter O MANTÉN SOLO UNO
    // @Bean
    // public CorsFilter corsFilter() {
    //     return new CorsFilter(corsConfigurationSource());
    // }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        // Configuración esencial
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost");
//        config.addAllowedOrigin("https://localhost");
//        config.addAllowedOrigin("https://sistemaweb-sgma.vercel.app/");
//        config.addAllowedOrigin("https://sgma-66ec41075156.herokuapp.com");
//
//        // Métodos HTTP permitidos
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("PATCH");
//
//        // Cabeceras permitidas
//        config.addAllowedHeader("Origin");
//        config.addAllowedHeader("Content-Type");
//        config.addAllowedHeader("Accept");
//        config.addAllowedHeader("Authorization");
//        config.addAllowedHeader("X-Requested-With");
//        config.addAllowedHeader("Access-Control-Request-Method");
//        config.addAllowedHeader("Access-Control-Request-Headers");
//        config.addAllowedHeader("Cookie");
//        config.addAllowedHeader("Set-Cookie");
//
//        config.setExposedHeaders(Arrays.asList(
//                "Set-Cookie", "Cookie", "Authorization", "Content-Disposition"
//        ));
//
//        // Tiempo de cache para preflight requests
//        config.setMaxAge(3600L);
//
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    // También crea el CorsConfigurationSource para SecurityConfig
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedOrigin("http://localhost:3000");
//        configuration.addAllowedOrigin("http://localhost:8080");
//        configuration.addAllowedOrigin("http://localhost:4200");
//        configuration.addAllowedOrigin("https://localhost");
//        configuration.addAllowedOrigin("http://localhost");
//        configuration.addAllowedOrigin("https://sistemaweb-sgma.vercel.app/");
//        configuration.addAllowedOrigin("https://sgma-66ec41075156.herokuapp.com");
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//
//        configuration.addExposedHeader("Set-Cookie");
//        configuration.addExposedHeader("Cookie");
//        configuration.addExposedHeader("Authorization");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
