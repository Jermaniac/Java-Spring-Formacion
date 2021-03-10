package cursojava.spring.ioc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import cursojava.spring.ioc.anotaciones.Asinc;
import cursojava.spring.ioc.anotaciones.Sinc;
import cursojava.spring.ioc.beans.GestionClientes;
import cursojava.spring.ioc.beans.GestionClientesAsincronaBean;
import cursojava.spring.ioc.beans.GestionClientesBean;
import cursojava.spring.ioc.beans.ServicioTrazas;
import cursojava.spring.ioc.beans.ServicioTrazasBean;

// Esta clase va a sustituir a un fichero xml de configuracion
// Con @ComponentScan escaneamos componentes y no es necesario los metodos @Bean
@Configuration
@Import({Configuracion2.class})
@ImportResource("classpath:/basico.xml")
@ComponentScan(basePackages = {"cursojava.spring.ioc.ejemplos"})
public class Configuracion1 {
	
	// Este metodo fabrica componentes, con el componentScan no es necesario este metodo
	// Con el name asociado a Bean es posible cambiar el nombre por el que se llama
	// Con scope haces que se cree un componente cada vez que sea necesario y que no se use el mismo
	// Con Primary permites que se diferencien las dos clases gestionclientes
	@Bean(name = {"gClientes","servicioClientes","gestionClientesName"})
	@Scope("prototype")
	@Primary 
	@Qualifier("sincrono")
	@Sinc
	public GestionClientes gestionClientes () {
		System.out.println("Creando GestionClientes");
		return new GestionClientesBean();
	}
	
	@Bean
	@Scope("prototype")
	@Qualifier("asincrono")
	@Asinc
//	@Primary
	public GestionClientes gestionClientesAsinc () {
		System.out.println("Creando GestionClientesAsincronaBean");
		return new GestionClientesAsincronaBean();
	}
	
	// Este metodo fabrica el componente que utiliza autowired en la clase GestionClientesBean, que es ServicioTrazas
	@Bean
	public ServicioTrazas servicioTrazas () {
		System.out.println("Creando ServicioTrazas");
		return new ServicioTrazasBean();
	}
	
}
