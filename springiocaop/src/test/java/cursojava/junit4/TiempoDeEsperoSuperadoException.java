package cursojava.junit4;

import java.net.http.HttpConnectTimeoutException;

public class TiempoDeEsperoSuperadoException extends HttpConnectTimeoutException {

	private long timeout;

	public TiempoDeEsperoSuperadoException(String message, long timeout) {
		super(message);
		this.timeout = timeout;
	}

	public long getTimeout() {
		return timeout;
	}
	
	

}
