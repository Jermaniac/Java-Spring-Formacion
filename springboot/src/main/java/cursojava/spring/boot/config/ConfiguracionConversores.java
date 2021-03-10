package cursojava.spring.boot.config;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import cursojava.spring.datos.conversores.CuentaPkEditor;
import cursojava.spring.datos.modelobanco.entidades.CuentaPK;

@Configuration
public class ConfiguracionConversores {
	
	@Bean
	@Lazy(false)
	public CustomEditorConfigurer conversores() {
		System.out.println("Conversores añadidos");
		CustomEditorConfigurer conversores = new CustomEditorConfigurer();
		
		Map<Class<?>,Class<? extends PropertyEditor>> tablaTipoAConversor = new HashMap();
		tablaTipoAConversor.put(CuentaPK.class, CuentaPkEditor.class);
		conversores.setCustomEditors(tablaTipoAConversor);
		return conversores;
	}

}
