package cursojava.practicaspring.german.datos.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "COMPRAS")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Compra implements Serializable{
	
	@Id
	private String codigo;
	
	@Column(name = "FECHA")
	private Date fecha;
	
	private BigDecimal total;
	
	@Column(name = "FECHAALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CLIENTE", referencedColumnName = "NIF")
	})
	private Cliente cliente;
	
//	@OneToMany(mappedBy = "compra")
//	private Set<Detalle> detalles;

	public Compra() {
		
	}
	public Compra(String codigo, Date fecha, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.cliente = cliente;
	}
	
	public Compra(String codigo, Date fecha, BigDecimal total, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
		this.cliente = cliente;
	}
	public Compra(String codigo, Date fecha, BigDecimal total, Date fechaAlta, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
		this.fechaAlta = fechaAlta;
		this.cliente = cliente;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return String.format("Compra [codigo=%s, fecha=%s, total=%s, fechaAlta=%s, cliente=%s]", codigo, fecha, total,
				fechaAlta, cliente);
	}
	
}
