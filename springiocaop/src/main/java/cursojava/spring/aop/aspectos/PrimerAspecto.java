package cursojava.spring.aop.aspectos;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // Indica que es aspecto
@Component // Para que se carge con componentScan y no tener que crear metodo
public class PrimerAspecto {

	// Aspecto -> advice + point cut
	// Advice -> el codigo que queremos incorporar en la ejecucion de algun elemento
	// Joint Point -> el punto de ejecucion real (llamada a un metodo, invocar
	// constructor...)
	// Point Cut -> Conjunto de joint point

	// Tipos
//	@Before
//	@After
//	@Around
//	@AfterReturning
//	@AfterThrowing
	// AspectJ Expression languaje
	// Con esta en concreto estoy tomando todos los metodos de la clase
	// GestionBancoBean independientemente del numero de parametros
	@Before("execution(* cursojava.spring.ioc.beans.GestionBancoBean.*(..))")
	public void antesDeCadaMetodo(JoinPoint jp) {
		System.out.println("Mensaje desde PrimerAspecto::antesDeCadaMetodo");
		// Con la clase JoinPoint podemos ver todos los elementos de la clase en
		// concreto, como el nombre, metodos y argumentos
		String nombreClase = jp.getTarget().getClass().getName();
		String definicionMetodo = jp.getSignature().getName();
		// Con este metodo se imprimen todos los elementos de la matriz
		String argumentos = Arrays.toString(jp.getArgs());
		System.out.println(String.format("Nombre de la clase: %s%nMetodo: %s%nArgumentos: %s%n", nombreClase,
				definicionMetodo, argumentos));
	}

	@Around("execution(* cursojava.spring.ioc.beans.*.*(..))")
	public void auditarEjecucion(ProceedingJoinPoint pjp) {
		try {
			String nombreClase = pjp.getTarget().getClass().getName();

			String metodo = pjp.getSignature().toLongString();

			// Con System.nanoTime puedo cronometrar, en este caso cuanto tarda el metodo en
			// ejecutarse
			long inicio = System.nanoTime();

			// Ejecutar el metodo
			Object resultado = pjp.proceed();

			long fin = System.nanoTime();

			System.out.println(String.format("%s -> %s nanosegundos", metodo, (fin - inicio) / 1e6));

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
