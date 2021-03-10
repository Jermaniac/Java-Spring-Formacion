package cursojava.spring.mvc.controladores;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cursojava.spring.mvc.modelo.Alumno;

@Controller
public class ControladorBasico {

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = "/prueba")
	public String primeraPrueba(Model modelo) {

		modelo.addAttribute("hora", new Date());
		modelo.addAttribute("alumno", new Alumno("12345678A", "German", "Perez", "correofalso@gmail.com"));
		System.out.println("/prueba invocada");

		// aqui se puede poner el nombre, navegacion por nombre natural/forward
		// se le pasa la ejecucion a la pagina prueba.jsp
		return "prueba";

		// aqui se navega por redireccion
		// return "redirect:prueba.jsp";
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = "/prueba2")
	public String navegarPorNombreLogico(Model modelo) {

		modelo.addAttribute("hora", new Date());
		modelo.addAttribute("alumno", new Alumno("12345678A", "German", "Perez", "correofalso@gmail.com"));
		System.out.println("/prueba2 invocado");

		return "prueba_ok";

		// aqui se puede poner el nombre, navegacion por nombre natural/forward
		// se le pasa la ejecucion a la pagina prueba.jsp
		// return "prueba.jsp";

		// aqui se navega por redireccion
		// return "redirect:prueba.jsp";
	}

	@GetMapping("/prueba3")
	public ModelAndView entregarVistaYModelo(HttpServletRequest request) {

		System.out.println("URL:" + request.getRequestURI());
		System.out.println("IP:" + request.getRemoteAddr());
		// En el nombre de la vista podemos elegir si la vista es el de prueba.jsp o
		// prueba_ok.jsp de esta forma
		ModelAndView modelYVista = new ModelAndView("prueba_ok");

		modelYVista.addObject("hora", new Date());
		modelYVista.addObject("alumno", new Alumno("87654321A", "Nombre2", "Apellido2", "correofalso2@gmail.com"));
		return modelYVista;

	}

	@RequestMapping(method = RequestMethod.GET, 
			path = "/cuenta/{codigo:\\d{4}-\\d{4}-\\d{2}-\\d{10}}/{fecha:[0-9]{4}-[0-9]{2}-[0-9]{2}}")
	public String buscarCuentaBancaria(@PathVariable("codigo") String codigoCuenta,
			@PathVariable("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaConsulta, Model modelo) {
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codigo", codigoCuenta);
		mapa.put("fecha", fechaConsulta);
		modelo.addAttribute("datos",mapa);

		return "cuenta";
	}
	
	@RequestMapping(method = RequestMethod.GET, 
			path = "/cuenta", params = {"codigo","fecha"})
	public String buscarCuentaBancariaPorParametrosEnConsulta(@RequestParam("codigo") String codigoCuenta,
			@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaConsulta, Model modelo) {
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codigo", codigoCuenta);
		mapa.put("fecha", fechaConsulta);
		modelo.addAttribute("datos",mapa);

		return "cuenta";
	}

}
