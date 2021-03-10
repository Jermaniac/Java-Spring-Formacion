package cursojava.spring.boot.servicios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.datos.modelobanco.entidades.Cuenta;
import cursojava.spring.datos.modelobanco.entidades.CuentaPK;
import cursojava.spring.datos.modelobanco.entidades.Movimiento;
import cursojava.spring.datos.repositorio.RepositorioCuentas;
import cursojava.spring.datos.repositorio.RepositorioMovimientos;

@Service
public class ServiciosBancoBean implements ServiciosBanco {
	
	// PARA TRABAJAR CON BBDD PODEMOS UTILIZAR ENTITYMANAGER O LOS REPOSITORIOS JPA.
	// @PersistenceContext (unitName = "modelobanco")  // Aqui si solo hay uno, no es necesario el nombre.
	// EntityManager em;
	// ------------------
	// @Autowired
	// RepositorioCuentas repoCuentas
	@Autowired
	private RepositorioCuentas repoCuentas;
	
	@Autowired
	private RepositorioMovimientos repoMovimientos;

	// Aplicamos transactional para que sea
	@Override
	@Transactional(rollbackFor = ServiciosBancoException.class)
	public long transferencia(CuentaPK origen, CuentaPK destino, BigDecimal cantidad) 
			throws ServiciosBancoException {
		try {
			Optional<Cuenta> opCuentaOut = repoCuentas.findById(origen);
			Optional<Cuenta> opCuentaIn = repoCuentas.findById(destino);
			if ((opCuentaOut.isPresent()) && (opCuentaIn.isPresent())) {
				Cuenta cuentaOut = opCuentaOut.get();
				Cuenta cuentaIn = opCuentaIn.get();
				if (cuentaOut.getSaldo().compareTo(cantidad)==1) {
					// Actualizamos los saldos de cada cuenta
					cuentaOut.setSaldo(cuentaOut.getSaldo().subtract(cantidad));
					cuentaIn.setSaldo(cuentaIn.getSaldo().add(cantidad));
					// Creamos los movimientos asociados
					Movimiento movimiento1 = new Movimiento(repoCuentas.findById(origen).get(), "Enviada transferencia de "+cantidad.toString());
					repoMovimientos.save(movimiento1);
					Movimiento movimiento2 = new Movimiento(repoCuentas.findById(destino).get(), "Recibida transferencia de "+cantidad.toString());
					repoMovimientos.save(movimiento2);
				}
				else {
					throw new ServiciosBancoException("Cuenta origen no tiene saldo suficiente.");
				}
			}
			else {
				throw new ServiciosBancoException("Cuenta origen o destino no existe.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiciosBancoException("No se puedo hacer transferencia.");
		}
		
		return -1;
	}
	
	@Override
	public long efectuarPago(CuentaPK cuenta, BigDecimal cantidad, String descripcion) 
			throws ServiciosBancoException {
		try {
			Optional<Cuenta> opCuenta = repoCuentas.findById(cuenta);
			
			if (opCuenta.isPresent()) {
				Cuenta cta = opCuenta.get();
				if (cta.getSaldo().compareTo(cantidad)==1) {
				
					cta.setSaldo(cta.getSaldo().subtract(cantidad));
	
					Movimiento movimiento1 = new Movimiento(cta, "Pago de"+cantidad.toString());
					repoMovimientos.save(movimiento1);
				}
				else {
					throw new ServiciosBancoException("Cuenta no tiene saldo suficiente.");
				}
			}
			else {
				throw new ServiciosBancoException("Cuenta no existe.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiciosBancoException("No se pudo hacer pago.");
		}
		return-1;
	}
	
	@Override
	public List<Movimiento> movimientosPorCuentaYRangoFechas(CuentaPK cuenta, Date desde, Date hasta)
			throws ServiciosBancoException {
		try {
			return repoMovimientos.consulta(cuenta,desde,hasta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiciosBancoException("No se pudo listar movimientos por cuenta y rangode fechas.");
		}
	}
	
	public Optional<Cuenta> buscarCuenta(CuentaPK pk) {
		return repoCuentas.findById(pk);
	}
	
	

	public void setRepoCuentas(RepositorioCuentas repoCuentas) {
		this.repoCuentas = repoCuentas;
	}

	public void setRepoMovimientos(RepositorioMovimientos repoMovimientos) {
		this.repoMovimientos = repoMovimientos;
	}

}
