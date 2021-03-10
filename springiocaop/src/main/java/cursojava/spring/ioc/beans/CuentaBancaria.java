package cursojava.spring.ioc.beans;

public class CuentaBancaria {

	private String banco;
	private String sucursal;
	private String dc;
	private String numero;

	public CuentaBancaria() {
	}

	public CuentaBancaria(String banco, String sucursal, String dc, String numero) {
		super();
		this.banco = banco;
		this.sucursal = sucursal;
		this.dc = dc;
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

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return String.format("CuentaBancaria [banco=%s, sucursal=%s, dc=%s, numero=%s]", banco, sucursal, dc, numero);
	}

}
