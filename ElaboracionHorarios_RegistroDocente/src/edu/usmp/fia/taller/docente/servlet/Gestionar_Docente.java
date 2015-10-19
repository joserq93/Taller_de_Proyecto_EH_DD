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
import edu.usmp.fia.taller.common.dao.DAOFactory;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.DAOFactoryRegDocente;

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
		try {
			docente=new Docente();
		//	SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd");

			String json_telefono=request.getParameter("json_telefono");
			String json_email=request.getParameter("json_email");
			String json_documento=request.getParameter("json_documento");
			//String jsonbj = new Gson().toJson(json);
			
			insertarCamposDinamicos("t_telefono_profesor",json_telefono,"telefono","1");
			insertarCamposDinamicos("t_telefono_profesor",json_email,"email","1");
			insertarCamposDinamicos("t_telefono_profesor",json_documento,"documento","1");
			/*
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter(); */
			
			
			String url_foto=request.getParameter("urlfoto");
			String nombre=request.getParameter("nombres");
			String apellido_paterno=request.getParameter("apellidopaterno");
			String apellido_materno=request.getParameter("apellidomaterno");
			String sexo=request.getParameter("sexo");
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


			System.out.print("HOLA 100");
			docente.setUrl_foto(url_foto);
			docente.setNombre(nombre);
			docente.setApellido_materno(apellido_materno);
			docente.setApellido_paterno(apellido_paterno);
			docente.setSexo(sexo);
			docente.setEstado_civil(estado_civil);
			System.out.print("HOLA 0");
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

			DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			DAOFactoryRegDocente regdoce = dao.getRegistroDocente();
			String id_docente = regdoce.guardarDocente(docente);
			dao.insertarCamposDinamicos("t_telefono_profesor",json_telefono,"telefono",id_docente);
			
			request.setAttribute("mensaje", id_docente);

			request.getRequestDispatcher("/RegistroDocente/mensaje.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
	}
	
	private boolean insertarCamposDinamicos(String tabla,String data,String campo,String id_profesor){
		try{
			 JSONArray json2 =(JSONArray) new JSONParser().parse(data.toString());
			 //DELETE FROM t_telefono_profesor WHERE `id_telefono` not in (1,3) and `id_profesor`=1
			 String deleteClausule="";
			 String insertsNuevos="";
			 for(int i =0; i<json2.size();i++){
				 JSONObject jsonObjet= (JSONObject) json2.get(i);
				 String campoJson=jsonObjet.get("campo").toString();
					 if(i==0)
						 deleteClausule+=campoJson;
					 else
						 deleteClausule+=","+campoJson;
					 
					 if(jsonObjet.get("id").toString().equals("-1")){
						 if(i==0)
							 insertsNuevos+=campoJson;
						 else
							 insertsNuevos+=","+campoJson;
					 };
			 }
			 if(!deleteClausule.equals("")){
				 System.out.println("DELETE FROM "+tabla+" WHERE "+campo+" not in ("+deleteClausule+") and `id_profesor`="+id_profesor);
				 System.out.println("insertsNuevos;"+insertsNuevos);
			 }
			  }
			  catch(ParseException pe){
			    System.out.println(pe);
			  }
		
		return true;
	}
	

}
