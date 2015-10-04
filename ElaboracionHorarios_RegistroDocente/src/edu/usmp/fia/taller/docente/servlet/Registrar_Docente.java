package edu.usmp.fia.taller.docente.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usmp.fia.taller.common.action.ActionServlet;
import edu.usmp.fia.taller.common.action.HttpMethod;
import edu.usmp.fia.taller.common.action.HttpMethodType;
import edu.usmp.fia.taller.common.action.RequireLogin;

/**
 * Servlet implementation class Registrar_Docente
 */
@WebServlet("/Registrar_Docente")
public class Registrar_Docente extends ActionServlet {
	
	@HttpMethod(HttpMethodType.GET)
	@RequireLogin(true)
	public void registrarDocente() throws Exception {
		System.out.println("ENTRE AL GET");
		request.getServletContext().getRequestDispatcher("/RegistroDocente/registroDocente.jsp").forward(request, response);
	}
	

}
