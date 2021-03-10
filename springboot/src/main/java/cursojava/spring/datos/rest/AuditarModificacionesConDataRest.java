package cursojava.spring.datos.rest;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import cursojava.spring.datos.modelobanco.entidades.Cliente;
import cursojava.spring.datos.modelobanco.entidades.Cuenta;

@Component
@RepositoryEventHandler
public class AuditarModificacionesConDataRest {

	@HandleBeforeCreate
	public void antesDeCrearCliente(Cliente cte) {
		System.out.println("Antes de crear cliente mediante Data REST");
		System.out.println(cte);
	}
	
	@HandleBeforeCreate
	public void antesDeCrearCuenta(Cuenta cta) {
		System.out.println("Antes de crear cuenta mediante Data REST");
		System.out.println(cta);
	}
}
