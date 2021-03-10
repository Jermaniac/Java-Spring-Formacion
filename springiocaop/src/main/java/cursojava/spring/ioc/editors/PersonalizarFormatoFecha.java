package cursojava.spring.ioc.editors;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

public class PersonalizarFormatoFecha implements PropertyEditorRegistrar{

	private String pattern = "dd-MM-yyyy";
	

	public String getPattern() {
		return pattern;
	}


	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	public void registerCustomEditors(PropertyEditorRegistry registry) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		registry.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
	}

}
