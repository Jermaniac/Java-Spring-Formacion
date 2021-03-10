package cursojava.spring.ioc.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
//@Lazy(true)
public class GestionBancoBean implements GestionBanco {
	
	public void cargarCodigos() {
		System.out.println("Cargando codigos...");
	}
	
	public void archivarOperaciones() {
		
		System.out.println("Archivando operaciones...");
	}
	
	public void transferencia (String origen, String destino, String cantidad) {
		System.out.println(String.format("%s -> %s con esta cantidad:%s",	origen, destino, cantidad));
	}

}
