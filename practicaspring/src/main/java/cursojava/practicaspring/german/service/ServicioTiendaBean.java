package cursojava.practicaspring.german.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cursojava.practicaspring.german.datos.entidades.Articulo;
import cursojava.practicaspring.german.datos.entidades.Cliente;
import cursojava.practicaspring.german.datos.entidades.Compra;
import cursojava.practicaspring.german.datos.entidades.ComprasD;
import cursojava.practicaspring.german.datos.entidades.Detalle;
import cursojava.practicaspring.german.datos.entidades.DetallePK;
import cursojava.practicaspring.german.repositorios.RepositorioArticulos;
import cursojava.practicaspring.german.repositorios.RepositorioClientes;
import cursojava.practicaspring.german.repositorios.RepositorioCompras;
import cursojava.practicaspring.german.repositorios.RepositorioDetalles;

@Service
public class ServicioTiendaBean {
	
	@Autowired
	RepositorioClientes repoClientes;
	
	@Autowired
	RepositorioCompras repoCompras;
	
	@Autowired
	RepositorioArticulos repoArticulo;
	
	@Autowired
	RepositorioDetalles repoDetalles;
	
	public Cliente clientePorUsuarioYClave(String usuario, String clave) {
		return repoClientes.buscarPorUsuarioClave(usuario, clave);
	}
	public List<Compra> comprasPorCliente(String nif){
		return repoCompras.buscarComprasPorCliente(nif);
	}
	public List<Articulo> articulosPorRangoPrecioUnidad(BigDecimal desde, BigDecimal hasta){
		return repoArticulo.buscarArticuloPorRangoPrecioUnidad(desde, hasta);
	}
	
	public List<Detalle> detallesPorCompra(String codigo){
		return repoDetalles.buscarDetallePorCompra(codigo);
	}
	public void insertarArticulos(List<Articulo> articulos) {
		repoArticulo.saveAll(articulos);
	}
	public void realizarCompra(ComprasD comprasD) throws ParseException {
		Compra compra = new Compra(
				comprasD.getCodigoUnico(),
				comprasD.getFecha(),
				comprasD.getTotal(),
				repoClientes.findById(comprasD.getCliente()).get());
		repoCompras.save(compra);
		List<Detalle> detalles = new ArrayList<Detalle>();
		for (int i =0; i<comprasD.getArticulos().size();i++) {
			DetallePK detallePK = new DetallePK(comprasD.getCodigoUnico(), comprasD.getArticulos().get(i).getCodigo());
			BigDecimal bg = comprasD.getArticulos().get(i).getPrecioUnidad();
			detalles.add(new Detalle(detallePK,bg));
		}
		repoDetalles.saveAll(detalles);
	}

}
