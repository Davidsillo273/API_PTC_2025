package DevSGMA_PTC.SGMA_PTC.Config.Cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Configuración esencial para el FrontEnd
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost"); // Para desarrollo
        config.addAllowedOrigin("https://localhost"); // para la movil
        config.addAllowedOrigin("https://movil-sgma.vercel.app/"); // Para producción

        // Métodos permitidos
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("PATCH");

        // Cabeceras permitidas
        config.addAllowedHeader("Origin");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Accept");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("Access-Control-Request-Method");
        config.addAllowedHeader("Access-Control-Request-Headers");
        config.addAllowedHeader("Cookie");
        config.addAllowedHeader("Set-Cookie");

        config.setExposedHeaders(Arrays.asList(
                "Set-Cookie", "Cookie", "Authorization", "Content-Disposition"
        ));

        config.setMaxAge(3600L); // 1 hora

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // También crea el CorsConfigurationSource para SecurityConfig
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedOrigin("http://localhost");
        configuration.addAllowedOrigin("https://movil-sgma.vercel.app/");
        configuration.addAllowedOrigin("https://*.herokuapp.com");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        configuration.addExposedHeader("Set-Cookie");
        configuration.addExposedHeader("Cookie");
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
