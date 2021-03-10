package cursojava.practicaspring.german.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import cursojava.practicaspring.german.datos.entidades.Compra;

// Esta interfaz hereda de JpaRepository<Entidad,PK> para poder realizar operaciones en BBDD.
// Ajustamos las propiedades path y el nombre de las cuentas en el json
@Repository
@RepositoryRestResource(path = "repocompras",collectionResourceRel = "datoscompra")
public interface RepositorioCompras extends JpaRepository<Compra, String> {
	
	@Query("select compra from Compra compra where compra.cliente.nif=:nif")
	List<Compra> buscarComprasPorCliente(@Param(value = "nif") String nif);	
	
}
