package cursojava.spring.datos.modelobanco.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.datos.modelobanco.dao.GestionClientes;
import cursojava.spring.datos.modelobanco.dao.NegocioException;
import cursojava.spring.datos.modelobanco.entidades.Cliente;

@Repository
public class GestionClientesJPABean implements GestionClientes {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void alta(Cliente cte) throws NegocioException{
		try {
			em.persist(cte);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("ERROR al crear cliente.",e);
		}
	}
	
	public void modificar(Cliente cte) throws NegocioException{
		
		try {
			em.merge(cte);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Error al modificar cliente.",e);
		}
		
	}
	
	public void borrar(String nif) throws NegocioException{
		try {
			// FORMA 1: De esta forma tenemos la consulta en la entidad como anotacion @NamedQuery
			em.createNamedQuery("cliente.borrar")
			// FORMA 2: De esta forma tenemos la consulta aqui mismo
				// Para poner parametros pongo :
				// Despues seteamos ese parametro con .setParameter
//			em.createQuery("delete from Cliente cte where cte.nif = :elNif")
			.setParameter("elNif",nif)
			.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Error al borrar cliente.",e);
		}
		
	}
	
	public Cliente buscarPorNif(String nif) throws NegocioException{
		try {
			Cliente cliente = em.find(Cliente.class, nif);
			// Si no encuentra cliente devuelve NULL, no da error
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Error al buscar cliente", e);
		}
	}
	
	public List<Cliente> todosLosClientes() throws NegocioException{
		try {
			
			//FORMA 1
			return em.createNamedQuery("cliente.todos",Cliente.class).getResultList();
			//FORMA 2
			//return em.createQuery("select cte from Cliente cte", Cliente.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Error al obtener clientes",e);
		}
	}

}
