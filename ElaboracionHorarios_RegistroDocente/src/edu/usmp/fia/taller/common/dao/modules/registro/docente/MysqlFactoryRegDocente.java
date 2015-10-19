package edu.usmp.fia.taller.common.dao.modules.registro.docente;


import java.sql.ResultSet;
import java.sql.Statement;
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
	public String guardarDocente(Docente docente) throws Exception {
		String resultado="";
		try {
			Connection conexion = (Connection) getConnection();
			Statement stmt = conexion.createStatement();
			//INSERT INTO  VALUES (NULL, 'adsadasd', 'asdasd', 'asdasd', 'asdasd', '1');
			
			
			String sqlPersona = "insert into t_persona (id,nombre, apellido_paterno, apellido_materno, sexo,email)"
					+ "values('49','"+docente.getNombre()+"','"+docente.getApellido_paterno()+"','"+docente.getApellido_materno()
					+"','"+docente.getSexo()+"','')";

			int id_persona=stmt.executeUpdate(sqlPersona,Statement.RETURN_GENERATED_KEYS);
			//(id_profesor ,id_Pais_nacionalidad ,id_Departamento_nacionalidad ,id_Provincia_nacionalidad ,id_Distrito_nacionalidad ,id_Departamento_direccion ,id_Provincia_direccion ,id_Distrito_direccion ,url_foto ,estado ,estado_civil ,fecha_nacimiento ,referencia_direccion)
			String consulta = "insert into t_profesor(id_profesor,id_Pais_nacionalidad,"
					+ "id_Departamento_nacionalidad,id_Provincia_nacionalidad,id_Distrito_nacionalidad,"
					+ "id_Departamento_direccion,id_Provincia_direccion,id_Distrito_direccion,"
					+ "url_foto,estado,estado_civil,"
					+ "fecha_nacimiento,referencia_direccion)"
					+ "values("+id_persona+",'"+docente.getId_Pais_nacionalidad()
					+"','"+docente.getId_Departamento_nacionalidad()+"','"+docente.getId_Provincia_nacionalidad()
					+"','"+docente.getId_Distrito_nacionalidad()+"','"+docente.getId_Departamento_direccion()
					+"','"+docente.getId_Provincia_direccion()+"','"+docente.getId_Distrito_direccion()
					+"','"+docente.getUrl_foto()+"','"+docente.getEstado()
					+"','"+docente.getEstado_civil()+"','"+docente.getFecha_nacimiento()+"','"+docente.getReferencia_direccion()+"')";

			System.out.println(consulta+docente.getFecha_nacimiento());
			int filas=stmt.executeUpdate(consulta,Statement.RETURN_GENERATED_KEYS);
			
			return id_persona+"";
			
			
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return resultado;
	}

	
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
