package cursojava.spring.datos.modelobanco.servicios;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.datos.modelobanco.dao.NegocioException;

@Transactional(
		rollbackFor = {NegocioException.class}, //Sin nada no hay rollback
		timeout = 60, // segundos
		propagation = Propagation.REQUIRED // Por defecto
		)
public interface OperacionesBancarias {

	void crearClientes(int numeroDeClientes) throws NegocioException;

	void crearCuentas(int numeroBancos, int numeroSucursales, int numeroCuentas) throws NegocioException;

	void asignarCuentasAClientes(int numeroMaximoDeCuentas, int numeroDeBancos, int numeroSucursales,
			int numeroCuentasBancarias) throws NegocioException;

	void cargarDatosEnModeloBanco(int numeroDeClientes, int numeroBancos, int numeroSucursales, int numeroCuentas,
			int numeroMaximoDeCuentas) throws NegocioException;

}