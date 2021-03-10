package cursojava.spring.datos.repositorios.jpa;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.datos.modelobanco.entidades.Cliente;

// Aqui usamos la interfaz de Operaciones comunes que extiende de JpaRepository y la especializamos.
// En este caso colocamos <Cliente,String> <Clase,id>
//@Async //Podemos indicar que todos los metodos son asincronos de forma global
public interface RepositorioClientesAsync extends OperacionesComunesRepo<Cliente, String>{

	// Con la anotacion @Async estos metodos NO BLOQUEAN. "Podria tardar mas en ejecutarse estos metodos"
	// Existe una interfaz Future que es recomendado para estos casos
	@Query("update Cliente cte set cte.provincia = ?1")
	@Modifying
	@Transactional
	@Async
	Future<Void> cambiarProvincia(String nombreProvincia);
	
	// En este caso no usamos la nomenclatura general, pero indicamos la consulta a ejecutar
	// Usamos la nomenclatura JPQL y la sintaxis %?1 para indicar que es el primer parametro
	@Query(value = "select cte from Cliente cte where cte.nombre like %?1")
	@Async
	Future<List<Cliente>> buscarPorNombre(String nombre);
	
	// En este otro caso seguimos usando JQPL
	// Pero cambiamos de forma de indicar parametros 
	@Query(value = "select cte from Cliente cte where cte.fechaAlta between :desde and :hasta")
	@Async
	Future<List<Cliente>> buscarPorRangoDeFechaAlta(@Param("desde") Date desde, @Param("hasta") Date hasta);
	
	// En este caso usamos SQL nativo, lo indicamos en la notacion
	@Query(value = "SELECT * FROM CLIENTES WHERE PROVINCIA = ?1", nativeQuery = true)
	@Async
	Future<List<Cliente>> buscarPorProvincia(String provincia);
}
