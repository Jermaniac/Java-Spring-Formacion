package cursojava.spring.datos.modelobanco.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cursojava.spring.datos.validaciones.Nif;

@Entity
@Table(name = "CLIENTES")
@NamedQueries({
	@NamedQuery(name = "cliente.borrar", query = "delete from Cliente cte where cte.nif = :elNif"),
	@NamedQuery(name = "cliente.todos", query = "select cte from Cliente cte")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente implements Serializable {

	// Estos valores se podrian llamar diferente de la base de datos real, para ello
	// podemos correguirlo con @column
	@Id
//	@NotNull
//	@NotEmpty(message = "Nif obligatorio")
	//@NotBlank
	@Nif
	private String nif;

	@Pattern(regexp = "^[a-zA-Z0-9 ]{3,10}$", message = "Nombre Incorrecto")
	private String nombre;

	private String apellidos;

	private String domicilio;
	@Column(name = "CP")
	private Integer codigoPostal;

	private String provincia;

	@Column(name = "CIUDAD")
	private String localidad;

	// Indicamos el nombre e indicamos que solo queremos fecha no hora
	@Column(name = "FECHA_NACIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	// Este campo no se debe enviar a la BD, por ello insertable false
	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
	//Aqui mapeamos la relacion Cliente con Cuenta
	// Tambien impedimos que este atributo se mapee a xml
	@XmlTransient
	@JsonIgnore
	@ManyToMany(mappedBy = "clientes")
	private Set<Cuenta> cuentas;

	public Cliente() {
	}

	public Cliente(String nif, String nombre, String apellidos) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Cliente(String nif, String nombre, String apellidos, String domicilio, Integer codigoPostal,
			String provincia, String localidad, Date fechaNacimiento) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.localidad = localidad;
		this.fechaNacimiento = fechaNacimiento;
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
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

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	@Override
	public String toString() {
		return String.format(
				"Cliente [nif=%s, nombre=%s, apellidos=%s, domicilio=%s, codigoPostal=%s, provincia=%s, localidad=%s, fechaNacimiento=%s, fechaAlta=%s]",
				nif, nombre, apellidos, domicilio, codigoPostal, provincia, localidad, fechaNacimiento, fechaAlta);
	}

}
