package cursojava.spring.ioc.ejemplos;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cursojava.spring.ioc.beans.GestionBanco;
import cursojava.spring.ioc.config.ConfiguracionAOP;

public class EmpleoDeAOP {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfiguracionAOP.class);
		
		GestionBanco gestionBanco = ctx.getBean(GestionBanco.class);
		gestionBanco.transferencia("ORIGENAOP", "DESTINOAOP", "1000");
	}

}
