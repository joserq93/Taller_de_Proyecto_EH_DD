package edu.usmp.fia.taller.common.dao.modules.registro.docente;

import java.util.List;

import edu.usmp.fia.taller.common.bean.Docente;

public interface DAOFactoryRegDocente {

	public List<Docente> getDocentes() throws Exception;
	public String guardarDocente(Docente docente) throws Exception;
	
}