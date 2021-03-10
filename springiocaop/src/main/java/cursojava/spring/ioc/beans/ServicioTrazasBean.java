package cursojava.spring.ioc.beans;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ServicioTrazasBean implements ServicioTrazas {
	
	public void trazar (String mensaje) {
		
		System.out.println(String.format("%s - %s", new Date(),mensaje));
		
	}

}
