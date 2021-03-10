package cursojava.spring.boot.servicios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.datos.modelobanco.entidades.CuentaPK;
import cursojava.spring.datos.modelobanco.entidades.Movimiento;

public interface ServiciosBanco {

	// Aplicamos transactional para que sea
	long transferencia(CuentaPK origen, CuentaPK destino, BigDecimal cantidad) throws ServiciosBancoException;

	long efectuarPago(CuentaPK cuenta, BigDecimal cantidad, String descripcion) throws ServiciosBancoException;

	List<Movimiento> movimientosPorCuentaYRangoFechas(CuentaPK cuenta, Date desde, Date hasta)
			throws ServiciosBancoException;

}