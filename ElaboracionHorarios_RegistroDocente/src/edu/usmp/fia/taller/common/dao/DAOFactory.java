package edu.usmp.fia.taller.common.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.usmp.fia.taller.common.dao.modules.DAOFactoryGeneral;
import edu.usmp.fia.taller.common.dao.modules.mallacurricular.DAOFactoryMCurricular;
import edu.usmp.fia.taller.common.dao.modules.registro.docente.DAOFactoryRegDocente;
import edu.usmp.fia.taller.common.dao.modules.elaboracionhorario.DAOFactoryElabHorarios;


public abstract class DAOFactory {

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;
	public static final int ORACLE = 3;

	
	public abstract DAOFactoryGeneral getGeneral();
	public abstract DAOFactoryMCurricular getMallaCurricular();
	public abstract DAOFactoryRegDocente getRegistroDocente();
	public abstract DAOFactoryElabHorarios getElaboracionHorario();
	
	
	public static DAOFactory getDAOFactory(int factory){
		switch (factory) {
			case MYSQL:
				return new MySqlDAOFactory();
			case SQLSERVER:
				//return new SqlDaoFactory();
			case ORACLE:
				//return new OracleDaoFactory();
			default:
				return null;
		}
	}


	public void close(Object o) {
		if(o == null)
			return;
		try {
			if (o.getClass() == PreparedStatement.class) {
				((PreparedStatement)o).clearParameters();
				((PreparedStatement)o).close();
			} else if (o.getClass() == Connection.class) {
				((Connection)o).close();
			} else if (o.getClass() == ResultSet.class) {
				((ResultSet)o).close();
			} else if (o.getClass() == CallableStatement.class) {
				((CallableStatement) o).clearParameters();
				((CallableStatement) o).close();
			}
		} catch (Exception ex) { 
		} finally {
			o = null;
		}
	}
	

	
}
