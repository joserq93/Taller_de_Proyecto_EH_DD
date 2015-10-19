package edu.usmp.fia.taller.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;








import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.usmp.fia.taller.common.dao.modules.DAOFactoryGeneral;
import edu.usmp.fia.taller.common.dao.modules.MysqlFactoryGeneral;
import edu.usmp.fia.taller.common.dao.modules.elaboracionhorario.DAOFactoryElabHorarios;
import edu.usmp.fia.taller.common.dao.modules.elaboracionhorario.MysqlFactoryElabHorarios;
import edu.usmp.fia.taller.common.dao.modules.mallacurricular.DAOFactoryMCurricular;
import edu.usmp.fia.taller.common.dao.modules.mallacurricular.MySqlFactoryMCurricular;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.DAOFactoryRegDocente;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.MysqlFactoryRegDocente;
import edu.usmp.fia.taller.common.util.ThreadUtil;


public class MySqlDAOFactory extends DAOFactory {

	
	protected static final Logger log = LogManager.getLogger(MySqlDAOFactory.class);

	
	private Connection connection = null;

	
	public Connection getConnection() {
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
	public boolean insertarCamposDinamicos(String tabla,String data,String campo,String id_profesor){
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


	public DAOFactoryGeneral getGeneral() { return new MysqlFactoryGeneral(); }
	public DAOFactoryMCurricular getMallaCurricular() { return new MySqlFactoryMCurricular(); }
	public DAOFactoryRegDocente getRegistroDocente() { return new MysqlFactoryRegDocente(); }
	public DAOFactoryElabHorarios getElaboracionHorario() {return new MysqlFactoryElabHorarios();}
	
}
