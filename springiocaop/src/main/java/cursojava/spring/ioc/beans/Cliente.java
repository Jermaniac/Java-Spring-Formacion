package cursojava.spring.ioc.beans;

public class Cliente {

	private String nif;
	private String nombre;
	private String apellidos;
	private Domicilio domicilio;
	private CuentaBancaria cuenta;

	public Cliente() {
		System.out.println("Creando cliente.....");
	}

	public Cliente(String nif, String nombre, String apellidos, Domicilio domicilio) {
		this();
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
	}

	public CuentaBancaria getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {
		return String.format("Cliente [nif=%s, nombre=%s, apellidos=%s, domicilio=%s, cuenta=%s]", nif, nombre,
				apellidos, domicilio, cuenta);
	}

}
