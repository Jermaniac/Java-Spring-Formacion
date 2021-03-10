package cursojava.spring.boot.servicios;

import java.util.List;

import cursojava.spring.boot.servicios.soap.Compra;

public interface ClienteServicioSoap {

	String probarEcho(String mensaje);

	List<Compra> probarCompra();

}