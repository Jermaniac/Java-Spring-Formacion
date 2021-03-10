package cursojava.practicaspring.german.datos.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DETALLECOMPRAS")
public class Detalle implements Serializable{

	@EmbeddedId
	private DetallePK pk;
	
	private BigDecimal cantidad;
	
	private BigDecimal precioUnidad;
//	
//	@ManyToOne
//	@JoinColumns({
//		@JoinColumn(name = "COMPRA", referencedColumnName = "CODIGO")
//	})
//	private Compra compra;
//	
//	@ManyToOne
//	@JoinColumns({
//		@JoinColumn(name = "ARTICULO", referencedColumnName = "CODIGO")
//	})
//	private Articulo articulo;
//	
	public Detalle() {
	}
	
	public Detalle(DetallePK pk, BigDecimal precioUnidad) {
		super();
		this.pk = pk;
		this.precioUnidad = precioUnidad;
	}

	public Detalle(DetallePK pk, BigDecimal cantidad, BigDecimal precioUnidad) {
		super();
		this.pk = pk;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
	}

	public DetallePK getPk() {
		return pk;
	}

	public void setPk(DetallePK pk) {
		this.pk = pk;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(BigDecimal precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	@Override
	public String toString() {
		return String.format("Detalle [pk=%s, cantidad=%s, precioUnidad=%s]", pk, cantidad, precioUnidad);
	}
	
}
