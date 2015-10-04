package edu.usmp.fia.taller.common.dao.modules.registro.docente;

import java.util.List;

import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.bean.Ubigeo;

public interface DAOFactoryRegDocente {

	public List<Docente> getDocentes() throws Exception;
	public String guardarDocente(Docente docente) throws Exception;
	public List<Ubigeo> getDepartamentos() throws Exception;
	public List<Ubigeo> getProvincias(String coddpto) throws Exception;
	public List<Ubigeo> getDistritos(String coddpto,String codprov) throws Exception;
}