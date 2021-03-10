package cursojava.spring.boot;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import cursojava.spring.boot.config.ConfiguracionAccesoDatos;
import cursojava.spring.boot.config.ConfiguracionConversores;
import cursojava.spring.boot.config.ConfiguracionMashallerSoap;
import cursojava.spring.boot.config.ConfiguracionSOAP;

// ESTA ANOTACION @SpringBootApplication ES LA SUMA DE ESTAS TRES:
// Con esta anotacion cualificamos esta clase como una clase de configuracion = @Configuration
// Tambien incorpora la semantica del component scan, indicamos a spring donde buscar = @ComponentScan
// Incorporar funcionalidad de starters = @EnableAutoConfiguration

//NOTA: existen dos clases que permiten interceptar cuando esta arrancada la app, vamos a implementar los dos, aunque sirven por separado
@SpringBootApplication
@Import({ConfiguracionAccesoDatos.class,ConfiguracionConversores.class,ConfiguracionMashallerSoap.class,ConfiguracionSOAP.class})
public class SpringbootApplication implements ApplicationRunner, CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ejecutando metodo run con parametros:");
		System.out.println(Arrays.toString(args));
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Ejecutando metodo run con opciones");
		System.out.println("Opciones en bruto:"+ Arrays.toString(args.getSourceArgs()));
		System.out.println("Opciones:"+ args.getOptionNames());
		System.out.println("No opciones:"+ args.getNonOptionArgs());
	}

}
