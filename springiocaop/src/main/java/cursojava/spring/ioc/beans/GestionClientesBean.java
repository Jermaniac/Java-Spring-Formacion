package cursojava.spring.ioc.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Por defecto el nombre de la anotacion @Component es el de la clase empezando con minusculas
// Service es un componente que representa la funcionalidad de negocio.
// Esta clase, en concreto, es para negocio, tiene funcionalidad por lo tanto @Service.
@Component("servicioCliente")
@Service
public class GestionClientesBean implements GestionClientes {

	// System.out.print no se debe utilizar de manera normal, lo mas adecuado es
	// utilizar una clase que nos de esa funcionalidad.
//	@Autowired(required=false)
	@Autowired
	private ServicioTrazas servicioTrazas;

	// No hace falta get
	public void setServicioTrazas(ServicioTrazas servicioTrazas) {
		this.servicioTrazas = servicioTrazas;
	}

	// Este metodo es invocado por spring al inicio del ciclo de vida con
	// init-method
	public void inicializar() {
		// System.out.println("Inicilizando " + this.getClass().getName());
		servicioTrazas.trazar("Inicilizando " + this.getClass().getName());
	}

	// Spring lo invoca al final por config xml destroy-method="limpiar"
	public void limpiar() {

//		System.out.println("Limpiando " + this.getClass().getName());
		servicioTrazas.trazar("Limpiando " + this.getClass().getName());

	}

	public void alta(Cliente cliente) {

//		System.out.println("Dando alta a cliente...");
		servicioTrazas.trazar("Dando alta a cliente...");

	}

}
