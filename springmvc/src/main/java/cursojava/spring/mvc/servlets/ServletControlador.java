package cursojava.spring.mvc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletControlador extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Para responses con contenido binario
		// ServletOutputStream outputStream = response.getOutputStream();
		
		// Para responses con contenido basado en texto
		PrintWriter out = response.getWriter();
		
		// Con esto podemos incorporar en la cabecera el tipo de dato, la estructura son MIME types
		// Con esta en concreto indicamos que es un archivo html
		response.setContentType("text/html");
		// Esta no es la forma mas adecuada de hacerlo
		out.println("<html>");
		out.println("<body>");
		out.printf("<h1>Fecha: %s</h1>",new Date());
		out.println("</body>");
		out.println("</html>");
		
		// Para que no se quede en memoria
		out.flush();

		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
