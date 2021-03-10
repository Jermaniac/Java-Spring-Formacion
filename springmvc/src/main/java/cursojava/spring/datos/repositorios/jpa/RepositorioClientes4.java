package cursojava.spring.datos.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import cursojava.spring.datos.modelobanco.entidades.Cliente;

public interface RepositorioClientes4 extends JpaRepository<Cliente, String>{
	
	// Metodos de consulta personalizados por la sintaxis del nombre del metodo (ya estan implementados)
	
	// Buscar por nombre y apellidos
	List<Cliente> findByNombreAndApellidos(String nombre, String apellidos);
	
	// Buscar por nombre y ordenamos por apellidos de forma ascendente
	List<Cliente> findByNombreOrderByApellidosAsc(String nombre);
	
	// En este caso esta seguro que encontrara un unico cliente
	// Con la anotacion @Nullable permitimos que pueda ser nulo
	// De esta forma si no se encuentra nif, no es un nullPointer
	@Nullable
	Cliente findByNif (String nif);

	
}
