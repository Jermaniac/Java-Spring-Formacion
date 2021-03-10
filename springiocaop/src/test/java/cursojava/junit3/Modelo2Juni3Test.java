package cursojava.junit3;


import cursojava.spring.ioc.beans.Cliente;
import junit.framework.TestCase;

// En MAVEN las clases dedicadas a pruebas tienen una convencion de nombre:
// *Test.java
// Test*.java
// *TestCase.java

public class Modelo2Juni3Test extends TestCase {
	
	private Cliente cte;
	
	public Modelo2Juni3Test() {
		super();
	}
	public Modelo2Juni3Test(String name) {
		super(name);
	}
	// Esto se ejecuta antes de cada test
	@Override
	protected void setUp() throws Exception {
		
		cte = new Cliente("12345678A", "German", "Gonzalez", null);
		super.setUp();
		
	}
	// Esto se ejecuta despues de cada test
	@Override
	protected void tearDown() throws Exception {
		System.out.println("----Ejecutando despues de cada test.");
		super.tearDown();
	}
	
	public void testProbarClientes () {
		
		System.out.println("Probando testProbarClientes....");
		assertEquals("El nif es incorrecto", cte.getNif(), "12345678A");
		
	}
	
	public void testProbarNombreCliente () {
		
		System.out.println("Probando NombreClientes....");
		cte.setNombre("8999$#");
		
		if (cte.getNombre().equals("8999$#")) {
			fail("El nombre admite caracteres no validos");
		}
		
		//Esto no es de Junit 3
//		assertNotEquals("El nombre admite caracteres no validos", cte.getNombre(), "12345678A");
		
	}

}
