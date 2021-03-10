package cursojava.spring.mvc.controladores;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cursojava.spring.datos.modelobanco.dao.GestionClientes;
import cursojava.spring.datos.modelobanco.dao.NegocioException;
import cursojava.spring.datos.modelobanco.entidades.Cliente;
import cursojava.spring.mvc.modelo.DatosError;

// Con esta anotacion es como si fuera @Controller y todos los metodos 
// incoporan @ResponseBody
@RestController
@RequestMapping("/rest/clientes")
public class ControladorClientesREST {

	@Autowired
	private GestionClientes gestionClientes;

	@Autowired
	public ControladorClientesREST(GestionClientes gestionClientes) {
		this.gestionClientes = gestionClientes;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{nif:\\d{8}[A-Z]}", produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> buscarPorNif(@PathVariable("nif") String elNif) {
		ResponseEntity<?> retorno = null;
		try {
			Cliente cte = gestionClientes.buscarPorNif(elNif);

			if (cte == null) {
				retorno = ResponseEntity.status(404).body(new DatosError(10040, "Cliente no existe", elNif));
			} else {
				retorno = ResponseEntity.ok(cte);
			}

		} catch (Exception e) {
			e.printStackTrace();
			retorno = ResponseEntity.status(500).body(new DatosError(10500, "No fue posible consultar", elNif));
		}
		return retorno;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> todosLosClientes() {
		ResponseEntity<?> respuesta = null;

		try {
			List<Cliente> clientes = gestionClientes.todosLosClientes();
			respuesta = ResponseEntity.ok(clientes);
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = ResponseEntity.status(500).body(new DatosError(10500, "No fue posible consultar todos los clientes", ""));

		}

		return respuesta;
	}

	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult resultado) {

		ResponseEntity<?> respuesta = null;

		if (resultado.hasErrors()) {
			respuesta = ResponseEntity.badRequest()
					.body(new DatosError(10010, "Cliente con errores validacion", cliente));
		} else {
			try {
				gestionClientes.alta(cliente);
				respuesta = ResponseEntity
						.created(new URI("http://localhost:8080/springmvc/rest/clientes/" + cliente.getNif()))
						.body(cliente);
			} catch (Exception e) {
				e.printStackTrace();
				respuesta = ResponseEntity.status(500).body(new DatosError(10500, "No fue posible dar alta", cliente));
			}
		}
		return respuesta;

	}
	
	 /*PARA HACER MODIFICACIONES SE DEBE HACER SIEMPRE POR POST*/
    @RequestMapping(
            method = RequestMethod.POST, 
            path = "/{nif:\\d{8}[A-Z]}",
                    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
                    consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    /*con @requestBody obtengo el cuerpo de la peticion http y ahi cogere lo que necesite
     * del cliente para modificarlo
     * Si tengo el @valid pongo el bindingResult para saber cual es el resultado de esas
     * validaciones.*/
    
    
    public ResponseEntity<?> modificarCliente(@PathVariable("nif") String nif, 
                                              @Valid @RequestBody Cliente cliente,
                                              BindingResult resultado){
        
        ResponseEntity<?> respuesta = null;
        
        try {
            Cliente cte = gestionClientes.buscarPorNif(nif);
            if(cte != null){
                if(resultado.hasErrors()) {
                    respuesta = ResponseEntity.badRequest().body(
                            new DatosError(100010, "Cliente con errores de validacion", cliente));
                }else {
                    try {
                        gestionClientes.modificar(cliente);
                        respuesta = ResponseEntity.ok(cliente);
                    } catch (NegocioException e) {
                        e.printStackTrace();
                        respuesta = ResponseEntity.badRequest().body(
                                new DatosError(100010, "No fue posible modificar cliente", cliente));
                    }
                }
            
            
            }else {
                respuesta = ResponseEntity.status(404).body(
                        new DatosError(100010, "Cliente no existe", nif));
            }
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = ResponseEntity.badRequest().body(
                    new DatosError(100010, "No fue posible modificar cliente", cliente));
        }
        
        return respuesta;
    }
	
	@RequestMapping(method = RequestMethod.DELETE,
			path = "/{nif:\\d{8}[A-Z]}",
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> borrarCliente(@PathVariable ("nif") String elNif) {

		ResponseEntity<?> retorno = null;

			try {
				Cliente cte = gestionClientes.buscarPorNif(elNif);
				if ( cte == null) {
					retorno = ResponseEntity.status(404).
							body(new DatosError(10040, "Cliente no existe", elNif));
				}
				else {
					gestionClientes.borrar(elNif);
					retorno = ResponseEntity.ok(cte);					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				retorno = ResponseEntity.status(500).body(new DatosError(10500, "No fue posible borrar cliente", ""));
			}
		return retorno;

	}


}
