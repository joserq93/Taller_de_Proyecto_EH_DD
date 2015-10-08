package edu.usmp.fia.taller.elaboracionhorario.dataaccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Statement;
import edu.usmp.fia.taller.common.bean.Curso;
import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.bean.Horarios;
import edu.usmp.fia.taller.common.bean.HorariosBean;
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

	@Override
	public int insertHorarios(HorariosBean objHor) throws Exception {
		// TODO Auto-generated method stub
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		Curso objCurso = null;
		int estado=0;
		int id=0;
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("INSERT INTO t_horario_tmp (ciclo, codCur, codLab, curso, detalleCurso, codSec, numCred, codFac, 01, turno,profesor, aula, escuela, lunes, martes, miercoles, jueves, viernes, sabado, domingo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			
			oPs.setString(1, objHor.getCicest());
			oPs.setString(2, objHor.getCodCur());
			oPs.setString(3, objHor.getCodCurteo());
			oPs.setString(4, objHor.getCurso());
			oPs.setString(5, objHor.getDesRes());
			oPs.setString(6, objHor.getCodSec());
			oPs.setString(7, objHor.getNumCre());
			oPs.setString(8, objHor.getCodFac());
			oPs.setString(9, objHor.getC01());
			oPs.setString(10, objHor.getTur());
			oPs.setString(11, objHor.getProfesor());
			oPs.setString(12, objHor.getAula());
			oPs.setString(13, objHor.getEscual());
			oPs.setString(14, objHor.getLunes());
			oPs.setString(15, objHor.getMartes());
			oPs.setString(16, objHor.getMiercoles());
			oPs.setString(17, objHor.getJueves());
			oPs.setString(18, objHor.getViernes());
			oPs.setString(19, objHor.getSabado());
			oPs.setString(20, objHor.getDomingo());


			estado=oPs.executeUpdate();
			ResultSet rs=oPs.getGeneratedKeys();
			if(rs.next()){
				id=rs.getInt(1);
			} 
			close(oRs);

		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return estado;
	}

}
