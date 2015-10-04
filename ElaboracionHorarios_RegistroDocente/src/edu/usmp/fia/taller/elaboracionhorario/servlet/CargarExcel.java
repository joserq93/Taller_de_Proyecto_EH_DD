package edu.usmp.fia.taller.elaboracionhorario.servlet;

import javax.servlet.annotation.WebServlet;
import edu.usmp.fia.taller.common.action.ActionServlet;
import edu.usmp.fia.taller.common.action.HttpMethod;
import edu.usmp.fia.taller.common.action.HttpMethodType;
import edu.usmp.fia.taller.common.action.RequireLogin;

@WebServlet("/elaboracionhorario/cargarExcel")
public class CargarExcel extends ActionServlet{
	
	
	@HttpMethod(HttpMethodType.GET)
	@RequireLogin(true)
	public void llamarPagExcel() throws Exception {
		request.getServletContext().getRequestDispatcher("/cargarExcel.jsp").forward(request, response);
	}
	
	@HttpMethod(HttpMethodType.POST)
	@RequireLogin(true)
	public void cargarExcel() throws Exception {
		
	}
	

}
