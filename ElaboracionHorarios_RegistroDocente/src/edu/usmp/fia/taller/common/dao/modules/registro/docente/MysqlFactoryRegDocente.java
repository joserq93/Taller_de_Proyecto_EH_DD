package edu.usmp.fia.taller.common.dao.modules.registro.docente;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.bean.Persona;
import edu.usmp.fia.taller.common.bean.Ubigeo;
import edu.usmp.fia.taller.common.bean.User;
import edu.usmp.fia.taller.common.bean.UserType;
import edu.usmp.fia.taller.common.bean.UsuarioDetalle;
import edu.usmp.fia.taller.common.dao.MySqlDAOFactory;

public class MysqlFactoryRegDocente extends MySqlDAOFactory implements DAOFactoryRegDocente {

	@Override
	public List<Docente> getDocentes() throws Exception {
		// TODO Auto-generated method stub
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		List<Docente> docentes = new ArrayList<Docente>();
		
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("select id_docente,nombre,apellido_paterno,apellido_materno from docente");
			oRs = oPs.executeQuery();
			Docente docente = null;
				while(oRs.next()){
					docente = new Docente();
					docente.setId_docente(oRs.getInt("id_docente"));
					docente.setNombre(oRs.getString("nombre"));
					docente.setApellido_paterno(oRs.getString("apellido_paterno"));
					docente.setApellido_materno(oRs.getString("apellido_materno"));
					docentes.add(docente);
				}
			
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return docentes;
	}
	
	@Override
	public List<Ubigeo> getDepartamentos() throws Exception {
		// TODO Auto-generated method stub
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
		Ubigeo ubigeo=null;
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("select * from ubigeo where codprov='00' and coddist='00' order by nombre");
			oRs = oPs.executeQuery();
				while(oRs.next()){
					ubigeo = new Ubigeo();
					ubigeo.setCoddpto(oRs.getString("coddpto"));
					ubigeo.setNombre(oRs.getString("nombre"));
					ubigeos.add(ubigeo);
				}
			
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return ubigeos;
	}

	@Override
	public List<Ubigeo> getProvincias(String coddpto) throws Exception {
		// TODO Auto-generated method stub
				Connection oCn = null;
				PreparedStatement oPs = null;
				ResultSet oRs = null;
				List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
				Ubigeo ubigeo=null;
				try {
					oCn = (Connection) getConnection(); 
					oPs = (PreparedStatement) oCn.prepareStatement("select * from ubigeo where coddpto='"+coddpto+"' and coddist='00' and codprov<>'00' order by nombre");
					oRs = oPs.executeQuery();
						while(oRs.next()){
							ubigeo = new Ubigeo();
							ubigeo.setCodprov(oRs.getString("codprov"));
							ubigeo.setNombre(oRs.getString("nombre"));
							ubigeos.add(ubigeo);
						}
					
				} finally {
					close(oRs);
					close(oPs);
					close(oCn);
				}
				return ubigeos;
	}

	@Override
	public List<Ubigeo> getDistritos(String coddpto,String codprov) throws Exception {
		// TODO Auto-generated method stub
		Connection oCn = null;
		PreparedStatement oPs = null;
		ResultSet oRs = null;
		List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
		Ubigeo ubigeo=null;
		try {
			oCn = (Connection) getConnection(); 
			oPs = (PreparedStatement) oCn.prepareStatement("select * from ubigeo where codprov='"+codprov+"' and coddpto='"+coddpto+"' and coddist<>'00' order by nombre");
			oRs = oPs.executeQuery();
				while(oRs.next()){
					ubigeo = new Ubigeo();
					ubigeo.setCoddist(oRs.getString("coddist"));
					ubigeo.setNombre(oRs.getString("nombre"));
					ubigeos.add(ubigeo);
				}
			
		} finally {
			close(oRs);
			close(oPs);
			close(oCn);
		}
		return ubigeos;
	}


}
