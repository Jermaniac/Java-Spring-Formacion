package cursojava.spring.datos.modelobanco.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MOVIMIENTOS")
public class Movimiento implements Serializable {

	// Para secuencias tipo ORACLE, indicamos que es una secuencia, indicamos el
	// nombre de la secuencia en la BD y le ponemos un nombre
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="laSecuencia")
	//@SequenceGenerator(sequenceName = "SEQ_MOVIMIENTOS", name = "laSecuencia")
	
	// Como no puede ser nulo podemos usar sin problemas el tipo primitivo int
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name= "FECHA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumns({
		// name -> clave foranea en tabla movimientos
		// referencedColumnName -> clave primaria en tabla cuentas
		@JoinColumn(name = "BANCO", referencedColumnName = "BANCO"),
		@JoinColumn(name = "SUCURSAL", referencedColumnName = "SUCURSAL"),
		@JoinColumn(name = "NUMERO", referencedColumnName = "NUMERO")
		})
	private Cuenta cuenta;
	
	public Movimiento() {
	}

	public Movimiento(Cuenta cuenta, String descripcion) {
		super();
		this.cuenta = cuenta;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return String.format("Movimiento [id=%s, fechaAlta=%s, descripcion=%s, cuenta=%s]", id, fechaAlta, descripcion,
				cuenta);
	}
	
}
