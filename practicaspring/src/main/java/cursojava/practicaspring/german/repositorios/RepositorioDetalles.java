package cursojava.practicaspring.german.repositorios;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import cursojava.practicaspring.german.datos.entidades.Detalle;
import cursojava.practicaspring.german.datos.entidades.DetallePK;

@Repository
@RepositoryRestResource(path = "repodetalles",collectionResourceRel = "datosdetalle")
public interface RepositorioDetalles extends JpaRepository<Detalle, DetallePK> {
	
	@Query("select detalle from Detalle detalle where detalle.pk.compra=:codigo")
	List<Detalle> buscarDetallePorCompra(String codigo);
	
}
