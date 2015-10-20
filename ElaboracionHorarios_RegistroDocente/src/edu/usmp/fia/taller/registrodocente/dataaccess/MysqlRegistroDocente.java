package edu.usmp.fia.taller.registrodocente.dataaccess;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.usmp.fia.taller.common.bean.Dia;
import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.bean.Documento;
import edu.usmp.fia.taller.common.bean.Email;
import edu.usmp.fia.taller.common.bean.GradoAcademico;
import edu.usmp.fia.taller.common.bean.Hora;
import edu.usmp.fia.taller.common.bean.Persona;
import edu.usmp.fia.taller.common.bean.Telefono;
import edu.usmp.fia.taller.common.bean.Ubigeo;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;
import edu.usmp.fia.taller.registrodocente.dataaccess.interfaces.DAORegistroDocente;;

public class MysqlRegistroDocente extends MySqlDAOFactory implements DAORegistroDocente{

	
	
	@Override
	public List<Docente> getDocentes() throws Exception {
		// TODO Auto-generated method stub
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		List<Docente> docentes = new ArrayList<Docente>();
		
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("select id_docente,nombre,apellido_paterno,apellido_materno from docente");
			oRs = oPs.executeQuery();
			Docente docente = null;
				while(oRs.next()){
					docente = new Docente();
					docente.setId_docente(oRs.getInt("id_docente"));
					docente.setNombre(oRs.getString("nombre"));
					docente.setApellido_paterno(oRs.getString("apellido_paterno"));
					docente.setApellido_materno(oRs.getString("apellido_materno"));
					docentes.add(docente);
				}
			
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return docentes;
	}
	
	@Override
	public List<Ubigeo> getDepartamentos() throws Exception {
		// TODO Auto-generated method stub
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
		Ubigeo ubigeo=null;
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("select * from ubigeo where codprov='00' and coddist='00' order by nombre");
			oRs = oPs.executeQuery();
				while(oRs.next()){
					ubigeo = new Ubigeo();
					ubigeo.setCoddpto(oRs.getString("coddpto"));
					ubigeo.setNombre(oRs.getString("nombre"));
					ubigeos.add(ubigeo);
				}
			
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return ubigeos;
	}

