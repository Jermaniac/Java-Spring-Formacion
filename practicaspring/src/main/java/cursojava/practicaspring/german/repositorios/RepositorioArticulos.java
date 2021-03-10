package cursojava.practicaspring.german.repositorios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import cursojava.practicaspring.german.datos.entidades.Articulo;

@Repository
@RepositoryRestResource(path = "repoarticulos",collectionResourceRel = "datosarticulo")
public interface RepositorioArticulos extends JpaRepository<Articulo, String> {
	
	@Query("select articulo from Articulo articulo where articulo.precioUnidad between ?1 and ?2")
	List<Articulo> buscarArticuloPorRangoPrecioUnidad(BigDecimal precioDesde, BigDecimal precioHasta);
	
}
