package cursojava.junit4;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;

public class Modelo5Junit4Test {
	
	@Rule
	public Timeout tiempoMaximo = Timeout.seconds(2);
//	public Timeout tiempoMaximo = Timeout.builder().withTimeout(2,TimeUnit.MINUTES).build();
	
	@Rule
	public ExpectedException excepcionEsperada = ExpectedException.none();
	
	@Rule
	public TemporaryFolder carpetaTemporal = new TemporaryFolder();
	
	@Test
	public void probarCarpetaTemporal(){
		System.out.println("--- probarCarpetaTemporal ---");
		
		try {
			File tmpFile = carpetaTemporal.newFile();
			File volcadoFile = carpetaTemporal.newFile("volcado.txt");
			System.out.println(String.format("Creado: %s", tmpFile.getAbsolutePath()));
			System.out.println(String.format("Creado: %s", volcadoFile.getAbsolutePath()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void unaPrueba(){
		System.out.println("--- unaPrueba ---");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void otraPrueba(){
		System.out.println("--- otraPrueba ---");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void nuevaPrueba(){
		System.out.println("--- nuevaPrueba ---");
		
		excepcionEsperada.expect(IllegalStateException.class);
		excepcionEsperada.expectMessage("Tarjeta no valida");
	}
	
	@Test
	public void otraPruebaMas() throws Exception{
		System.out.println("--- otraPruebaMas ---");
		
		
		//excepcionEsperada.expect(HttpConnectTimeoutException.class);
		excepcionEsperada.expect(HttpConnectTimeoutException.class);
		excepcionEsperada.expectMessage(startsWith("Timeout"));
		excepcionEsperada.expect(hasProperty("timeout",is(10000L)));
		// Codigo que influye en error
		throw new TiempoDeEsperoSuperadoException("Timeout ...",10000);

	}
}
