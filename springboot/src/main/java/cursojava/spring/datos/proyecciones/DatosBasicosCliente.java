package cursojava.spring.datos.proyecciones;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import cursojava.spring.datos.modelobanco.entidades.Cliente;

// Tiene que estar en el paquete de la entidad
// YA ESTA EN EL PROYECTO basesdedatos
@Projection(name = "nombreYFecha",types = {Cliente.class})
public interface DatosBasicosCliente {
	
	String getNombre();
	
	Date getFechaAlta();

}
