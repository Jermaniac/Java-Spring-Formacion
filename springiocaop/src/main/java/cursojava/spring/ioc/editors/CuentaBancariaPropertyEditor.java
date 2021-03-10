package cursojava.spring.ioc.editors;

import java.beans.PropertyEditorSupport;

import cursojava.spring.ioc.beans.CuentaBancaria;

public class CuentaBancariaPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String codigoCuenta) throws IllegalArgumentException {

		// Primero validamos que la cadena que nos pasan es de un codigo
		// Podemos utilizar una expresion regular
		// De forma estricta se ponen entre corchetes {digitos} y de forma literal los
		// guiones -
		if (codigoCuenta.matches("^\\d{4}-\\d{4}-\\d{2}-\\d{10}$")) {
			String[] campos = codigoCuenta.split("-");
			CuentaBancaria cuenta = new CuentaBancaria(campos[0], campos[1], campos[2], campos[3]);
			setValue(cuenta);
		}
		// Si no es valido se lanza una excepcion
		else {
			throw new IllegalArgumentException(
					String.format("El codigo de cuenta no es valido: [%s]", codigoCuenta));
		}
	}

}
