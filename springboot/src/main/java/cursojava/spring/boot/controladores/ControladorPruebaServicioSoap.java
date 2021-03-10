package cursojava.spring.boot.controladores;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cursojava.spring.boot.servicios.ClienteServicioSoap;
import cursojava.spring.boot.servicios.soap.AltaCompra;
import cursojava.spring.boot.servicios.soap.Compra;
import cursojava.spring.boot.servicios.soap.DatosCliente;
import cursojava.spring.boot.servicios.soap.Entrada;
import cursojava.spring.boot.servicios.soap.ObjectFactory;

@Controller
@RequestMapping("/pruebas/soap")
public class ControladorPruebaServicioSoap {
	

	@Autowired
	private ClienteServicioSoap servicioSoap;
	
	@RequestMapping(method = RequestMethod.GET, path = "/echo")
	public ModelAndView echo(@RequestParam(name="mensaje",defaultValue = "Mensaje prueba Soap(echo)") String mensaje){
		ModelAndView mv = new ModelAndView("clientesoap");
		
		String respuestaEcho = servicioSoap.probarEcho(mensaje);
		
		mv.addObject("mensaje",respuestaEcho);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/compra")
	public ModelAndView compra(@RequestParam(name="codigo",defaultValue = "C400020210308") String codigo){
		
		ModelAndView mv = new ModelAndView("clientesoap");
		
		List<Compra> compras = servicioSoap.probarCompra();
		
		mv.addObject("mensaje","Resultado de Comprar");
		
		mv.addObject("compras",compras);

		
		return mv;
	}

	public void setServicioSoap(ClienteServicioSoap servicioSoap) {
		this.servicioSoap = servicioSoap;
	}
	
}
