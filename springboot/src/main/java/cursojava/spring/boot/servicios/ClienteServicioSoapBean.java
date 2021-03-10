package cursojava.spring.boot.servicios;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import cursojava.spring.boot.config.ConfiguracionSOAP;
import cursojava.spring.boot.servicios.soap.AltaCompra;
import cursojava.spring.boot.servicios.soap.Compra;
import cursojava.spring.boot.servicios.soap.DatosCliente;
import cursojava.spring.boot.servicios.soap.Echo;
import cursojava.spring.boot.servicios.soap.EchoResponse;
import cursojava.spring.boot.servicios.soap.Entrada;
import cursojava.spring.boot.servicios.soap.LasCompras;
import cursojava.spring.boot.servicios.soap.LasComprasResponse;
import cursojava.spring.boot.servicios.soap.ObjectFactory;

@Service
public class ClienteServicioSoapBean extends WebServiceGatewaySupport implements ClienteServicioSoap{
	
	private ObjectFactory factory;
	private String urlSOAP;
	
	@Autowired
	public ClienteServicioSoapBean(Jaxb2Marshaller marshaller, ConfiguracionSOAP config) {
		setMarshaller(marshaller);
		setUnmarshaller(marshaller);
		urlSOAP = config.getUrl();
		factory = new ObjectFactory();
	}
	@Override
	public List<Compra> probarCompra() {
		
		AltaCompra altaCompra = factory.createAltaCompra();
		Compra compra = factory.createCompra();
		compra.setCodigo("C100020210308");
		compra.setImporte(new BigDecimal(10));
		
		DatosCliente datosCliente = factory.createDatosCliente();
		
		datosCliente.setId("10203040A");
		datosCliente.setNombre("German");
		datosCliente.setApellidos("Gonzalez");
		
		compra.setCliente(datosCliente);
		
		Entrada entrada1 = factory.createEntrada();
		entrada1.setId("10D");
		entrada1.setCantidad(5);
		entrada1.setPrecio(new BigDecimal("120.23"));
		entrada1.setRefArticulo("ART200");
		
		compra.getEntradas().add(entrada1);
		
		altaCompra.setArg0(compra);
		
		JAXBElement<EchoResponse> respuesta = (JAXBElement<EchoResponse>) getWebServiceTemplate().marshalSendAndReceive(
				//"http://localhost:8080/serviciossoap/ServicioDePrueba",
				urlSOAP,
				altaCompra);
		
		respuesta.getValue();
		
		////////////////////////////////////
		
		LasCompras consultaCompras = factory.createLasCompras();
		
		JAXBElement<LasComprasResponse> respuestaConsulta = (JAXBElement<LasComprasResponse>) getWebServiceTemplate()
				.marshalSendAndReceive(
						//"http://localhost:8080/serviciossoap/ServicioDePrueba",
						urlSOAP,
						consultaCompras);
		
		return respuestaConsulta.getValue().getReturn();
		
			
	}

	@Override
	public String probarEcho(String mensaje) {
		
		Echo echo = factory.createEcho();
		echo.setArg0(mensaje);
		
//		EchoResponse response = (EchoResponse) getWebServiceTemplate()
//				.marshalSendAndReceive("http://localhost:8080/serviciossoap/ServicioDePrueba", echo);
//		
		JAXBElement<EchoResponse> response = (JAXBElement<EchoResponse>) getWebServiceTemplate().marshalSendAndReceive(
				//"http://localhost:8080/serviciossoap/ServicioDePrueba",
				urlSOAP,
				echo);
		
		return response.getValue().getReturn();
	}
	

}
