package cursojava.spring.datos;

public class MensajeTraza {
	
	private Integer codigo;
	private String mensaje;
	
	public MensajeTraza() {
	}
	public MensajeTraza(Integer codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
