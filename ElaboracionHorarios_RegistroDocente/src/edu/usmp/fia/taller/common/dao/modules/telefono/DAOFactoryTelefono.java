package edu.usmp.fia.taller.common.dao.modules.telefono;

import edu.usmp.fia.taller.common.bean.Telefono;

public interface DAOFactoryTelefono {

	
	public boolean guardarTelefono(Telefono telefono) throws Exception;
	public boolean guardarTelefonos(String json_telefonos,String id_profesor) throws Exception;
}
