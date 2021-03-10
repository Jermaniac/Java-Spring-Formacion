package cursojava.spring.mvc.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cursojava.spring.datos.modelobanco.dao.GestionClientes;
import cursojava.spring.datos.modelobanco.dao.NegocioException;
import cursojava.spring.datos.modelobanco.entidades.Cliente;
import cursojava.spring.mvc.modelo.DatosBusqueda;

// En este caso el requestmapping funciona a modo de url general
@Controller
@RequestMapping("/clientes")
public class ControladorClientesMVC {

	@Autowired
	private GestionClientes gestionClientes;

	@ModelAttribute("lasProvincias")
	public Map<String, String> pasarProvincias() {
		HashMap<String, String> tablaProvincias = new HashMap<String, String>();
		tablaProvincias.put("08", "Barcelona");
		tablaProvincias.put("28", "Madrid");
		tablaProvincias.put("40", "Segovia");
		return tablaProvincias;
	}
// Lo ponemos en ControlErroresEnControladores.java
//	@ExceptionHandler({Exception.class})
//	public ModelAndView controlDeErrores(Exception ex) {
//		
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("elError", ex);
//		mv.addObject("fechaHora", new Date());
//		return mv;
//		
//	}

	@RequestMapping(method = { RequestMethod.GET }, path = "/alta")
	public ModelAndView inicioAlta() {

		ModelAndView mv = new ModelAndView("clientes");
		mv.addObject("cliente", new Cliente());
		return mv;
	}

	// En este caso decidimos tomar como parametro un Cliente
	@RequestMapping(method = { RequestMethod.POST }, path = "/alta")
	public String alta(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult resultadoValidaciones,
			Model model) {
		System.out.println(cliente);
		if (resultadoValidaciones.hasErrors()) {
			return "clientes";
		}

		try {

			gestionClientes.alta(cliente);

			model.addAttribute("altaOK", "Alta realizada con exito.");

			return "clientes_alta_ok";

		} catch (NegocioException e) {

			e.printStackTrace();

			model.addAttribute("errorAlta", "No se pudo realizar alta.");
			return "clientes_alta_ko";

		}

	}
	
	@GetMapping("/buscar")
	public ModelAndView buscar() {
		ModelAndView mv = new ModelAndView("buscar");
		mv.addObject("datos", new DatosBusqueda());
		return mv;
	}
	
	@PostMapping("/buscar")
	public String realizarBusqueda(@ModelAttribute("datos") DatosBusqueda datos, Model model) {
		try {
			if (datos.getBuscarTodos()) {
				List<Cliente> todosClientes = gestionClientes.todosLosClientes();
				model.addAttribute("losClientes",todosClientes);
			}
			else {
				Cliente cte = gestionClientes.buscarPorNif(datos.getNif());
				ArrayList<Cliente> lista = new ArrayList<Cliente>();
				if (cte != null) {
					lista.add(cte);
				}
				model.addAttribute("losClientes",lista);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("hayError",e);
		}
		return "buscar";
	}

	//SERVICIOS REST basicos
	
	@RequestMapping(
			method = RequestMethod.GET,
			path="/{nif:\\d{8}[A-Z]}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	@ResponseBody
	public Cliente buscarPorNif(@PathVariable("nif") String nif) {
		
		Cliente cliente = null;
		
		try {
			cliente = gestionClientes.buscarPorNif(nif);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
	
}
