package cursojava.practicaspring.german.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cursojava.practicaspring.german.datos.entidades.Articulo;
import cursojava.practicaspring.german.datos.entidades.Articulos;
import cursojava.practicaspring.german.datos.entidades.Cliente;
import cursojava.practicaspring.german.datos.entidades.Compra;
import cursojava.practicaspring.german.datos.entidades.ComprasD;
import cursojava.practicaspring.german.datos.entidades.Detalle;
import cursojava.practicaspring.german.service.ServicioTiendaBean;

@RestController
@RequestMapping("/german/tienda")
public class ControladorTienda {

	@Autowired
	private ServicioTiendaBean servicioTienda;

	@RequestMapping(method = RequestMethod.GET,
			path = "/buscar/cliente/{nif}/{clave}",
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }
	)
	public ResponseEntity<?> buscarCliente(@PathVariable("nif") String nif, @PathVariable("clave") String clave) {

		ResponseEntity<?> retorno = null;
		

		Cliente cliente = servicioTienda.clientePorUsuarioYClave(nif, clave);
		retorno = ResponseEntity.ok(cliente);

		return retorno;

	}
	
	@RequestMapping(method = RequestMethod.GET,
			path = "/buscar/compras/{nif}",
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }
	)
	public ResponseEntity<?> buscarCompras(@PathVariable("nif") String nif) {

		ResponseEntity<?> retorno = null;
		
		List<Compra> compras = servicioTienda.comprasPorCliente(nif);
		retorno = ResponseEntity.ok(compras);

		return retorno;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			path = "/buscar/articulos/{desde}/{hasta}",
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }
	)
	public ResponseEntity<?> buscarArticulosPorPrecioUnidad(@PathVariable("desde") BigDecimal desde,
			@PathVariable("hasta") BigDecimal hasta) {

		ResponseEntity<?> retorno = null;
		
		List<Articulo> articulos = servicioTienda.articulosPorRangoPrecioUnidad(desde, hasta);
		retorno = ResponseEntity.ok(articulos);

		return retorno;
	}
	
	
	@RequestMapping(method = RequestMethod.GET,
			path = "/buscar/detalles/{codigo}",
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }
	)
	public ResponseEntity<?> buscarDetallePorCodigoCompra(@PathVariable("codigo") String codigo) {

		ResponseEntity<?> retorno = null;
		
		List<Detalle> detalles = servicioTienda.detallesPorCompra(codigo);
		retorno = ResponseEntity.ok(detalles);

		return retorno;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			path = "/insertar",
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE }
	)
	public ResponseEntity<?> insertarArticulos(@RequestBody Articulos articulos) {

		ResponseEntity<?> retorno = null;
		
		servicioTienda.insertarArticulos(articulos.getArticulos());
		retorno = ResponseEntity.ok(null);

		return retorno;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			path = "/crearCompra",
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE }
	)
	public ResponseEntity<?> realizarCompra(@RequestBody ComprasD compras) throws ParseException {

		ResponseEntity<?> retorno = null;
		servicioTienda.realizarCompra(compras);
		retorno = ResponseEntity.ok(null);

		return retorno;
	}
	
	

}
