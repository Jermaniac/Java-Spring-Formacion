package cursojava.spring.datos.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

// Indicamos que no queremos una implementacion, si no querer tener definidos metodos comunes a varias entidades
@NoRepositoryBean
public interface OperacionesComunesRepo<T, ID> extends JpaRepository<T, ID> {
	
	public List<T> findTop20ByProvinciaOrderByApellidos(String provincia);

	public List<T> findByNombre(String nombre);

	public List<T> findByNombreOrderByApellidos(String nombre);

}
