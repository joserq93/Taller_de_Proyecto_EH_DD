package edu.usmp.fia.taller.common.dao.modules.telefono;

import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.jdbc.Connection;

import edu.usmp.fia.taller.common.bean.Telefono;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.DAOFactoryRegDocente;

public class MySqlFactoryTelefono  extends MySqlDAOFactory implements DAOFactoryTelefono {

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
		String sqlDelete[]=insertarCamposDinamicos("t_telefono_profesor",json_telefonos,"telefono",id_profesor);
		
		try {
			stmt.executeUpdate(sqlDelete[0]);
			//String consulta = "INSERT INTO t_telefono_profesor (telefono,id_profesor) VALUES ('"+telefono.getTelefono()+"','"+telefono.getId_profesor()+"');";

			stmt.executeUpdate(sqlDelete[1]);
			
			close(stmt);
			close(conexion);

			return true;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return false;
	}
	
	private String[] insertarCamposDinamicos(String tabla,String data,String campo,String id_profesor){
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


}
