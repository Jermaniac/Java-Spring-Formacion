package cursojava.spring.ioc.ejemplos;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cursojava.spring.ioc.beans.CatalogoDeTiposSoportados;
import cursojava.spring.ioc.beans.Cliente;
import cursojava.spring.ioc.beans.GestionClientes;
import cursojava.spring.ioc.beans.GestionClientesBean;

public class EmpleoBasico {

	public static void main(String[] args) {
		// Este es un proyecto java estandar.
		// Empleamos minima definicion de una fabrica de beans.
//		BeanFactory beanfactory = new ClassPathXmlApplicationContext("basico.xml");

		// Empleamos ClassPathXmlApplicationContext para poder acceder a metodo registerShutdownHook.
		ClassPathXmlApplicationContext beanfactory = new ClassPathXmlApplicationContext("basico.xml");
		// Para permitir a la fabrica de bean detecte el fin del procesos Java.
		// Con esto puedo por fin ver la ejecucion de metodos destroy de los bean
		beanfactory.registerShutdownHook();
//		probarConfiguracion(beanfactory);

//		probarScopeyLazy(beanfactory);
		
		
//		probarCicloVida(beanfactory);	
//		probarHerencia(beanfactory);
		
		GestionClientes gestorCliente = beanfactory.getBean("gestorClientes", GestionClientes.class);
		Cliente client = beanfactory.getBean("prueba1",Cliente.class);
		gestorCliente.alta(client);
		
	}

	private static void probarHerencia(ClassPathXmlApplicationContext beanfactory) {
		// No podemos pedir un componente abstracto
		try {
			Cliente cliente1 = beanfactory.getBean("clienteBase",Cliente.class);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cliente cliente2 = beanfactory.getBean("prueba1",Cliente.class);
		System.out.println(cliente2);
		Cliente cliente3 = beanfactory.getBean("prueba2",Cliente.class);
		System.out.println(cliente3);
	}

	private static void probarCicloVida(ClassPathXmlApplicationContext beanfactory) {
		Cliente cliente1 = beanfactory.getBean("juan",Cliente.class);
		
		GestionClientes gestion = beanfactory.getBean("gTestClientes",GestionClientes.class);
		gestion.alta(cliente1);
		
		
		GestionClientes gestion2 = beanfactory.getBean("gestorClientesAsincrono",GestionClientes.class);
		gestion2.alta(cliente1);
	}

	private static void probarScopeyLazy(BeanFactory beanfactory) {
		// Es importante saber que por defecto BeanFactory crea los componentes POR
		// ADELANTADO.
		// Con lazy-init en el componente en config xml se puede cambiar bajo demanda

		// AMBITOS DE CREACION
		// Por defecto es singleton, por lo tanto devuelve la misma instancia siempre
		// que se crea uan variable.

		Cliente cliente1 = beanfactory.getBean("juan", Cliente.class);
		Cliente cliente2 = beanfactory.getBean("juan", Cliente.class);

		if (cliente1 == cliente2) {
			System.out.println("JUAN - Son la misma instancia.");
		} else {
			System.out.println("JUAN - Son diferentes instancias.");
		}
		// Para evitar esto se puede usar scope="prototype" en el fichero config.
		// De esta forma obtenemos diferentes instancias.
		Cliente cliente3 = beanfactory.getBean("luis", Cliente.class);
		Cliente cliente4 = beanfactory.getBean("luis", Cliente.class);

		if (cliente3 == cliente4) {
			System.out.println("LUIS - Son la misma instancia.");
		} else {
			System.out.println("LUIS - Son diferentes instancias.");
		}
	}

	private static void probarConfiguracion(BeanFactory beanfactory) {
		Cliente juan = (Cliente) beanfactory.getBean("juan");
		System.out.println(juan);
		Cliente luis = beanfactory.getBean("luis", Cliente.class);
		System.out.println(luis);
		Cliente ana = beanfactory.getBean("ana", Cliente.class);
		System.out.println(ana);

		// Puedes declarar el bean de esta forma si solo existe un solo elemento de la
		// clase.
		CatalogoDeTiposSoportados catalogo = beanfactory.getBean(CatalogoDeTiposSoportados.class);
		System.out.println(catalogo);
	}

}
