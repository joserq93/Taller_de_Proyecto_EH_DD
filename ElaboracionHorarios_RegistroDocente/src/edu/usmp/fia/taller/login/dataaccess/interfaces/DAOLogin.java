package edu.usmp.fia.taller.login.dataaccess.interfaces;

import edu.usmp.fia.taller.common.bean.Persona;
import edu.usmp.fia.taller.common.bean.User;

public interface DAOLogin {

	public Persona getPersonaByUser(String user, String password) throws Exception;
	
}
