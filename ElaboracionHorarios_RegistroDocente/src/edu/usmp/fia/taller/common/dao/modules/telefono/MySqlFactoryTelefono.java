package edu.usmp.fia.taller.common.dao.modules.telefono;

import java.sql.Statement;

import com.mysql.jdbc.Connection;

import edu.usmp.fia.taller.common.bean.Telefono;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.DAOFactoryRegDocente;

public class MySqlFactoryTelefono  extends MySqlDAOFactory implements DAOFactoryTelefono {

	@Override
	public boolean guardarTelefono(Telefono telefono) throws Exception {
		String resultado;
		try {
			Connection conexion = (Connection) getConnection();
			Statement stmt = conexion.createStatement();
			//INSERT INTO  VALUES (NULL, 'adsadasd', 'asdasd', 'asdasd', 'asdasd', '1');
			
			
			String consulta = "INSERT INTO t_telefono_profesor (telefono,id_profesor) VALUES ('"+telefono.getTelefono()+"','"+telefono.getId_profesor()+"');";

			int filas=stmt.executeUpdate(consulta);
			

			return true;
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return false;
	}



}
