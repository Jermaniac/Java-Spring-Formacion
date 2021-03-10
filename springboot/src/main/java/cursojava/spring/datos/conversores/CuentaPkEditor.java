package cursojava.spring.datos.conversores;

import java.beans.PropertyEditorSupport;

import cursojava.spring.datos.modelobanco.entidades.CuentaPK;

public class CuentaPkEditor extends PropertyEditorSupport{
	
	// CuentaPK%20%5Bbanco=0001,%20sucursal=1111,%20numero=1234567890%5D
	
	// 0001-0001-0000000001
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		if (text.matches("\\d{4}-\\d{4}-\\d{10}")) {
			String [] campos = text.split("-");
			setValue(new CuentaPK(campos[0], campos[1], campos[2]));
		}
		else {
			throw new IllegalArgumentException("Codigo de cuenta incorrecto:"+ text);
		}
	}
	

}
