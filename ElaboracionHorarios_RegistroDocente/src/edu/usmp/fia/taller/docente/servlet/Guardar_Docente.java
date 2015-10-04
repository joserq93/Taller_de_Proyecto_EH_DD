package edu.usmp.fia.taller.docente.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import edu.usmp.fia.taller.common.dao.modules.registro.docente.MysqlFactoryRegDocente;


@WebServlet("/Guardar_Docente")
public class Guardar_Docente extends ActionServlet {
	@HttpMethod(HttpMethodType.POST)
	@RequireLogin(true)
	public void GuardarDocente() throws Exception {
		Docente docente=null;

		try {
			docente=new Docente();
			SimpleDateFormat forma=new SimpleDateFormat("dd/MM/yyyy");
			System.out.print("HOLA 1\n");

			System.out.print('-'+request.getParameter("codigo"));
			int id_docente=Integer.parseInt(request.getParameter("codigo"));
			System.out.print("HOLA 1\n");
			int id_Pais_nacionalidad=Integer.parseInt(request.getParameter("pais"));
			System.out.print("HOLA 1\n");
			int id_Departamento_nacionalidad=Integer.parseInt(request.getParameter("departamento1"));
			System.out.print("HOLA 1\n");
			int id_Provincia_nacionalidad=Integer.parseInt(request.getParameter("provincia1"));
			System.out.print("HOLA 1\n");
			int id_Distrito_nacionalidad=Integer.parseInt(request.getParameter("distrito1"));
			System.out.print("HOLA 1\n");
			int id_Departamento_direccion=Integer.parseInt(request.getParameter("departamento2"));
			System.out.print("HOLA 1\n");
			int id_Provincia_direccion=Integer.parseInt(request.getParameter("provincia2"));
			System.out.print("HOLA 1\n");
			int id_Distrito_direccion=Integer.parseInt(request.getParameter("distrito2"));
			System.out.print("HOLA 1\n");
			String nombre=request.getParameter("nombres");
			System.out.print("HOLA 1\n");
			String apellido_materno=request.getParameter("apellidomaterno");
			System.out.print("HOLA 1\n");
			String apellido_paterno=request.getParameter("apellidopaterno");
			System.out.print("HOLA 1\n");
			String url_foto=request.getParameter("urlfoto");
			System.out.print("HOLA 1\n");
			int telefono=Integer.parseInt(request.getParameter("telefono"));
			System.out.print("HOLA 1\n");
			String correo=request.getParameter("email");
			System.out.print("HOLA 1\n");
			char estado='1';
			char estado_civil=request.getParameter("estadocivil").charAt(0);
			System.out.print("HOLA 1\n");
			char sexo=request.getParameter("sexo").charAt(0);
			System.out.print("HOLA 1.1");
			Date fecha_nacimiento=forma.parse(request.getParameter("fechanacimiento"));
			String referencia_direccion=request.getParameter("referencia");
			String tipo_documento=request.getParameter("tipodoc");
			String grado_academico=request.getParameter("gradoacademico");
			String profecion=request.getParameter("profecion");
			String especialidad=request.getParameter("especialidad");
			String institucion=request.getParameter("institucion");
			System.out.print("HOLA 1.2");
			Date fecha_ingreso=forma.parse(request.getParameter("fechaingreso"));
			System.out.print("HOLA 2");
			
			
			
			
			

			
			docente.setId_docente(id_docente);
			docente.setId_Pais_nacionalidad(id_Pais_nacionalidad);
			docente.setId_Departamento_nacionalidad(id_Departamento_nacionalidad);
			docente.setId_Provincia_nacionalidad(id_Provincia_nacionalidad);
			docente.setId_Distrito_nacionalidad(id_Distrito_nacionalidad);
			docente.setId_Departamento_direccion(id_Departamento_direccion);
			docente.setId_Provincia_direccion(id_Provincia_direccion);
			docente.setId_Distrito_direccion(id_Distrito_direccion);
			docente.setNombre(nombre);
			docente.setApellido_materno(apellido_materno);
			docente.setApellido_paterno(apellido_paterno);
			docente.setUrl_foto(url_foto);
			docente.setTelefono(telefono);
			docente.setCorreo(correo);
			docente.setEstado(estado);
			docente.setEstado_civil(estado_civil);
			docente.setSexo(sexo);
			docente.setFecha_nacimiento(fecha_nacimiento);
			docente.setReferencia_direccion(referencia_direccion);
			docente.setTipo_documento(tipo_documento);
			docente.setGrado_academico(grado_academico);
			docente.setProfecion(profecion);
			docente.setEspecialidad(especialidad);
			docente.setInstitucion(institucion);
			docente.setFecha_ingreso(fecha_ingreso);
			System.out.print("HOLA 2");
			
			DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			
			DAOFactoryRegDocente regdoce = dao.getRegistroDocente();
			System.out.print("HOLA 3");
			String mensaje = regdoce.guardarDocente(docente);
			
			request.setAttribute("mensaje", mensaje);
			System.out.print("HOLA 4");
			
			request.getRequestDispatcher("/RegistroDocente/mensaje.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
	}

}
