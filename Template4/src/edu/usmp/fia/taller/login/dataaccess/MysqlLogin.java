package edu.usmp.fia.taller.login.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.usmp.fia.taller.common.bean.Persona;
import edu.usmp.fia.taller.common.bean.User;
import edu.usmp.fia.taller.common.bean.UserType;
import edu.usmp.fia.taller.common.bean.UsuarioDetalle;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;
import edu.usmp.fia.taller.login.dataaccess.interfaces.DAOLogin;

public class MysqlLogin extends MySqlDAOFactory implements DAOLogin {

	
	@Override
	public Persona getPersonaByUser(String user, String password) throws Exception {
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		Persona oPersona = null;
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("{call GET_USUARIO(?,?)}");
			oPs.setString(1, user);
			oPs.setString(2, password);
			oRs = oPs.executeQuery();
			if(oRs.next()) {
				oPersona = new Persona();
				User oUser = new User();
				oPersona.setNombre1(oRs.getString("nombre_1"));
				oPersona.setNombre2(oRs.getString("nombre_2"));
				oPersona.setApePaterno(oRs.getString("ape_paterno"));
				oPersona.setApeMaterno(oRs.getString("ape_materno"));
				oUser.setIdUser(oRs.getInt("id_user"));
				oUser.setActivate(oRs.getBoolean("is_activate"));
				oPersona.setUsuario(oUser);
			}
			close(oRs);
			if(oPersona!=null && oPs.getMoreResults()) {
				oRs = oPs.getResultSet();
				List<UsuarioDetalle> oListDetalle = new ArrayList<UsuarioDetalle>();
				while(oRs.next()) {
					UsuarioDetalle oUserDetail = new UsuarioDetalle();
					oUserDetail.setUserType( UserType.get(oRs.getInt("id_usuario_tipo")) );
					oListDetalle.add(oUserDetail);
				}
			}
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return oPersona;
	}


}
