package edu.usmp.fia.taller.common.dao.modules.email;

import edu.usmp.fia.taller.common.bean.Email;

public interface DAOFactoryEmail {

	
	public boolean guardarEmail(Email emal) throws Exception;
	public boolean guardarEmails(String json_email,String id_profesor) throws Exception;
}
