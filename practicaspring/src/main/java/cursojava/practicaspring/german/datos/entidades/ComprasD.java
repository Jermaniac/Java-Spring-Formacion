package cursojava.practicaspring.german.datos.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ComprasD {
	
	private String codigoUnico;
	private Date fecha;
	private String cliente;
	private BigDecimal total;
	private List<Articulo> articulos;
	
	public ComprasD() {
		
	}
	public ComprasD(String codigoUnico, Date fecha, String cliente, BigDecimal total, List<Articulo> articulos) {
		super();
		this.codigoUnico = codigoUnico;
		this.fecha = fecha;
		this.cliente = cliente;
		this.total = total;
		this.articulos = articulos;
	}
	public String getCodigoUnico() {
		return codigoUnico;
	}
	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<Articulo> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	
	

}
