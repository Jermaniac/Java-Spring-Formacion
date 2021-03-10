package cursojava.spring.mvc.modelo;

public class Alumno {

	private String nif;
	private String nombre;
	private String apellido;
	private String correo;

	public Alumno() {
	}

	public Alumno(String nif, String nombre, String apellido, String correo) {
		this.nif = nif;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;

	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
