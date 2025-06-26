package DevSGMA_PTC.SGMA_PTC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgmaPtcApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreifMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(SgmaPtcApplication.class, args);
	}

}
w