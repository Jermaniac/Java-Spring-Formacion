package cursojava.spring.boot.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cursojava.spring.datos.modelobanco.entidades.Cuenta;
import cursojava.spring.datos.modelobanco.entidades.CuentaPK;
import cursojava.spring.datos.repositorio.RepositorioCuentas;

@Controller
@RequestMapping("/data/cuentas")
public class ControladorCuentas {
	
	@Autowired
	private RepositorioCuentas repoCuentas;

	@RequestMapping(
			method = RequestMethod.GET,
			path="/buscar/{banco:\\d{4}}/{sucursal:\\d{4}}/{numero:\\d{10}}"
			)
	public ModelAndView buscar(
			@PathVariable("banco") String banco, 
			@PathVariable("sucursal") String sucursal,
			@PathVariable("numero") String numero
			) {
		
		ModelAndView mv = new ModelAndView("listadocuentas");
		
		// Optional es una forma que tienes en java para evitar los nulos como retorno de una funcion
		// No devolvera excepcion aunque sea nulo
		Optional<Cuenta> laCuenta = repoCuentas.findById(new CuentaPK(banco, sucursal, numero));
		List<Cuenta> datos = List.of();
		if(laCuenta.isPresent()) {
			datos= List.of(laCuenta.get());
		}
		mv.addObject("datos",datos);
		return mv;
		
	}
	
	// /consulta?page=0&size=20&sort=saldo
	@RequestMapping(
			method = RequestMethod.GET,
			path="/consulta"
			)
	public ModelAndView buscarConPaginacion(Pageable paginacion) {
		ModelAndView mv = new ModelAndView("listadocuentas");
		// Devuelve un Objeto Page
		Page<Cuenta> consulta = repoCuentas.findAll(paginacion);
		mv.addObject("datos",consulta.getContent());
		return mv;
	}
	
	
	public void setRepoCuentas(RepositorioCuentas repoCuentas) {
		this.repoCuentas = repoCuentas;
	}
	
	
	
}
