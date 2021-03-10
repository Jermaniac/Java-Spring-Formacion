package cursojava.spring.ioc.ejemplos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import cursojava.spring.ioc.anotaciones.Asinc;
import cursojava.spring.ioc.anotaciones.Sinc;
import cursojava.spring.ioc.beans.Cliente;
import cursojava.spring.ioc.beans.GestionBanco;
import cursojava.spring.ioc.beans.GestionClientes;
import cursojava.spring.ioc.config.Configuracion1;
import cursojava.spring.ioc.config.Configuracion2;

// Esta clase va a utilizar la configuracion de una clase java y no un xml
// La marcamos componente para que tenga dependencias inyectadas y le ponemos otro nombre que no sea el de clase
@Component("probarDependencias")
public class EmpleoConfiguracionClasesJava {

	// De esta forma vamos a probar los diferentes tipos de gestion clientes
	@Autowired
	private GestionClientes gestionClientes;

	@Autowired
	@Qualifier("sincrono")
	private GestionClientes gestionClientes2;

	@Autowired
	@Qualifier("asincrono")
	private GestionClientes gestionClientes3;

	// Con nuestras propias anotaciones
	@Autowired
	@Sinc
	private GestionClientes gestionClientes4;

	@Autowired
	@Asinc
	private GestionClientes gestionClientes5;

	@Autowired
	private GestionBanco gestionBanco;

	@Autowired
	private List<Cliente> listaCliente;

	public static void main(String[] args) {

		// Con esto se consigue que se pinten los metodos de destroy
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Configuracion1.class);
		
		ctx.registerShutdownHook(); // Este metodo solo sale si las clases son iguales al declarar arriba
		
		// Este es el equivalente de Java estandar
		Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Terminado")));

		// Esto deberia funcionar
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(Configuracion1.class, Configuracion2.class);

//		probarScopeYNombre(ctx);

		// Ahora pido el componente por tipo, se puede ver que creamos tres para
		// comprobar que crea dos sincronos y un asincrono

		EmpleoConfiguracionClasesJava prueba = ctx.getBean(EmpleoConfiguracionClasesJava.class);

		prueba.gestionClientes.alta(null);// @Primary

		prueba.gestionClientes2.alta(null);// @Qualifier("sincrono")

		prueba.gestionClientes3.alta(null); // @Qualifier("asincrono")

		prueba.gestionClientes4.alta(null); // @Sinc

		prueba.gestionClientes5.alta(null); // @Asinc

		prueba.gestionBanco.transferencia("ORIGEN", "DESTINO", "120");

		// Si pones una coleccion de un tipo que tienes en la fabrica de componentes, te
		// crea todos los beans
		System.out.println(prueba.listaCliente);
	}

	private static void probarScopeYNombre(ApplicationContext ctx) {
		// IMPORTANTE: el nombre que le ponemos es el nombre del metodo en la clase
		// configuracion Configuracion1.java
		GestionClientes gestionClientes = ctx.getBean("gestionClientesName", GestionClientes.class);
		gestionClientes.alta(null);

		GestionClientes gestionClientes2 = ctx.getBean("gestionClientesName", GestionClientes.class);
		gestionClientes2.alta(null);

		// Usan la misma instancia por defecto, pero esto se puede cambiar en la clase
		// configuracion con @Scope
	}

	public void setGestionClientes(GestionClientes gestionClientes) {
		this.gestionClientes = gestionClientes;
	}

	public void setGestionClientes2(GestionClientes gestionClientes2) {
		this.gestionClientes2 = gestionClientes2;
	}

	public void setGestionClientes3(GestionClientes gestionClientes3) {
		this.gestionClientes3 = gestionClientes3;
	}

	public void setGestionClientes4(GestionClientes gestionClientes4) {
		this.gestionClientes4 = gestionClientes4;
	}

	public void setGestionClientes5(GestionClientes gestionClientes5) {
		this.gestionClientes5 = gestionClientes5;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

}
