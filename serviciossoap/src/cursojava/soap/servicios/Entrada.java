package cursojava.soap.servicios;

import java.math.BigDecimal;

public class Entrada {
	
	private String id;
	private int cantidad;
	private String refArticulo;
	private BigDecimal precio;
	
	public Entrada() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getRefArticulo() {
		return refArticulo;
	}

	public void setRefArticulo(String refArticulo) {
		this.refArticulo = refArticulo;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
}
