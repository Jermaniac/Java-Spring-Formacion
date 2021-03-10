package cursojava.spring.datos.repositorios.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.datos.modelobanco.entidades.Cliente;
import cursojava.spring.datos.proyecciones.DatosBasicosCliente;

// Aqui usamos la interfaz de Operaciones comunes que extiende de JpaRepository y la especializamos.
// En este caso colocamos <Cliente,String> <Clase,id>
public interface RepositorioClientes extends OperacionesComunesRepo<Cliente, String>{
	
	// Esto NO es una consulta, hace cambios en BBDD, indicamos modificaciones con @Modifying y 
	// capacidad transaccional con @Transactional
	// Se le cambia la provincia a TODOS
	@Query("update Cliente cte set cte.provincia = ?1")
	@Modifying
	@Transactional
	void cambiarProvincia(String nombreProvincia);
	
	// En este caso no usamos la nomenclatura general, pero indicamos la consulta a ejecutar
	// Usamos la nomenclatura JPQL y la sintaxis %?1 para indicar que es el primer parametro
	@Query(value = "select cte from Cliente cte where cte.nombre like %?1")
	List<Cliente> buscarPorNombre(String nombre);
	
	// En este otro caso seguimos usando JQPL
	// Pero cambiamos de forma de indicar parametros 
	@Query(value = "select cte from Cliente cte where cte.fechaAlta between :desde and :hasta")
	List<Cliente> buscarPorRangoDeFechaAlta(@Param("desde") Date desde, @Param("hasta") Date hasta);
	
	// En este caso usamos SQL nativo, lo indicamos en la notacion
	@Query(value = "SELECT * FROM CLIENTES WHERE PROVINCIA = ?1", nativeQuery = true)
	List<Cliente> buscarPorProvincia(String provincia);
	
	// La consulta puede ser cualquiera
	// Con el tipo clase Sort denotamos que es ORDENABLE, permite ordenacion dinamica (por condiciones)
	@Query(value = "select cte from Cliente cte where cte.fechaAlta between :desde and :hasta")
	List<Cliente> buscarPorRangoDeFechaAltaOrdenable(
			@Param("desde") Date desde, @Param("hasta") Date hasta,
			Sort ordenacion
			);
	
	// Ahora usamos la clase Pageable 
	@Query(value = "select cte from Cliente cte where cte.fechaAlta between :desde and :hasta")
	List<Cliente> buscarPorRangoDeFechaAltaConPaginacion(
			@Param("desde") Date desde, @Param("hasta") Date hasta,
			Pageable ordenacion
			);
	
	// Empleamos interface que limita las propiedades que tenemos en memoria
	@Query(value = "select cte from Cliente cte where cte.nombre like %?1")
	List<DatosBasicosCliente> buscarPorNombreDatosBasicos(String nombre);
	
}
