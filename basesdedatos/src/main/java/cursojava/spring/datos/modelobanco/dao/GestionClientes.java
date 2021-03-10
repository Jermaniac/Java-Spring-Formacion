package cursojava.spring.datos.modelobanco.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.datos.modelobanco.entidades.Cliente;

@Transactional(rollbackFor = NegocioException.class)
public interface GestionClientes {

	void alta(Cliente cte) throws NegocioException;
	
	@Transactional(readOnly = true) //No requiere transaccion
	Cliente buscarPorNif(String nif) throws NegocioException;

	@Transactional(readOnly = true) //No requiere transaccion
	List<Cliente> todosLosClientes() throws NegocioException;

	void borrar(String nif) throws NegocioException;

	void modificar(Cliente cte) throws NegocioException;

}