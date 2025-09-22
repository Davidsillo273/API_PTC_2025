package DevSGMA_PTC.SGMA_PTC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgmaPtcApplication {

    public static void main(String[] args) {

        loadEnvVariables();
        SpringApplication.run(SgmaPtcApplication.class, args);
    }

    static void loadEnvVariables() {
        // Verificar si estamos en Heroku (PORT es una variable que siempre existe en Heroku)
        boolean isHeroku = System.getenv("PORT") != null;

        if (isHeroku) {
            System.out.println("Ejecutando en Heroku - usando variables de entorno del sistema");
            String port = System.getenv("PORT");
            if (port == null) {
                port = "8081";
            }
            System.setProperty("server.port", port);
        }

        // Asegurar que el puerto de Heroku tenga prioridad
        String herokuPort = System.getenv("PORT");
        if (herokuPort != null) {
            System.setProperty("server.port", herokuPort);
        }
    }
}