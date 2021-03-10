package cursojava.spring.ioc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import cursojava.spring.ioc.beans.GestionBanco;
import cursojava.spring.ioc.beans.GestionBancoBean;
import cursojava.spring.ioc.beans.ServicioTrazas;

//Quito el componentScan porque ya sale en Configuracion1 y desde Configuracion1 ya lo estoy importando
@Configuration
public class Configuracion2 {

	// Para el init method se elige un metodo del BEAN
	// Estamos añadiendo como parametro una clase inyectable, pero no esta en el
	// Bean GestionBancoBean porque no lo utiliza
	@Bean(initMethod = "cargarCodigos", destroyMethod = "archivarOperaciones")
	@Lazy(true)
	public GestionBanco gestionBanco(ServicioTrazas servicioTrazas) {
		servicioTrazas.trazar("Creando GestionBancoBean");
		return new GestionBancoBean();
	}

}
