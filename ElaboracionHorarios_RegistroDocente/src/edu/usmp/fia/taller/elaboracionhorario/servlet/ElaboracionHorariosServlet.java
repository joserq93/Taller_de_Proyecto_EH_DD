package edu.usmp.fia.taller.elaboracionhorario.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.MySQLConnection;

import edu.usmp.fia.taller.common.action.ActionServlet;
import edu.usmp.fia.taller.common.action.HttpMethod;
import edu.usmp.fia.taller.common.action.HttpMethodType;
import edu.usmp.fia.taller.common.action.RequireLogin;
import edu.usmp.fia.taller.common.bean.HorariosBean;
import edu.usmp.fia.taller.common.dao.DAOFactory;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;
import edu.usmp.fia.taller.common.util.ThreadUtil;


@WebServlet("/ElaboracionHorariosServlet")
public class ElaboracionHorariosServlet extends  ActionServlet  {

	@HttpMethod(HttpMethodType.GET)
	@RequireLogin(true)
	public void leerHorario() throws Exception {
		System.out.println("leerhorario");
		PrintWriter out = response.getWriter();
		try {
			String version = request.getParameter("version");
			String newVersion = request.getParameter("newVersion");
			String borrarVersion = request.getParameter("borrarVersion");
			System.out.println("prueba; "+"version: "+version+"  "+" newVersion: "+newVersion+" "+" borrarversion: "+ borrarVersion);
			System.out.println(version);
			HttpSession sesion = request.getSession();
			if(borrarVersion != null)
			{
				sesion.removeAttribute("versionHorario");
			}
			
			if(version != null)
			{
				sesion.setAttribute("versionHorario", version);
			}
			String versionHorario = (String)sesion.getAttribute("versionHorario");
			
			if(versionHorario == null)
			{
				
				String query = "SELECT DISTINCT version FROM t_horario_detalle";
				System.out.println(query);
				Connection con = (Connection) getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				System.out.println("sigo aqui");
				java.util.List<String> versiones = new ArrayList<String>();
				int i = 0;
				while(rs.next())
				{
					versiones.add(rs.getString("version"));
					System.out.println(rs.getString("version"));
					i++;
				}
				close(con);
				request.setAttribute("versiones", versiones );
				getServletContext().getRequestDispatcher("/ElaboracionHorarios/version.jsp").forward(request, response);
			}
			else
			{
				System.out.println("sigo aqui");
				String cycle = request.getParameter("cycle");
				if(cycle == null){ 
					cycle = "0";
				}
				System.out.println("cycle: getprimero "+ cycle);
				String query = 
				"SELECT tt.name,tt.horas,tt.horasTeoria,tt.horasLaboratorio,tt.horasPractica,tt.id,GROUP_CONCAT(profesor) as profesor FROM ("+
					"SELECT "+
						"CONCAT(i.abreviatura,'-', j.nombre) as name, "+
						"(l.horasTeoria+l.horasLaboratorio+l.horasPractica-(SELECT COUNT(*) FROM t_horario_detalle WHERE curso_seccion_id = i.id AND version = '"+versionHorario+"')) as horas, "+
						"(l.horasTeoria) as horasTeoria, "+
						"(l.horasLaboratorio) as horasLaboratorio, "+
						"(l.horasPractica) as horasPractica, "+
						"e.id, "+
						"CONCAT(k.nombre, ' ',k.apellido_paterno,'=',GROUP_CONCAT(DISTINCT CONCAT(g.id,'-',h.id) SEPARATOR '::')) as profesor "+
					"FROM "+
						"t_ciclo a "+
					"INNER JOIN "+
						"t_plan_curricular_detalle b "+
					"ON "+
						"a.id = b.ciclo_id "+
					"INNER JOIN "+
						"t_curso c "+
					"ON "+
						"c.id = b.curso_id "+
					"INNER JOIN "+
						"t_curso_profesor d "+
					"ON "+
						"d.curso_id = c.id "+
					"INNER JOIN "+
						"t_profesor e "+
					"ON "+
						"d.profesor_id = e.id "+
					"INNER JOIN "+
						"t_disponibilidad_profesor f "+
					"ON "+
						"f.profesor_id = e.id "+
					"INNER JOIN "+
						"t_dia g "+
					"ON "+
						"g.id = f.dia_id "+
					"INNER JOIN "+
						"t_hora h "+
					"ON "+
						"h.id = f.hora_id "+
					"INNER JOIN "+
						"t_curso_seccion i "+
					"ON "+
						"i.curso_id = c.id "+
					"INNER JOIN "+
						"t_seccion j "+
					"ON "+
						"j.id = i.seccion_id "+
					"INNER JOIN "+
						"t_persona k "+
					"ON "+
						"k.id = e.id "+
					"INNER JOIN "+
						"t_plan_curricular_detalle l "+
					"ON "+
						"l.curso_id = c.id "+
					"WHERE "+
						"a.nombre = '"+cycle+"' "+
					"GROUP BY CONCAT(i.abreviatura,' - ', j.nombre, ' ', k.apellido_paterno) "+
					" ORDER BY name ASC ) tt GROUP BY tt.name";
				System.out.println("QUERY: "+query);
				Connection con = (Connection) getConnection(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				Map<String, Object> data = new HashMap<String, Object>();
				int i = 0;
				String secciones = "";
				while(rs.next())
				{	
					if(!secciones.equalsIgnoreCase(rs.getString("name")))
					{
						Map<String, Object> seccion = new HashMap<String, Object>();
						
						String nombreSeccion = rs.getString("name");
						String[] nombresSeccion = nombreSeccion.split("-");
						String nombreCurso = nombresSeccion[0];
						String seccionCurso = nombresSeccion[1];
						
						seccion.put("seccion", rs.getString("name"));
						seccion.put("se", seccionCurso);
						seccion.put("name", nombreCurso);
						seccion.put("horas", rs.getString("horas"));
						
						seccion.put("horasTeoria", rs.getString("horasTeoria"));
						seccion.put("horasLaboratorio", rs.getString("horasLaboratorio"));
						seccion.put("horasPractica", rs.getString("horasPractica"));
						
						String[] profes = rs.getString("profesor").split(",");
						Map<String, Object> teachers = new HashMap<String, Object>();
						for(int iProfe = 0; iProfe < profes.length; ++iProfe)
						{
							Map<String, Object> teacher = new HashMap<String, Object>();
							
							Map<String, Object> disponibilidad = new HashMap<String, Object>();
							String[] disponibilidadProfe = profes[iProfe].split("=");
							String[] disPro = disponibilidadProfe[1].split("::");
							
							for(int iDis = 0; iDis < disPro.length; ++iDis)
							{
								disponibilidad.put(String.valueOf(iDis), disPro[iDis]);
							}
							
							
							teacher.put("nombre", disponibilidadProfe[0]);
							teacher.put("disponibilidad", disponibilidad);
							
							teachers.put(String.valueOf(iProfe), teacher);
						}
						seccion.put("profesores", teachers);
						
						data.put(String.valueOf(i), seccion);
						
						secciones = rs.getString("name");
						
						i++;
					}
				}
				close(con);
			    JSONObject json = new JSONObject();
			    json.put("cursos", data);
			  	request.setAttribute("json", json.toString() );
			  	
			  	String query2 = "SELECT "+
			  						"c.dia_id as dia, "+
			  						"c.hora_id as hora, "+
			  						"CONCAT(d.nombre, ' ', d.apellido_paterno) as nombre, "+
			  						"CONCAT(b.abreviatura,' - ',g.nombre) as curso "+
			  					"FROM "+ 
			  						"t_horario_detalle a "+
			  					"INNER JOIN "+
			  						"t_curso_seccion b "+
			  					"ON "+
			  						"a.curso_seccion_id = b.id "+
			  					"INNER JOIN "+
			  						"t_disponibilidad_profesor c "+
			  					"ON "+
			  						"c.id = disponibilidad_profesor_id "+
			  					"INNER JOIN "+
			  						"t_persona d "+
			  					"ON "+
			  						"d.id = c.profesor_id "+
			  					"INNER JOIN "+
			  						"t_plan_curricular_detalle e "+
			  					"ON "+
			  						"e.curso_id = b.curso_id "+
			  					"INNER JOIN "+
			  						"t_ciclo f "+
			  					"ON "+
			  						"f.id = e.ciclo_id "+
			  					"INNER JOIN "+
			  						"t_seccion g "+
			  					"ON "+
			  						"g.id = b.seccion_id "+
			  					"WHERE "+
			  						"f.nombre = '"+cycle+"' AND a.version = '"+sesion.getAttribute("versionHorario")+"'";
			  	//System.out.print(query2);
			  	System.out.println("QUERY2: "+query2);
			  	Connection con2 = (Connection) getConnection();
				Statement stmt2 = con2.createStatement();
				ResultSet rs2 = stmt2.executeQuery(query2);
				
				Map<String, Object> dataHorasResult = new HashMap<String, Object>();
				int i2 = 0;
				while(rs2.next())
				{
					Map<String, Object> dataHoras = new HashMap<String, Object>();
					dataHoras.put("dia", rs2.getString("dia"));
					dataHoras.put("hora", rs2.getString("hora"));
					dataHoras.put("nombre", rs2.getString("nombre"));
					dataHoras.put("curso", rs2.getString("curso"));
					
					dataHorasResult.put(String.valueOf(i2), dataHoras);
					
					i2++;
				}
				close(con);
				JSONObject json2 = new JSONObject();
				json2.put("horas", dataHorasResult);
			  	request.setAttribute("horas", json2.toString() );
			  	
			  	request.setAttribute("cycler", cycle);
			  	request.setAttribute("version", versionHorario);
			  	getServletContext().getRequestDispatcher("/ElaboracionHorarios/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			out.print(e.getMessage());
		}
	}
	


	@HttpMethod(HttpMethodType.GET)
	@RequireLogin(true)
	public void cargarHorario() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hola cargar horario");
				PrintWriter out = response.getWriter();
				try
				{
					String cycle = request.getParameter("cycle");
					System.out.println("ciclo: "+cycle);
					HttpSession sesion = request.getSession();
					String versionHorario = (String)sesion.getAttribute("versionHorario");
					System.out.println("versionHorario: "+versionHorario);
					String jsonHoras = request.getParameter("horas-horario");
					System.out.println("jsonHoras: "+jsonHoras);
					JSONArray jsonObject = new JSONArray(jsonHoras);
					
					Connection con3= (Connection) getConnection(); 
					Statement stmt3 = con3.createStatement();
					
					String queryDelete = "DELETE FROM t_horario_detalle WHERE curso_seccion_id IN (SELECT b.id FROM t_plan_curricular_detalle a INNER JOIN t_curso_seccion b ON a.curso_id = b.curso_id INNER JOIN t_ciclo c ON c.id = a.ciclo_id WHERE c.nombre = '"+cycle+"') AND version = '"+versionHorario+"'";
					System.out.print(queryDelete);
					stmt3.executeUpdate(queryDelete);
					System.out.println("siso despues del delete");
					String query = "";
					for (int i = 0; i < jsonObject.length(); i++){
					    String datos = (String)jsonObject.get(i);//nombre+'=='+profesoor+'=='+$(this).attr("hora-data");
					    String[] arrayDatos = datos.split("==");
					    String[] arrayHoras = arrayDatos[2].split("-");
					    
					    String buscarCursoSeccion = "SELECT a.id as id FROM t_curso_seccion a INNER JOIN t_seccion b ON a.seccion_id = b.id WHERE CONCAT(a.abreviatura,' - ',b.nombre) = '"+arrayDatos[0]+"'";
						System.out.println("buscarCursoSeccion: "+buscarCursoSeccion);
					    ResultSet rs3 = stmt3.executeQuery(buscarCursoSeccion);
						String idSeccion = "";
						while(rs3.next())
						{
							idSeccion = rs3.getString("id");
						}
						close(con3);
						
						String buscarDisponibilidadProfesor = "SELECT a.id as id FROM t_disponibilidad_profesor a INNER JOIN t_persona b ON a.profesor_id = b.id WHERE CONCAT(b.nombre,' ',b.apellido_paterno) = '"+arrayDatos[1]+"' AND dia_id = '"+arrayHoras[0]+"' AND hora_id = '"+arrayHoras[1]+"'";
						System.out.println(buscarDisponibilidadProfesor);
						ResultSet rs2 = stmt3.executeQuery(buscarDisponibilidadProfesor);
						String idDisponibilidadProfesor = "";
						while(rs2.next())
						{
							idDisponibilidadProfesor = rs2.getString("id");
						}
					    
					    query = "INSERT INTO t_horario_detalle (id, curso_seccion_id, disponibilidad_aula_id, disponibilidad_profesor_id, version) VALUES (1, '"+idSeccion+"', '68', '"+idDisponibilidadProfesor+"', '"+versionHorario+"')";
					    System.out.println(query);
					    stmt3.executeUpdate(query);
					}
					System.out.println("CYCLE final: "+cycle);
					response.sendRedirect("?f=leerHorario&cycle="+cycle);
				} catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}
	}
	
	private Connection connection = null;
	private Connection getConnection() {
		
		if(connection==null){
			try {
				String userName = null;
				String password = null;
				///String url = "jdbc:mysql://localhost:3306/bd_taller_proyectos";
				String url = "jdbc:mysql://localhost:3306/bd_taller_proyectos";
				Class.forName ("com.mysql.jdbc.Driver").newInstance();
				
				switch (ThreadUtil.getCallerModule()) {
				case 0:
					userName = "root";
					password = "root";
					break;
				case 1:
					userName = "seguimiento";
					password = "seguimiento";
					break;
				case 2:
					userName = "ejemplo";
					password = "ejemplo";
					break;
				default:
					break;
				}
				

				connection = DriverManager.getConnection (url, userName, password);
				System.out.println ("Database connection established");
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;

		
	}
	
	public void close(Object o) {
		if(o == null)
			return;
		try {
			if (o.getClass() == PreparedStatement.class) {
				((PreparedStatement)o).clearParameters();
				((PreparedStatement)o).close();
			} else if (o.getClass() == Connection.class) {
				((Connection)o).close();
			} else if (o.getClass() == ResultSet.class) {
				((ResultSet)o).close();
			} else if (o.getClass() == CallableStatement.class) {
				((CallableStatement) o).clearParameters();
				((CallableStatement) o).close();
			}
		} catch (Exception ex) { 
		} finally {
			o = null;
		}
	}
	
}
