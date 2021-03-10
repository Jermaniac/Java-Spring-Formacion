package cursojava.practicaspring.german.datos.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class DetallePK implements Serializable{
	
	private String compra;
	
	private String articulo;
	
	public DetallePK() {
	}

	public DetallePK(String compra, String articulo) {
		super();
		this.compra = compra;
		this.articulo = articulo;
	}

	public String getCompra() {
		return compra;
	}

	public void setCompra(String compra) {
		this.compra = compra;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

}
