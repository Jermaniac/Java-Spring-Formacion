package cursojava.spring.boot.rest;

import java.util.Date;

import cursojava.spring.datos.modelobanco.entidades.CuentaPK;

public class CuentaFechas {
	
	private CuentaPK pk;
	
	private String desde;
	private String hasta;
	public CuentaPK getPk() {
		return pk;
	}
	public void setPk(CuentaPK pk) {
		this.pk = pk;
	}
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}
	
	

}