	@Override
	public boolean guardarDocente(Docente docente) throws Exception {
		String resultado="";
		
		try {
			Connection conexion = (Connection) getConnection();
			Statement stmt = conexion.createStatement();
			//INSERT INTO  VALUES (NULL, 'adsadasd', 'asdasd', 'asdasd', 'asdasd', '1');
			
			
			//(id_profesor ,id_Pais_nacionalidad ,id_Departamento_nacionalidad ,id_Provincia_nacionalidad ,id_Distrito_nacionalidad ,id_Departamento_direccion ,id_Provincia_direccion ,id_Distrito_direccion ,url_foto ,estado ,estado_civil ,fecha_nacimiento ,referencia_direccion)
			String consulta = "insert into t_profesor(id,id_Pais_nacionalidad,"
					+ "id_Departamento_nacionalidad,id_Provincia_nacionalidad,id_Distrito_nacionalidad,"
					+ "id_Departamento_direccion,id_Provincia_direccion,id_Distrito_direccion,"
					+ "url_foto,estado,estado_civil,"
					+ "fecha_nacimiento,referencia_direccion)"
					+ "values("+docente.getId_docente()+",'"+docente.getId_Pais_nacionalidad()
					+"','"+docente.getId_Departamento_nacionalidad()+"','"+docente.getId_Provincia_nacionalidad()
					+"','"+docente.getId_Distrito_nacionalidad()+"','"+docente.getId_Departamento_direccion()
					+"','"+docente.getId_Provincia_direccion()+"','"+docente.getId_Distrito_direccion()
					+"','"+docente.getUrl_foto()+"','"+docente.getEstado()
					+"','"+docente.getEstado_civil()+"','"+docente.getFecha_nacimiento()+"','"+docente.getReferencia_direccion()+"')";

			
			int filas=stmt.executeUpdate(consulta,Statement.RETURN_GENERATED_KEYS);
			
			return true;
			
			
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return false;
	}
	
	
	public List<Ubigeo> getProvincias(String coddpto) throws Exception {
		// TODO Auto-generated method stub
				Connection oCn = null;
				PreparedStatement oPs = null;
				ResultSet oRs = null;
				List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
				Ubigeo ubigeo=null;
				try {
					oCn = (Connection) getConnection(); 
					oPs = (PreparedStatement) oCn.prepareStatement("select * from ubigeo where coddpto='"+coddpto+"' and coddist='00' and codprov<>'00' order by nombre");
					oRs = oPs.executeQuery();
						while(oRs.next()){
							ubigeo = new Ubigeo();
							ubigeo.setCodprov(oRs.getString("codprov"));
							ubigeo.setNombre(oRs.getString("nombre"));
							ubigeos.add(ubigeo);
						}
					
				} finally {
					close(oRs);
					close(oPs);
					close(oCn);
				}
				return ubigeos;
	}

	@Override
	public List<Ubigeo> getDistritos(String coddpto,String codprov) throws Exception {
		// TODO Auto-generated method stub
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
		Ubigeo ubigeo=null;
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("select * from ubigeo where codprov='"+codprov+"' and coddpto='"+coddpto+"' and coddist<>'00' order by nombre");
			oRs = oPs.executeQuery();
				while(oRs.next()){
					ubigeo = new Ubigeo();
					ubigeo.setCoddist(oRs.getString("coddist"));
					ubigeo.setNombre(oRs.getString("nombre"));
					ubigeos.add(ubigeo);
				}
			
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return ubigeos;
	}
	
	
	@Override
	public Vector<Dia> listarDias() throws Exception {
		Dia dia=null;
		Vector<Dia> dias=null;
		try {
			Connection conexion = (Connection) getConnection();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select id, nombre  from t_dia");
			
			
			
			dias=new Vector<Dia>();
			
			while(rs.next()){
				dia=new Dia();
				dia.setId(rs.getInt("id"));
				dia.setNombre(rs.getString("nombre"));
				dias.addElement(dia);
			}
				
			
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		return dias;
	}

	@Override
	public Vector<Hora> listarHoras() throws Exception {
		Hora hora=null;
		Vector<Hora> horas=null;
		try {
			Connection conexion = (Connection) getConnection();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select id, horaInicio,horaFin,turno  from t_hora");
			
			
			
			horas=new Vector<Hora>();
			
			while(rs.next()){
				hora=new Hora();
				hora.setId(rs.getInt("id"));
				hora.setHorainicio(rs.getString("horaInicio"));
				hora.setHorafin(rs.getString("horaFin"));
				hora.setTurno(rs.getString("turno"));
				horas.addElement(hora);
			}
				
			
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		return horas;
	}
	
//----------------------------INICIO metodo de email
	@Override
	public boolean guardarEmail(Email email) throws Exception {
		Connection conexion = (Connection) getConnection();
		Statement stmt = conexion.createStatement();
		
		try {
			
			//INSERT INTO  VALUES (NULL, 'adsadasd', 'asdasd', 'asdasd', 'asdasd', '1');
			
			
			String consulta = "INSERT INTO t_email_profesor (email,id_profesor) VALUES ('"+email.getEmail()+"','"+email.getIdProfesor()+"');";

			int filas=stmt.executeUpdate(consulta);
			

			return true;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return false;
	}
	
	@Override
	public boolean guardarEmails(String json_emails,String id_profesor) throws Exception {
		Connection conexion = (Connection) getConnection();
		Statement stmt = conexion.createStatement();
		String sqlDelete[]=insertarCamposDinamicosEmail("t_email_profesor",json_emails,"email",id_profesor);
		
		try {
			stmt.executeUpdate(sqlDelete[0]);
			stmt.executeUpdate(sqlDelete[1]);
			
			close(stmt);
			close(conexion);
			return true;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return false;
	}
	
	private String[] insertarCamposDinamicosEmail(String tabla,String data,String campo,String id_profesor){
		String sqls[];
		String delete="";
		String insert="";
		try{
			 JSONArray json2 =(JSONArray) new JSONParser().parse(data.toString());
			 //DELETE FROM t_telefono_profesor WHERE `id_telefono` not in (1,3) and `id_profesor`=1
			 String deleteClausule="";
			 String insertsNuevos="";
			 for(int i =0; i<json2.size();i++){
				 JSONObject jsonObjet= (JSONObject) json2.get(i);
				 String campoJson=jsonObjet.get("campo").toString();
					 if(i==0)
						 deleteClausule+="'"+campoJson+"'";
					 else
						 deleteClausule+=","+"'"+campoJson+"'";
					 
					 if(jsonObjet.get("id").toString().equals("-1")){
						 String email=jsonObjet.get("campo").toString();
						 if(i==0){
							 insertsNuevos+="('"+id_profesor+"','"+email+"'";
						 }
						 else{
							 insertsNuevos+="),('"+id_profesor+"','"+email+"'";
						 }
					 }
			 }
			 
			 if(!deleteClausule.equals("")){
				 delete = "DELETE FROM "+tabla+" WHERE "+campo+" not in ("+deleteClausule+") and `id_profesor`="+id_profesor;
			 }
			 
			 if(!insertsNuevos.equals("")){
				 insertsNuevos+=")";

				 insert="INSERT INTO t_email_profesor (id_profesor, email) VALUES "+insertsNuevos;
			 }
			  
		}
		catch(ParseException pe){
			    System.out.println(pe);
		}
		sqls = new String[]{delete,insert};
		return sqls;
	}
//----------------------------FIN metodo de email
	
	
	
	
//----------------------------INICIO metodo de persona
	private int obtenerUltiId()throws Exception{
		Connection conexion = (Connection) getConnection();
		Statement stmt = conexion.createStatement();
		ResultSet rs=stmt.executeQuery("select id from t_persona  where id like '201%' ORDER BY id DESC");
		int i=0;
		if(rs.next()){
			i=Integer.parseInt(rs.getString("id"));
		}
		
		return i+1;
	}
	
	@Override
	public int guardarPersona(Persona persona) throws Exception {
		int id_persona=obtenerUltiId();
		System.out.print("---"+id_persona+"codigo persona");
		
		try {
			Connection conexion = (Connection) getConnection();
			Statement stmt = conexion.createStatement();
		//	int id_persona=generarIdProfesor();

			String sqlPersona = "insert into t_persona (id, nombre, apellido_paterno, apellido_materno, sexo)"
					+ "values('"+id_persona+"','"+persona.getNombre1()+"','"+persona.getApePaterno()
					+"','"+persona.getApeMaterno()+"','"+persona.getSexo()+"')";

			//stmt.executeUpdate(sqlPersona,Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate(sqlPersona);
			return id_persona;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return -1;
	}

	private int generarIdProfesor() throws Exception{

		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		int nuevoId=-1;
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("SELECT max(id_persona) FROM persona");
			oRs = oPs.executeQuery();
				while(oRs.next()){
					nuevoId=Integer.parseInt(oRs.getString(1))+1;
				}
			
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return nuevoId;
	}
//----------------------------FIN metodo de persona
	
	
	
	
	
//----------------------------INICIO metodo de telefono
	@Override
	public boolean guardarTelefono(Telefono telefono) throws Exception {
		Connection conexion = (Connection) getConnection();
		Statement stmt = conexion.createStatement();
		
		try {
			
			//INSERT INTO  VALUES (NULL, 'adsadasd', 'asdasd', 'asdasd', 'asdasd', '1');
			
			
			String consulta = "INSERT INTO t_telefono_profesor (telefono,id_profesor) VALUES ('"+telefono.getTelefono()+"','"+telefono.getId_profesor()+"');";

			int filas=stmt.executeUpdate(consulta);
			

			return true;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return false;
	}
	
	@Override
	public boolean guardarTelefonos(String json_telefonos,String id_profesor) throws Exception {
		Connection conexion = (Connection) getConnection();
		Statement stmt = conexion.createStatement();
		String sqlDelete[]=insertarCamposDinamicosTelefono("t_telefono_profesor",json_telefonos,"telefono",id_profesor);
		
		try {
			stmt.executeUpdate(sqlDelete[0]);
			stmt.executeUpdate(sqlDelete[1]);
			
			close(stmt);
			close(conexion);

			return true;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return false;
	}
	
	private String[] insertarCamposDinamicosTelefono(String tabla,String data,String campo,String id_profesor){
		String sqls[];
		String delete="";
		String insert="";
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
						 String numeroTelef=jsonObjet.get("campo").toString();
						 if(i==0){
							 insertsNuevos+="('"+id_profesor+"','"+numeroTelef+"'";
						 }
						 else{
							 insertsNuevos+="),('"+id_profesor+"','"+numeroTelef+"'";
						 }
					 }
			 }
			 
			 if(!deleteClausule.equals("")){
				 delete = "DELETE FROM "+tabla+" WHERE "+campo+" not in ("+deleteClausule+") and `id_profesor`="+id_profesor;
			 }
			 
			 if(!insertsNuevos.equals("")){
				 insertsNuevos+=")";
				 insert="INSERT INTO t_telefono_profesor (id_profesor, telefono) VALUES "+insertsNuevos;
			 }
			  
		}
		catch(ParseException pe){
			    System.out.println(pe);
		}
		sqls = new String[]{delete,insert};
		return sqls;
	}
//----------------------------FIN metodo de telefono
	
	private String[] insertarCamposDinamicosDocumento(String tabla,String data,String id_profesor){
		String sqls[];
		String delete="";
		String insert="";
		try{
			 JSONArray json2 =(JSONArray) new JSONParser().parse(data.toString());
			 //DELETE FROM t_telefono_profesor WHERE `id_telefono` not in (1,3) and `id_profesor`=1
			 String deleteClausule="";
			 String insertsNuevos="";
			 for(int i =0; i<json2.size();i++){
				 JSONObject jsonObjet= (JSONObject) json2.get(i);
				 String numDoc=jsonObjet.get("campo").toString();
					 if(i==0)
						 deleteClausule+="'"+numDoc+"'";
					 else
						 deleteClausule+=","+"'"+numDoc+"'";
					 
					 if(jsonObjet.get("id").toString().equals("-1")){
						 String tipodoc=jsonObjet.get("tipodoc").toString();
						 String numDoc2=jsonObjet.get("campo").toString();
						 if(i==0){
							 insertsNuevos+="('"+id_profesor+"','"+numDoc2+"','"+tipodoc+"'";
						 }
						 else{
							 insertsNuevos+="),('"+id_profesor+"','"+numDoc2+"','"+tipodoc+"'";
						 }
					 }
			 }
			 
			 if(!deleteClausule.equals("")){
				 delete = "DELETE FROM "+tabla+" WHERE numero not in ("+deleteClausule+") and `id_profesor`="+id_profesor;
			 }
			 
			 if(!insertsNuevos.equals("")){
				 insertsNuevos+=")";

				 insert="INSERT INTO t_documento_profesor (id_profesor, numero, tipo) VALUES "+insertsNuevos;
			 }
			  
		}
		catch(ParseException pe){
			    System.out.println(pe);
		}
		sqls = new String[]{delete,insert};
		return sqls;
	}
//----------------------------FIN metodo de Documento

	private String[] insertarCamposDinamicosGrado(String tabla,String data,String id_profesor){
		String sqls[];
		String delete="";
		String insert="";
		try{
			 JSONArray json2 =(JSONArray) new JSONParser().parse(data.toString());
			 //DELETE FROM t_telefono_profesor WHERE `id_telefono` not in (1,3) and `id_profesor`=1
			 String deleteClausule="";
			 String insertsNuevos="";
			 for(int i =0; i<json2.size();i++){
				 JSONObject jsonObjet= (JSONObject) json2.get(i);
				 String numDocId=jsonObjet.get("id").toString();
				 String especialidad=jsonObjet.get("especialidad").toString();
				 String gradoAcademico=jsonObjet.get("gradoAcademico").toString();
				 String profesion=jsonObjet.get("profesion").toString();
				 String institucion=jsonObjet.get("institucion").toString();
				 String fechaIngreso=jsonObjet.get("fechaIngreso").toString();
				 if(!numDocId.equals("-1")){
					 if(i==0){
						deleteClausule+="'"+numDocId+"'";
					 }
					 else{
						deleteClausule+=","+"'"+numDocId+"'";
					 }
				 }else{
					
					 if(i==0){
						deleteClausule+="'"+numDocId+"'";
						insertsNuevos+="('"+id_profesor+"','"+especialidad+"','"+gradoAcademico+"','"+profesion+"','"+institucion+"','"+fechaIngreso+"'";
					 }
					 else{
						deleteClausule+=","+"'"+numDocId+"'";
						insertsNuevos+="),('"+id_profesor+"','"+especialidad+"','"+gradoAcademico+"','"+profesion+"','"+institucion+"','"+fechaIngreso+"'";
					 }
				}
			 }
			 
			 if(!deleteClausule.equals("")){
				 delete = "DELETE FROM "+tabla+" WHERE id_grado_academico not in ("+deleteClausule+") and `id_profesor`="+id_profesor;
			 }
			 
			 if(!insertsNuevos.equals("")){
				 insertsNuevos+=")";

				 insert="INSERT INTO t_grado_academico_profesor (id_profesor, especialidad, grado, profesion, nombre_institucion, fecha_ingreso) VALUES "+insertsNuevos;
			 }
			  
		}
		catch(ParseException pe){
			    System.out.println(pe);
		}
		sqls = new String[]{delete,insert};
		return sqls;
	}
//----------------------------FIN metodo de grado academico

	@Override
	public boolean guardarDocumento(Documento documento) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean guardarDocumentos(String json_documentos, String id_profesor) throws Exception {
		Connection conexion = (Connection) getConnection();
		Statement stmt = conexion.createStatement();
		String sqlDelete[]=insertarCamposDinamicosDocumento("t_documento_profesor",json_documentos,id_profesor);

		try {
			stmt.executeUpdate(sqlDelete[0]);
			stmt.executeUpdate(sqlDelete[1]);
			
			close(stmt);
			close(conexion);

			return true;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean guardarGradoAcademico(GradoAcademico gradoAcademico)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean guardarGradosAcademicos(String json_gradoAcademico, String id_profesor) throws Exception {
		Connection conexion = (Connection) getConnection();
		Statement stmt = conexion.createStatement();
		String sqlDelete[]=insertarCamposDinamicosGrado("t_grado_academico_profesor",json_gradoAcademico,id_profesor);

		try {
			stmt.executeUpdate(sqlDelete[0]);
			stmt.executeUpdate(sqlDelete[1]);
			
			close(stmt);
			close(conexion);

			return true;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		return false;
	}
}
