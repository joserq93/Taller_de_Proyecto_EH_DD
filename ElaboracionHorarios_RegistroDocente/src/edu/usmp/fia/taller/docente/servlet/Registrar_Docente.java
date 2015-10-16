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
import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.dao.DAOFactory;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.DAOFactoryRegDocente;

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
	
	@HttpMethod(HttpMethodType.POST)
	@RequireLogin(true)
	public void guardarDocente() throws Exception {
		Docente docente=null;
		try {
			docente=new Docente();
		//	SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd");

			String url_foto=request.getParameter("urlfoto");
			String nombre=request.getParameter("nombres");
			String apellido_paterno=request.getParameter("apellidopaterno");
			String apellido_materno=request.getParameter("apellidomaterno");
			char sexo=request.getParameter("sexo").charAt(0);
			char estado_civil=request.getParameter("estadocivil").charAt(0);
			String fecha_nacimiento=request.getParameter("fechanacimiento");
			int id_Pais_nacionalidad=Integer.parseInt(request.getParameter("pais"));
			int id_Departamento_nacionalidad=Integer.parseInt(request.getParameter("departamento1"));
			int id_Provincia_nacionalidad=Integer.parseInt(request.getParameter("provincia1"));
			int id_Distrito_nacionalidad=Integer.parseInt(request.getParameter("distrito1"));
			int id_Departamento_direccion=Integer.parseInt(request.getParameter("departamento2"));
			int id_Provincia_direccion=Integer.parseInt(request.getParameter("provincia2"));
			int id_Distrito_direccion=Integer.parseInt(request.getParameter("distrito2"));
			String referencia_direccion=request.getParameter("referencia");
			char estado='1';
			//int telefono=Integer.parseInt(request.getParameter("telefono"));
			/*String correo=request.getParameter("email");
			String tipo_documento=request.getParameter("tipodoc");
			String grado_academico=request.getParameter("gradoacademico");
			String profecion=request.getParameter("profecion");
			String especialidad=request.getParameter("especialidad");
			String institucion=request.getParameter("institucion");
			String fecha_ingreso=request.getParameter("fechaingreso");
			docente.setTelefono(telefono);*/


			docente.setUrl_foto(url_foto);
			docente.setNombre(nombre);
			docente.setApellido_materno(apellido_materno);
			docente.setApellido_paterno(apellido_paterno);
			docente.setSexo(sexo);
			docente.setEstado_civil(estado_civil);
			docente.setFecha_nacimiento(fecha_nacimiento);
			docente.setId_Pais_nacionalidad(id_Pais_nacionalidad);
			docente.setId_Departamento_nacionalidad(id_Departamento_nacionalidad);
			docente.setId_Provincia_nacionalidad(id_Provincia_nacionalidad);
			docente.setId_Distrito_nacionalidad(id_Distrito_nacionalidad);
			docente.setId_Departamento_direccion(id_Departamento_direccion);
			docente.setId_Provincia_direccion(id_Provincia_direccion);
			docente.setId_Distrito_direccion(id_Distrito_direccion);
			docente.setReferencia_direccion(referencia_direccion);
			docente.setEstado(estado);
			
			/*docente.setReferencia_direccion(referencia_direccion);
			docente.setCorreo(correo);
			docente.setTipo_documento(tipo_documento);
			docente.setGrado_academico(grado_academico);
			docente.setProfecion(profecion);
			docente.setEspecialidad(especialidad);
			docente.setInstitucion(institucion);
			docente.setFecha_ingreso(fecha_ingreso);*/
			
			DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			
			DAOFactoryRegDocente regdoce = dao.getRegistroDocente();
			String mensaje = regdoce.guardarDocente(docente);
			
			request.setAttribute("mensaje", mensaje);

			System.out.print("HOLA 2"+mensaje);
			request.getRequestDispatcher("/RegistroDocente/mensaje.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
	}
	

}
