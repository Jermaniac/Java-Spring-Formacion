package cursojava.spring.mvc.controladores;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cursojava.spring.datos.modelobanco.entidades.Cliente;
import cursojava.spring.datos.repositorios.jpa.RepositorioClientes;
import cursojava.spring.datos.repositorios.jpa.RepositorioClientes1;
import cursojava.spring.datos.repositorios.jpa.RepositorioClientes2;
import cursojava.spring.datos.repositorios.jpa.RepositorioClientes3;
import cursojava.spring.datos.repositorios.jpa.RepositorioClientes4;
import cursojava.spring.datos.repositorios.jpa.RepositorioClientesAsync;

@Controller
@RequestMapping("/data/clientes")
public class ControladorProbarRepositoriosJPA {

	// El que extiende de Repository
	@Autowired
	private RepositorioClientes1 repo1;

	// El que extiende de CrudRepository
	@Autowired
	private RepositorioClientes2 repo2;

	// El que extiende de PaggingAndSortingRepository
	@Autowired
	private RepositorioClientes3 repo3;

	// El que extiende de JpaRepository
	@Autowired
	private RepositorioClientes4 repo4;

	// El que extiende de otra interfaz (OperacionesComunesRepo)
	// que a su vez extiende de JpaRepository
	@Autowired
	private RepositorioClientes repo;

	// El que incopora asincronismo
	@Autowired
	private RepositorioClientesAsync repoAsync;

