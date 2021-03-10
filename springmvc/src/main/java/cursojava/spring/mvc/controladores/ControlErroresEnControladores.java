package cursojava.spring.mvc.controladores;

import java.util.Date;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//Estas clases permiten indicar a que controlador se aplica esta clase
@ControllerAdvice(assignableTypes = {ControladorClientesMVC.class})
public class ControlErroresEnControladores {
	
	@ExceptionHandler({Exception.class})
	public ModelAndView controlDeErrores(Exception ex) {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("elError", ex);
		mv.addObject("fechaHora", new Date());
		return mv;
		
	}
	
}
