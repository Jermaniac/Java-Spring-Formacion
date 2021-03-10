package cursojava.soap.servicios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

@WebService
public class ServicioDePrueba {
	
	private static Map<String,Compra> tablaCompras = new HashMap<String, Compra>();
	
	public String echo(String mensaje) {
		return mensaje;
	}
	
	public void altaCompra(Compra datosCompra) {
		
		tablaCompras.put(datosCompra.getCodigo(), datosCompra);
		
	}
	
	public Compra buscarPorCodigo(String codigo) {
		return tablaCompras.get(codigo);
	}
	
	public Collection<Compra> lasCompras(){
		return tablaCompras.values();
	}
}
