package cursojava.spring.datos.validaciones;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorNif implements ConstraintValidator<Nif, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
				
		if (value!=null && value.matches("\\d{8}[A-Z]")) { //si no es nulo y cumple con la expresion regular(aprox)
			return true;
		}
		else {
			return false;
		}
	}

}
