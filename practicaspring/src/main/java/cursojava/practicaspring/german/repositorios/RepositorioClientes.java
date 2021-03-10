package cursojava.practicaspring.german.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import cursojava.practicaspring.german.datos.entidades.Cliente;

// Esta interfaz hereda de JpaRepository<Entidad,PK> para poder realizar operaciones en BBDD.
// Ajustamos las propiedades path y el nombre de las cuentas en el json
@Repository
@RepositoryRestResource(path = "repoclientes",collectionResourceRel = "datoscliente")
public interface RepositorioClientes extends JpaRepository<Cliente, String> {
	
	@Query("select cliente from Cliente cliente where cliente.nif= :nif and cliente.clave=:password")
	Cliente buscarPorUsuarioClave(@Param(value = "nif") String nif, @Param(value = "password") String password);	
	
}
