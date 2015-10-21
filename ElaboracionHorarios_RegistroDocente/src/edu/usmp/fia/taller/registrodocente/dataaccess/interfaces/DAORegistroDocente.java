package edu.usmp.fia.taller.registrodocente.dataaccess.interfaces;

import java.util.List;
import java.util.Vector;

import edu.usmp.fia.taller.common.bean.Dia;
import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.bean.Email;
import edu.usmp.fia.taller.common.bean.Hora;
import edu.usmp.fia.taller.common.bean.Persona;
import edu.usmp.fia.taller.common.bean.Telefono;
import edu.usmp.fia.taller.common.bean.Ubigeo;

public interface DAORegistroDocente {
	public List<Docente> getDocentes() throws Exception;
	public boolean guardarDocente(Docente docente) throws Exception;
	public List<Ubigeo> getDepartamentos() throws Exception;
	public List<Ubigeo> getProvincias(String coddpto) throws Exception;
	public List<Ubigeo> getDistritos(String coddpto,String codprov) throws Exception;
	public Vector<Dia> listarDias()throws Exception;
	public Vector<Hora> listarHoras()throws Exception;
	
	//INICIO metodos de email
	public boolean guardarEmail(Email emal) throws Exception;
	public boolean guardarEmails(String json_email,String id_profesor) throws Exception;
	//FIN metodos de email
	
	
	
//----------------------------INICIO metodo de persona
	public int guardarPersona(Persona persona) throws Exception;
//----------------------------FIN metodo de persona
	
	
	
	
//----------------------------INICIO metodo de telefono
	public boolean guardarTelefono(Telefono telefono) throws Exception;
	public boolean guardarTelefonos(String json_telefonos,String id_profesor) throws Exception;
//----------------------------FIN metodo de telefono
}
