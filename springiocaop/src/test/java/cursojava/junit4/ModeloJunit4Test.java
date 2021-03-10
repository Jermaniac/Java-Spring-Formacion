package cursojava.junit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModeloJunit4Test {
	
	@BeforeClass
	public static void cargarComponentes() {
		
		System.out.println("### Antes de TODO ###");
		
	}
	
	@AfterClass
	public static void registarDatos() {
		
		System.out.println("### Despues de TODO ###");
		
	}
	
	// Esto se ejecuta antes de cada test
	@Before
	public void crearModeloDatos() {
		
		System.out.println("### Antes de cada prueba ###");

	}
	
	//Esto se ejecuta despues de cada test
	@After
	public void borrarModeloDatos() {
		
		System.out.println("### Despues de cada prueba ###");

	}
	
	@Test
	public void probarNombreDeCliente() {
		
		System.out.println("--- probarNombreDeCliente ---");
		
	}
	
	@Test
	public void comprobarConectividad() {
		
		System.out.println("--- comprobarConectividad ---");
		
	}

}
