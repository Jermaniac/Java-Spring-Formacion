package cursojava.practicaspring.german;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import cursojava.practicaspring.german.config.ConfiguracionAccesoDatos;



@SpringBootApplication
@Import({ConfiguracionAccesoDatos.class})
public class PracticaspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaspringApplication.class, args);
	}

}
