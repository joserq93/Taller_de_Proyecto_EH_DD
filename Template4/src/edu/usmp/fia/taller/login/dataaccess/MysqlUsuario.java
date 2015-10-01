package edu.usmp.fia.taller.login.dataaccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.usmp.fia.taller.common.bean.Modulo;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;
import edu.usmp.fia.taller.login.dataaccess.interfaces.DAOUsuario;

public class MysqlUsuario extends MySqlDAOFactory implements DAOUsuario {

	
	
	public List<Modulo> getModulosPorUsuario(int idusuario) throws Exception {
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		List<Modulo> oList = new ArrayList<Modulo>();
		try {
			oCn = (Connection) getConnection();
			oPs = (PreparedStatement) oCn.prepareStatement("{call GET_MODULOS_BY_USER(?)}");
			oPs.setInt(1, idusuario);
			oRs = oPs.executeQuery();
			while(oRs.next()) {
				Modulo oModulo = new Modulo();
				oModulo.setIdModulo(oRs.getInt("id_modulo"));
				oModulo.setDescripcion(oRs.getString("descripcion"));
				oList.add(oModulo);
			}
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return oList;
	}

	
}
