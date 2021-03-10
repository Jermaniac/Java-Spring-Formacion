package cursojava.spring.datos.repositorio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import cursojava.spring.datos.modelobanco.entidades.Cuenta;
import cursojava.spring.datos.modelobanco.entidades.CuentaPK;

// Esta interfaz hereda de JpaRepository<Entidad,PK> para poder realizar operaciones en BBDD.
// Ajustamos las propiedades path y el nombre de las cuentas en el json
@Repository
@RepositoryRestResource(path = "repocuentas",collectionResourceRel = "datoscuentas")
public interface RepositorioCuentas extends JpaRepository<Cuenta, CuentaPK> {
	
	@Query("select cta from Cuenta cta where cta.saldo between ?1 and ?2")
	List<Cuenta> buscarPorRangoDeSaldos (BigDecimal desde, BigDecimal hasta);
	
	@RestResource(exported = false) // No disponible para cliente REST
	@Query("select cta from Cuenta cta where cta.saldo < 0")
	List<Cuenta> buscarEnDescubierto ();
	
	@RestResource(
			description = @Description("Consulta para buscar por banco"),
			path = "porBanco",
			rel = "banco"
			)
	@Query("select cta from Cuenta cta where cta.pk.banco= :banco")
	List<Cuenta> buscarPorBanco(@Param(value = "banco") String banco);
	
	// MIS METODOS
	
//	@Query("select cta from Cuenta cta where cta.fechaAlta between ?1 and ?2")
//	List<Cuenta> buscarPorFechas(Date desde, Date hasta);
	
	
	

}
