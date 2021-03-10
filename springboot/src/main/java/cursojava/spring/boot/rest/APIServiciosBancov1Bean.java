package cursojava.spring.boot.rest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cursojava.spring.boot.servicios.ServiciosBanco;
import cursojava.spring.boot.servicios.ServiciosBancoException;
import cursojava.spring.datos.MensajeTraza;
import cursojava.spring.datos.modelobanco.entidades.Cuenta;
import cursojava.spring.datos.modelobanco.entidades.CuentaPK;
import cursojava.spring.datos.modelobanco.entidades.Movimiento;

@RestController
@RequestMapping("/banco/v1.0")
public class APIServiciosBancov1Bean {
	
	@Autowired
	private ServiciosBanco servicio;
	
	// En este metodo paso los datos por el json (PK de origen y destino y el importe)
	@RequestMapping(
			method = RequestMethod.POST,
			consumes={ MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE },
			path="/transferir"
			)
	public ResponseEntity<?> transferencia(
			@Valid @RequestBody Cuentas ctas
			) throws ServiciosBancoException {
		ResponseEntity<?> retorno = null;
		try {
			
			servicio.transferencia(ctas.getPk1(), ctas.getPk2(), ctas.getSaldo());
			retorno = ResponseEntity.ok(new MensajeTraza(
						201, String.format("Se ha realizado la transferencia de %s euros.",ctas.getSaldo().toString()) )
						);
		} catch (Exception e) {
			e.printStackTrace();
			retorno = ResponseEntity.status(500).body(
					new MensajeTraza(500, "No fue posible realizar la transferencia."));
			}
		return retorno;

		
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes={ MediaType.APPLICATION_JSON_VALUE,
						MediaType.APPLICATION_XML_VALUE },
			path="/pagar/{cantidad}"
			)
	public ResponseEntity<?> pagar(
			@Valid @RequestBody Cuenta cta,
			@PathVariable("cantidad") Integer cantidad
			) {
		ResponseEntity<?> retorno = null;
		try {
			BigDecimal bg = new BigDecimal(cantidad);
			servicio.efectuarPago(cta.getPk(), bg,"Pago efectuado por cantidad: "+cantidad.toString());
			retorno = ResponseEntity.ok(
					new MensajeTraza(200, "Se ha realizado el pago."));
		} catch (Exception e) {
			e.printStackTrace();
			retorno = ResponseEntity.status(500).body(
					new MensajeTraza(500, "No fue posible realizar el pago."));
		}
		return retorno;
		
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes={ MediaType.APPLICATION_JSON_VALUE,
						MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE },
			path="/obtenerMovimientos"
			)
	public ResponseEntity<?> mostrar(
			@Valid @RequestBody CuentaFechas ctaF
			) {
		List<Movimiento> movimientos = List.of();
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ctaF.getDesde());
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ctaF.getHasta());
			movimientos =  servicio.movimientosPorCuentaYRangoFechas(ctaF.getPk(), date1, date2);
			return ResponseEntity.ok(movimientos);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(
					new MensajeTraza(500, "No fue posible realizar esta consulta."));
		}
		
	}
	
	

}
