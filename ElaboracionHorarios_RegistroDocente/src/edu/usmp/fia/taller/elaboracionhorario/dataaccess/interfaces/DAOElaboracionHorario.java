package edu.usmp.fia.taller.elaboracionhorario.dataaccess.interfaces;

import java.util.List;

import edu.usmp.fia.taller.common.bean.*;


public interface DAOElaboracionHorario {
	
	public List<Curso> getCursoAll () throws Exception; // codCurso, nombreCursoIndivual, nombreCursoGeneeral
	public List<Docente> getProfesoresAll(); //nombreProfesor

}
