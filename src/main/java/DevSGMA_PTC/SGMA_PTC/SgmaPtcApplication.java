package DevSGMA_PTC.SGMA_PTC;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgmaPtcApplication {

    public static void main(String[] args) {

        loadEnvVariables();

        SpringApplication.run(SgmaPtcApplication.class, args);
    }

    static void loadEnvVariables() {
        // Carga las variables de entorno desde un archivo .env usando la librería Dot
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        // Establece cada variable de entorno como una propiedad del sistema
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );


    }

}