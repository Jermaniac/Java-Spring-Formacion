package cursojava.spring.datos.modelobanco.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CuentaPK implements Serializable {

	private String banco;

	private String sucursal;

	private String numero;

	public CuentaPK() {
	}

	public CuentaPK(String banco, String sucursal, String numero) {
		super();
		this.banco = banco;
		this.sucursal = sucursal;
		this.numero = numero;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		
		return String.format("%s-%s-%s", banco, sucursal, numero);
		//return String.format("CuentaPK [banco=%s, sucursal=%s, numero=%s]", banco, sucursal, numero);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaPK other = (CuentaPK) obj;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		return true;
	}

}
