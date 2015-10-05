package edu.usmp.fia.taller.elaboracionhorario.dataaccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.usmp.fia.taller.common.bean.Curso;
import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;
import edu.usmp.fia.taller.elaboracionhorario.dataaccess.interfaces.DAOElaboracionHorario;

public class MysqlElaboracionHorarios extends MySqlDAOFactory implements DAOElaboracionHorario {

	@Override
	public List<Curso> getCursoAll() throws Exception {
		// TODO Auto-generated method stub
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		Curso objCurso = null;
		List <Curso> listCurso= new ArrayList<Curso>();
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("SELECT * FROM t_curso");
			oRs = oPs.executeQuery();
			while(oRs.next()) {
				objCurso = new Curso();
				objCurso.setIdCurso(oRs.getInt("id"));
				objCurso.setNombreCurso(oRs.getString("nombre"));
				listCurso.add(objCurso);
			}
			close(oRs);

		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return listCurso;

	}

	@Override
	public List<Docente> getProfesoresAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
