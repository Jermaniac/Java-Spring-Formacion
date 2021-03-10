package cursojava.spring.ioc.ejemplos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cursojava.spring.ioc.beans.GestionClientes;

public class EmpleoConAnotaciones {
	
	public static void main(String[] args) {
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("basica3.xml");
		
		GestionClientes servicioClientes = beanFactory.getBean("servicioCliente",GestionClientes.class);
		
		servicioClientes.alta(null);
	}

}