package edu.usmp.fia.taller.common.dao.modules.persona;

import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.usmp.fia.taller.common.bean.Persona;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.DAOFactoryRegDocente;

public class MySqlFactoryPersona  extends MySqlDAOFactory implements DAOFactoryPersona {

	@Override
	public int guardarPersona(Persona persona) throws Exception {
		try {
			Connection conexion = (Connection) getConnection();
			Statement stmt = conexion.createStatement();
			int id_persona=generarIdProfesor();

			String sqlPersona = "insert into persona (id_persona,nombre_1, ape_paterno, ape_materno, id_user)"
					+ "values('"+id_persona+"','"+persona.getNombre1()+"','"+persona.getApePaterno()
					+"','"+persona.getApePaterno()+"',null)";

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

}
