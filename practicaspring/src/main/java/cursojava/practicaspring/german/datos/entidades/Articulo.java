package cursojava.practicaspring.german.datos.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ARTICULOS")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Articulo {
	
	@Id
	private String codigo;
	
	private String nombre;
	
	private String descripcion;
	
	@Column(name = "PRECIOUNIDAD")
	private BigDecimal precioUnidad;
	
	@Column(name = "PESOGRAMOS")
	private BigDecimal pesoGramos;
	
	private BigDecimal cantidad;
	
	@Column(name = "FECHAALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
//	@OneToMany(mappedBy = "articulo")
//	private Set<Detalle> detalle;

	public Articulo() {
		
	}
	
	public Articulo(String codigo, String nombre, String descripcion, BigDecimal precioUnidad, BigDecimal pesoGramos,
			BigDecimal cantidad, Date fechaAlta) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnidad = precioUnidad;
		this.pesoGramos = pesoGramos;
		this.cantidad = cantidad;
		this.fechaAlta = fechaAlta;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getPrecioUnidad() {
		return precioUnidad;
	}
	public void setPrecioUnidad(BigDecimal precioUnidad) {
		this.precioUnidad = precioUnidad;
	}
	public BigDecimal getPesoGramos() {
		return pesoGramos;
	}
	public void setPesoGramos(BigDecimal pesoGramos) {
		this.pesoGramos = pesoGramos;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return String.format(
				"Articulo [codigo=%s, nombre=%s, descripcion=%s, precioUnidad=%s, pesoGramos=%s, cantidad=%s, fechaAlta=%s]",
				codigo, nombre, descripcion, precioUnidad, pesoGramos, cantidad, fechaAlta);
	}

}
