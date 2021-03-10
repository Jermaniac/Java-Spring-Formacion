package cursojava.spring.ioc.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// implementando InitializingBean indicamos que nos invoque el método afterPropertiesSet al inicio
public class GestionClientesAsincronaBean implements GestionClientes, InitializingBean, DisposableBean {

	public void alta(Cliente cliente) {

		System.out.println("Dando de alta de forma asincrona");

	}

	// Con este metodo no hace falta init-method en el archivo xml config y se llama al inicio
	public void afterPropertiesSet() throws Exception {

		System.out.println("Inicilizando " + this.getClass().getName());

	}
	// Con este método de DisposableBean, este metodo se llama al final
	public void destroy() throws Exception {
		
		System.out.println("Destruyendo " + this.getClass().getName());
		
	}

}
