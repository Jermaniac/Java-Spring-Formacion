package cursojava.spring.ioc.beans;

public class Domicilio {
	
	private String nombre;
	private String localidad;
	private String provincia;
	
	public Domicilio() {
	}
	
	public Domicilio(String nombre, String localidad, String provincia) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.provincia = provincia;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return String.format("Domicilio [nombre=%s, localidad=%s, provincia=%s]", nombre, localidad, provincia);
	}
	
}
