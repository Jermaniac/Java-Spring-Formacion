package cursojava.junit4;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class Modelo4Junit4Test {
	
	@Test
	public void pruebaUno(){
		System.out.println("--- Prueba uno ---");
	}
	
	@Ignore("Pendiente de revision cumplir con norma blabla")
	@Test
	public void pruebaDos(){
		System.out.println("--- Prueba dos ---");
	}
	
	@Test(timeout = 5000)
	public void pruebaTres(){
		try {
			Thread.sleep(20000);
			System.out.println("--- Prueba tres ---");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	// Si se produce una excepcion QUE NO CAPTURAMOS, el metodo falla
	@Test
	public void pruebaCuatro(){
		
		System.out.println("--- Prueba cuatro ---");
		
		throw new IllegalStateException("Tarjeta con numero incorrecto");

	}
	
	// Capturamos la excepcion para que no falle el metodo test
	@Test
	public void pruebaCinco(){
		
		try {
			System.out.println("--- Prueba cinco ---");
			
			throw new IllegalStateException("Tarjeta con numero incorrecto");
		} catch (IllegalStateException e) {
			assertTrue("Tarjeta con numero incorrecto", true);
		}

	}
	
	@Test(expected = IllegalStateException.class)
	public void pruebaSeis(){
		
		System.out.println("--- Prueba cuatro ---");
		
		throw new IllegalStateException("Tarjeta con numero incorrecto");

	}
	

}
