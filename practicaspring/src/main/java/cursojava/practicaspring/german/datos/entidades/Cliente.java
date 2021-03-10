package cursojava.practicaspring.german.datos.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "CLIENTES")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente implements Serializable{
	
	@Id
	private String nif;
	
	private String nombre;

	private String apellidos;

	private String domicilio;
	
	@Column(name = "CP")
	private Integer codigoPostal;

	private String localidad;
	
	private String provincia;

	private String email;
	
	private String usuario;
	
	private String clave;
	
	@Column(name = "FECHANACIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@Column(name = "FECHAALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
	@OneToMany(mappedBy = "cliente")
	private List<Compra> compras;
	
	public Cliente() {
	}

	public Cliente(String nif, String nombre, String apellidos, String email, String usuario, String clave) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario = usuario;
		this.clave = clave;
	}

	public Cliente(String nif, String nombre, String apellidos, String domicilio, Integer codigoPostal,
			String localidad, String provincia, String email, String usuario, String clave, Date fechaNacimiento,
			Date fechaAlta) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.provincia = provincia;
		this.email = email;
		this.usuario = usuario;
		this.clave = clave;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaAlta = fechaAlta;
	}


	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	

	
}
