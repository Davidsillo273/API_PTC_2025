package DevSGMA_PTC.SGMA_PTC.Config;

import DevSGMA_PTC.SGMA_PTC.Utils.JWT.JWTUtils;
import DevSGMA_PTC.SGMA_PTC.Utils.JWT.JwtCookieAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public JWTUtils jwtUtils() {
        return new JWTUtils();
    }

    @Bean
    public JwtCookieAuthFilter jwtCookieAuthFilter(JWTUtils jwtUtils){

        return new JwtCookieAuthFilter(jwtUtils);
    }
}
