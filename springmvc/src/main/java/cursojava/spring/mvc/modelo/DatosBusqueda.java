package cursojava.spring.mvc.modelo;

public class DatosBusqueda {
	
	//@Nif
	public String nif = "";
	
	public Boolean buscarTodos = false;
	
	public String getNif() {
		return nif;
	}



	public void setNif(String nif) {
		this.nif = nif;
	}



	public Boolean getBuscarTodos() {
		return buscarTodos;
	}



	public void setBuscarTodos(Boolean buscarTodos) {
		this.buscarTodos = buscarTodos;
	}


}