	@RequestMapping(method = RequestMethod.GET, path = "/prueba1")
	public ModelAndView prueba1() {
		ModelAndView mv = new ModelAndView("listado");
		// Con el metodo findAll del repo4 se puede obtener todos los clientes
		// La clase que hereda de JpaRepository tiene muchos metodos que facilitan
		// consultas
		mv.addObject("datos", repo4.findAll());
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/prueba2")
	public ModelAndView prueba2() {
		ModelAndView mv = new ModelAndView("listado");
		// Con el metodo findByNombreAndApellidos del repo4 se puede obtener todos los
		// clientes con esos datos
		// La clase que hereda de JpaRepository tiene muchos metodos que facilitan
		// consultas
		mv.addObject("datos", repo4.findByNombreAndApellidos("Nombre 7", "Apellido 7"));

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/prueba3")
	public ModelAndView prueba3() {
		ModelAndView mv = new ModelAndView("listado");
		// Con el metodo findByNif del repo4 se puede obtener el cliente con dicho nif
		// La clase que hereda de JpaRepository tiene muchos metodos que facilitan
		// consultas
		// Con List.of consigo una lista.
		List<Cliente> datos = List.of();
		Cliente cte = repo4.findByNif("00000001B");
		// De esta forma controlamos el nullPointer y en el caso de que sea nulo, la
		// lista esta vacia.
		if (cte != null) {
			datos = List.of(cte);
		}
		mv.addObject("datos", datos);

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/prueba4")
	public ModelAndView prueba4() {
		ModelAndView mv = new ModelAndView("listado");
		// Con el metodo findByNombreAOrderByApellidos del repo se puede obtener los
		// clientes con ese nombre y ordenados por apellidos
		// En este caso, estamos usando DOS interfaces, una que hereda de JpaRepository
		// y otra que hereda esa interfaz y la especializa
		mv.addObject("datos", repo.findByNombreOrderByApellidos("Nombre 7"));

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/prueba5")
	public ModelAndView prueba5() {
		ModelAndView mv = new ModelAndView("listado");
		mv.addObject("datos", repo.findTop20ByProvinciaOrderByApellidos("Madrid"));

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/prueba6")
	public ModelAndView prueba6() {
		ModelAndView mv = new ModelAndView("listado");
		mv.addObject("datos", repo.buscarPorRangoDeFechaAlta(Date.valueOf("2021-03-01"), Date.valueOf("2021-03-03")));

		return mv;
	}

	// En esta prueba ponemos una provincia a todos los datos y las buscamos para
	// ver si se ha realizado
	@RequestMapping(method = RequestMethod.GET, path = "/prueba7")
	public ModelAndView prueba7(@RequestParam(name = "provincia", defaultValue = "Madrid") String provincia) {
		ModelAndView mv = new ModelAndView("listado");
		repo.cambiarProvincia(provincia);
		mv.addObject("datos", repo.buscarPorProvincia(provincia));
		return mv;
	}

	// En esta prueba usamos asincronismo
	@RequestMapping(method = RequestMethod.GET, path = "/prueba10")
	public ModelAndView prueba10(@RequestParam(name = "provincia", defaultValue = "Prueba10") String provincia) {
		ModelAndView mv = new ModelAndView("listado");

		// Este metodo es necesario que acabe, es decir, que se ejecute por completo
		Future<Void> cambio = repoAsync.cambiarProvincia(provincia);

		// Este metodo .get ES BLOQUEANTE, bloquea el hilo actual (maximo 5 seg en este
		// caso)
		try {
			cambio.get(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Future<List<Cliente>> resultado = repoAsync.buscarPorProvincia(provincia);

		// En este caso el get bloquea indefinidamente (NO LIMITS)
		try {
			List<Cliente> datos = resultado.get();
			mv.addObject("datos", datos);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		// cambio.isDone() // metodo no bloqueante que pregunta si ya ha terminado el
		// proceso

		return mv;
	}

	// En esta prueba usamos la clase Sort en el metodo como parametro para ordenar
	// por apellidos ascendente y nombre descendente
	// Sort.unsorted() -> NO EMPLEAMOS ORDENACION
	@RequestMapping(method = RequestMethod.GET, path = "/prueba11")
	public ModelAndView prueba11() {
		ModelAndView mv = new ModelAndView("listado");
		mv.addObject("datos", repo.buscarPorRangoDeFechaAltaOrdenable(Date.valueOf("2021-03-01"),
				Date.valueOf("2021-03-03"), Sort.by("apellidos").descending().and(Sort.by("nombre").ascending())
		));

		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/prueba12")
	public ModelAndView prueba12() {
		ModelAndView mv = new ModelAndView("listado");
		mv.addObject("datos", repo.buscarPorRangoDeFechaAltaConPaginacion(Date.valueOf("2021-03-01"),
				Date.valueOf("2021-03-03"), PageRequest.of(4, 20)
		));

		return mv;
	}

	// En esta prueba usamos la clase Pageable
	// Pageable.unpaged() -> NO EMPLEAMOS Paginacion
	// Con PageRequest indicamos que se muestran 4 paginas con 20 y ademas ordenamos por nombre ascendente
	@RequestMapping(method = RequestMethod.GET, path = "/prueba13")
	public ModelAndView prueba13() {
		ModelAndView mv = new ModelAndView("listado");
		mv.addObject("datos", repo.buscarPorRangoDeFechaAltaConPaginacion(Date.valueOf("2021-03-01"),
				Date.valueOf("2021-03-03"), PageRequest.of(4, 20, Sort.by("nombre").ascending())
		));

		return mv;
	}
	
	//En este caso podemos tener el objeto Pageable como parametro en el controlador
	// Pageable -> Permite indicar con unos parametros los valores de paginacion de validacion
	// Esto habria que poner en la url (EJEMPLO): ?page=0&size=20&sort=nombre,desc
	@RequestMapping(method = RequestMethod.GET, path = "/prueba14")
	public ModelAndView prueba14(@PageableDefault(page = 0, size=20) Pageable paginacion) {
		ModelAndView mv = new ModelAndView("listado");
		mv.addObject("datos", repo.buscarPorRangoDeFechaAltaConPaginacion(
				Date.valueOf("2021-03-01"),
				Date.valueOf("2021-03-03"), 
				paginacion
		));

		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/prueba15")
	public ModelAndView prueba15(@RequestParam(name = "nombre", defaultValue = "1") String nombre) {
		ModelAndView mv = new ModelAndView("listadobasico");
		mv.addObject("datos", repo.buscarPorNombreDatosBasicos(nombre));
		return mv;
	}
	
	public RepositorioClientes1 getRepo1() {
		return repo1;
	}

	public RepositorioClientes2 getRepo2() {
		return repo2;
	}

	public RepositorioClientes3 getRepo3() {
		return repo3;
	}

	public RepositorioClientes4 getRepo4() {
		return repo4;
	}

	public void setRepo1(RepositorioClientes1 repo1) {
		this.repo1 = repo1;
	}

	public void setRepo2(RepositorioClientes2 repo2) {
		this.repo2 = repo2;
	}

	public void setRepo3(RepositorioClientes3 repo3) {
		this.repo3 = repo3;
	}

	public void setRepo4(RepositorioClientes4 repo4) {
		this.repo4 = repo4;
	}

	public RepositorioClientes getRepo() {
		return repo;
	}

	public void setRepo(RepositorioClientes repo) {
		this.repo = repo;
	}

	public RepositorioClientesAsync getRepoAsync() {
		return repoAsync;
	}

	public void setRepoAsync(RepositorioClientesAsync repoAsync) {
		this.repoAsync = repoAsync;
	}

}
