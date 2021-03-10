package cursojava.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConfiguracionMashallerSoap {
	
	@Bean
	public Jaxb2Marshaller mashaller() {
		Jaxb2Marshaller mashall = new Jaxb2Marshaller();
		
		mashall.setContextPath("cursojava.spring.boot.servicios.soap");
		return mashall;
	}

	
}
