package cursojava.spring.datos.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import cursojava.spring.datos.modelobanco.entidades.CuentaPK;
import cursojava.spring.datos.modelobanco.entidades.Movimiento;

// Esta interfaz hereda de JpaRepository<Entidad,PK> para poder realizar operaciones en BBDD.
// Ajustamos las propiedades path y el nombre de las cuentas en el json
@Repository
@RepositoryRestResource(path = "repomovimientos",collectionResourceRel = "datosmovimientos")
public interface RepositorioMovimientos extends JpaRepository<Movimiento, Integer> {
	
	@Query("select mov from Cuenta cta join cta.movimientos mov where cta.pk = :pk "+
		    "and mov.fechaAlta between :desde and :hasta")
	List<Movimiento> consulta(@Param(value = "pk") CuentaPK pk, @Param(value = "desde") Date fechaDesde, @Param(value = "hasta") Date fechaHasta);
	
	
}
