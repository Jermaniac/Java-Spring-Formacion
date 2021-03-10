package cursojava.junit3;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ConjuntoDePruebas1TestSinMaven extends TestSuite {

	public ConjuntoDePruebas1TestSinMaven() {

		// Para incluir los metodos test que indiquemos
		addTest(new ModeloJuni3Test("testProbarClientes"));
		addTest(new Modelo2Juni3Test("testProbarClientes"));

		// Para incluir todos los metodos de la clase test que indiquemos
		addTestSuite(ModeloJuni3Test.class);
		addTestSuite(Modelo2Juni3Test.class);
	}

	public static Test suite() {
		return new ConjuntoDePruebas1TestSinMaven();
	}

}
