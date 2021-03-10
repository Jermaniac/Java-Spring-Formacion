package cursojava.spring.mvc.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosError<T> {
	
	private int codigoErrorNegocio;
	private String mensajeErrorNegocio;
	private T datos;
	
	public DatosError() {
	}

	public DatosError(int codigoErrorNegocio, String mensajeErrorNegocio, T datos) {
		super();
		this.codigoErrorNegocio = codigoErrorNegocio;
		this.mensajeErrorNegocio = mensajeErrorNegocio;
		this.datos = datos;
	}

	public int getCodigoErrorNegocio() {
		return codigoErrorNegocio;
	}

	public void setCodigoErrorNegocio(int codigoErrorNegocio) {
		this.codigoErrorNegocio = codigoErrorNegocio;
	}

	public String getMensajeErrorNegocio() {
		return mensajeErrorNegocio;
	}

	public void setMensajeErrorNegocio(String mensajeErrorNegocio) {
		this.mensajeErrorNegocio = mensajeErrorNegocio;
	}

	public T getDatos() {
		return datos;
	}

	public void setDatos(T datos) {
		this.datos = datos;
	}
	

}
