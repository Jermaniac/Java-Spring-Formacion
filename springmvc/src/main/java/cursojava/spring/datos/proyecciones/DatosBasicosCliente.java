package cursojava.spring.datos.proyecciones;

public interface DatosBasicosCliente {
	
	// Subconjunto de propiedades que queremos
	String getNif();
	String getNombre();
	String getApellidos();
	
	// Podemos aniadir propiedades calculadas
	default String getFichaCliente() {
		return String.format("%10s%20s%40s", getNif(), getNombre(), getApellidos());
	}

}
