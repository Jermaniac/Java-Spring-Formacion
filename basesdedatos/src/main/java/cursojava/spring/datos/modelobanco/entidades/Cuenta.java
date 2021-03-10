package cursojava.spring.datos.modelobanco.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CUENTAS")
public class Cuenta implements Serializable {

	// Clave primaria compuesta
	@EmbeddedId
	private CuentaPK pk;

	private String dc;

	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;

	private BigDecimal saldo;

	@Column(name = "LIMITE_DESCUBIERTO")
	private BigDecimal limiteDescubierto;

	@Column(name = "LIMITE_OPERACION")
	private BigDecimal limiteOperacion;

	@JsonIgnore
	@OneToMany(mappedBy = "cuenta")
	private Set<Movimiento> movimientos;

	// Aqui mapeamos la relacion Cuenta con Cliente
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			// TABLA JOIN
			name = "TITULARES",
			// ENLACE CON LA TABLA TITULARES name = TITULARES, referencedColumnName = CUENTA
			// inverseJoinColumns: ENLACE CON TABLA CLIENTES name = TITULARES, referencedColumnName = CLIENTES
			joinColumns = { 
					@JoinColumn(name = "BANCO", referencedColumnName = "BANCO"),
					@JoinColumn(name = "SUCURSAL", referencedColumnName = "SUCURSAL"),
					@JoinColumn(name = "NUMERO", referencedColumnName = "NUMERO") }, 
			inverseJoinColumns = {
					@JoinColumn(name = "NIF", referencedColumnName = "NIF") }
	)
	
	private Set<Cliente> clientes = new HashSet<Cliente>();

	public Cuenta() {
	}

	// Para que sea mas facil de probar ponemos la pk (obligatorio) y saldo
	public Cuenta(CuentaPK pk, BigDecimal saldo) {
		super();
		this.pk = pk;
		this.saldo = saldo;
	}

	public Cuenta(CuentaPK pk, String dc, BigDecimal saldo, BigDecimal limiteDescubierto, BigDecimal limiteOperacion) {
		super();
		this.pk = pk;
		this.dc = dc;
		this.saldo = saldo;
		this.limiteDescubierto = limiteDescubierto;
		this.limiteOperacion = limiteOperacion;
	}

	public CuentaPK getPk() {
		return pk;
	}

	public void setPk(CuentaPK pk) {
		this.pk = pk;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getLimiteDescubierto() {
		return limiteDescubierto;
	}

	public void setLimiteDescubierto(BigDecimal limiteDescubierto) {
		this.limiteDescubierto = limiteDescubierto;
	}

	public BigDecimal getLimiteOperacion() {
		return limiteOperacion;
	}

	public void setLimiteOperacion(BigDecimal limiteOperacion) {
		this.limiteOperacion = limiteOperacion;
	}

	public Set<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	// No meto los movimientos porque los movimientos no forman parte de la cuenta,
	// si son necesarios podemos usar getMovimientos
	@Override
	public String toString() {
		return String.format("Cuenta [pk=%s, dc=%s, fechaAlta=%s, saldo=%s, limiteDescubierto=%s, limiteOperacion=%s]",
				pk, dc, fechaAlta, saldo, limiteDescubierto, limiteOperacion);
	}

}
