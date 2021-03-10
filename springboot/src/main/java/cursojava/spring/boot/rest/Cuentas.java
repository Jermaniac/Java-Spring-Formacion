package cursojava.spring.boot.rest;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import cursojava.spring.datos.modelobanco.entidades.CuentaPK;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cuentas {
	
	private CuentaPK pk1;
	
	private CuentaPK pk2;
	
	private BigDecimal saldo;

	public CuentaPK getPk1() {
		return pk1;
	}

	public void setPk1(CuentaPK pk1) {
		this.pk1 = pk1;
	}

	public CuentaPK getPk2() {
		return pk2;
	}

	public void setPk2(CuentaPK pk2) {
		this.pk2 = pk2;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	

}
