package cursojava.spring.datos.modelobanco.pruebas;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cursojava.spring.datos.modelobanco.dao.GestionClientes;
import cursojava.spring.datos.modelobanco.entidades.Cliente;
import cursojava.spring.datos.modelobanco.servicios.OperacionesBancarias;

public class ProbarModeloBanco {
	
	public static void main(String[] args) {
				
		try {
			//probarGestionClientes();
			ApplicationContext ctx = new ClassPathXmlApplicationContext("springjpa.xml");
			
			OperacionesBancarias operacionesBancarias = ctx.getBean(OperacionesBancarias.class);
			
			operacionesBancarias.cargarDatosEnModeloBanco(1000, 10, 10, 10, 4);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void probarGestionClientes() {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("springjpa.xml");
			GestionClientes gestionClientes = ctx.getBean(GestionClientes.class);
			Cliente cliente1 = new Cliente("12345678B","German","Gonzalez");
			gestionClientes.alta(cliente1);
			System.out.println(gestionClientes.buscarPorNif(cliente1.getNif()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
