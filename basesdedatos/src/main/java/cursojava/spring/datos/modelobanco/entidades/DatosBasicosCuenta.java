package cursojava.spring.datos.modelobanco.entidades;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import cursojava.spring.datos.modelobanco.entidades.Cuenta;

@Projection(name = "saldoYFecha",types = {Cuenta.class})
public interface DatosBasicosCuenta {
	
	BigDecimal getSaldo();
	
	Date getFechaAlta();

}
