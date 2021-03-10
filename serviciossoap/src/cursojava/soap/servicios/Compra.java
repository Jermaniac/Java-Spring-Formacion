package cursojava.soap.servicios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Compra {
	
	private DatosCliente cliente;
	private BigDecimal importe;
	private Date fecha;
	private String codigo;
	private List<Entrada> entradas;
	
	public Compra() {
	}

	public DatosCliente getCliente() {
		return cliente;
	}

	public void setCliente(DatosCliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

}
