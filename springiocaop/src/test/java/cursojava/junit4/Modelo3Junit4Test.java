package cursojava.junit4;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Modelo3Junit4Test {
	
	@Test
	public void unaPrueba() {
		System.out.println("--- Una prueba ---");
	}
	
	@Test
	public void otraPrueba() {
		System.out.println("--- Otra prueba ---");
	}
	
	@Test
	public void nuevaPrueba() {
		System.out.println("--- Nueva prueba ---");
	}

}
