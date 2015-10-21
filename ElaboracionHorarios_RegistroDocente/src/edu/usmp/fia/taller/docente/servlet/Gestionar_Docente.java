package edu.usmp.fia.taller.docente.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import edu.usmp.fia.taller.common.action.ActionServlet;
import edu.usmp.fia.taller.common.action.HttpMethod;
import edu.usmp.fia.taller.common.action.HttpMethodType;
import edu.usmp.fia.taller.common.action.RequireLogin;
import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.bean.Persona;
import edu.usmp.fia.taller.common.dao.DAOFactory;
import edu.usmp.fia.taller.common.dao.modules.email.DAOFactoryEmail;
import edu.usmp.fia.taller.common.dao.modules.persona.DAOFactoryPersona;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.DAOFactoryRegDocente;
import edu.usmp.fia.taller.common.dao.modules.telefono.DAOFactoryTelefono;

/**
 * Servlet implementation class Registrar_Docente
 */
@WebServlet("/Gestionar_Docente")
public class Gestionar_Docente extends ActionServlet {
	
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
		Persona persona=null;
		try {
		//	SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd");

			
			//String jsonbj = new Gson().toJson(json);
			
			/*insertarCamposDinamicos("t_telefono_profesor",json_telefono,"telefono","1");
			insertarCamposDinamicos("t_telefono_profesor",json_email,"email","1");
			insertarCamposDinamicos("t_telefono_profesor",json_documento,"documento","1");*/
			/*
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter(); */
			
			
			String url_foto=request.getParameter("urlfoto");
			String nombre=request.getParameter("nombres");
			String apellido_paterno=request.getParameter("apellidopaterno");
			String apellido_materno=request.getParameter("apellidomaterno");
			//String sexo=request.getParameter("sexo");
			//char estado_civil=request.getParameter("estadocivil").charAt(0);
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

			persona=new Persona();

			persona.setNombre1(nombre);
			persona.setApePaterno(apellido_materno);
			persona.setApeMaterno(apellido_paterno);
			
			DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			DAOFactoryPersona regPersona = dao.getRegistroPersona();
			int idPersona = regPersona.guardarPersona(persona);
			
			docente=new Docente();
			docente.setId_docente(idPersona);
			docente.setUrl_foto(url_foto);
			//docente.setSexo(sexo);
			//docente.setEstado_civil(estado_civil);
			//docente.setFecha_nacimiento(fecha_nacimiento);
			docente.setId_Pais_nacionalidad(id_Pais_nacionalidad);
			docente.setId_Departamento_nacionalidad(id_Departamento_nacionalidad);
			docente.setId_Provincia_nacionalidad(id_Provincia_nacionalidad);
			docente.setId_Distrito_nacionalidad(id_Distrito_nacionalidad);
			docente.setId_Departamento_direccion(id_Departamento_direccion);
			docente.setId_Provincia_direccion(id_Provincia_direccion);
			docente.setId_Distrito_direccion(id_Distrito_direccion);
			docente.setFecha_nacimiento(fecha_nacimiento);
			//docente.setReferencia_direccion(referencia_direccion);
			docente.setEstado(estado);
			
			/*docente.setReferencia_direccion(referencia_direccion);
			docente.setCorreo(correo);
			docente.setTipo_documento(tipo_documento);
			docente.setGrado_academico(grado_academico);
			docente.setProfecion(profecion);
			docente.setEspecialidad(especialidad);
			docente.setInstitucion(institucion);
			docente.setFecha_ingreso(fecha_ingreso);*/

			DAOFactoryRegDocente regdoce = dao.getRegistroDocente();
			boolean resultadoExito = regdoce.guardarDocente(docente);
			
			if(resultadoExito){
				String json_telefono=request.getParameter("json_telefono");
				String json_email=request.getParameter("json_email");
				String json_documento=request.getParameter("json_documento");

				DAOFactoryTelefono regTelefono = dao.getRegistroTelefono();
				regTelefono.guardarTelefonos(json_telefono, ""+idPersona);
				DAOFactoryEmail regEmail = dao.getRegistroEmail();
				regEmail.guardarEmails(json_email, ""+idPersona);
			}
			
			request.setAttribute("mensaje", "nada");

			request.getRequestDispatcher("/RegistroDocente/mensaje.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
	}

}
