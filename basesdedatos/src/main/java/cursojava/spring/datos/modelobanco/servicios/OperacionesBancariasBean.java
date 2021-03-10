package cursojava.spring.datos.modelobanco.servicios;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.datos.modelobanco.dao.NegocioException;
import cursojava.spring.datos.modelobanco.entidades.Cliente;
import cursojava.spring.datos.modelobanco.entidades.Cuenta;
import cursojava.spring.datos.modelobanco.entidades.CuentaPK;
import cursojava.spring.datos.modelobanco.entidades.Movimiento;

@Service
public class OperacionesBancariasBean implements OperacionesBancarias {

	// Esto es como un @Autowired
	@PersistenceContext
	private EntityManager em;

	public void crearClientes(int numeroDeClientes) throws NegocioException {

		try {
			for (int i = 1; i <= numeroDeClientes; i++) {
				// formateamos la cadena para que cree nifs, nombres y apellidos
				Cliente cte = new Cliente(
						String.format("%08dA", i), 
						String.format("Nombre %d", i),
						String.format("Apellido %d", i));
				em.persist(cte);
			}
			// Enviar todo el codigo sql que no habias enviado hasta ahora. Enviar todo lo
			// pendiente
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Error al crear varios clientes.", e);
		}

	}

	public void crearCuentas (int numeroBancos, int numeroSucursales, int numeroCuentas) throws NegocioException{
		
		try {
			Random rnd = new Random();
			for (int i = 1; i <= numeroBancos; i++) {
				for (int j = 1; j <= numeroSucursales; j++) {
					for (int k = 1; k <= numeroCuentas; k++) {
						CuentaPK cuentaPk = new CuentaPK(
								String.format("%04d", i), 
								String.format("%04d", j), 
								String.format("%010d",k)
								);
						Cuenta cuenta = new Cuenta(cuentaPk,BigDecimal.valueOf(rnd.nextDouble() * 100000));
						
						em.persist(cuenta);
						
						Movimiento movimiento = new Movimiento(cuenta, "Alta de cuenta");
						
						em.persist(movimiento);
					}
				}
			}
			
			em.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Error al crear varias cuentas",e);
		}

	}
	
	public void asignarCuentasAClientes (int numeroMaximoDeCuentas, int numeroDeBancos, int numeroSucursales, int numeroCuentasBancarias) throws NegocioException{
		
		try {
			Random rnd = new Random();
			
			//Consulta PLSQL con la clase que espero
			TypedQuery<Cliente> consulta = em.createQuery("select cte from Cliente cte", Cliente.class);
			 
			// Como obtengo como resultado una lista, podemos indicarlo de esta forma
			List<Cliente> losClientes = consulta.getResultList();
			 
			for (Cliente cte : losClientes) {
				int numeroCuentas = rnd.nextInt(numeroMaximoDeCuentas+1);
				
				for ( int i = 1; i <= numeroCuentas; i ++) {
					CuentaPK pk = new CuentaPK(
							String.format("%04d", rnd.nextInt(numeroDeBancos) + 1), 
							String.format("%04d", rnd.nextInt(numeroSucursales) + 1), 
							String.format("%010d", rnd.nextInt(numeroCuentasBancarias) + 1)
							);
					
					Cuenta cta = em.find(Cuenta.class, pk);
					
					if (cta != null) {
						// No hace falta persist, es automatico
						cta.getClientes().add(cte);
					}

				}
				
			}
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Error al asociar Cuentas con Clientes.",e);
		}
		
	}
	
	@Transactional(rollbackFor = {NegocioException.class})
	public void cargarDatosEnModeloBanco (
			int numeroDeClientes, 
			int numeroBancos, int numeroSucursales, int numeroCuentas,
			int numeroMaximoDeCuentas
			) throws NegocioException{
		
		try {
			crearClientes(numeroDeClientes);
			crearCuentas(numeroBancos, numeroSucursales, numeroCuentas);
			asignarCuentasAClientes(numeroMaximoDeCuentas, numeroBancos, numeroSucursales, numeroCuentas);
			
		} catch (NegocioException e) {
			e.printStackTrace();
			throw new NegocioException("Error al cargarDatosEnModeloBanco",e);
		}
		
		
	}

}
