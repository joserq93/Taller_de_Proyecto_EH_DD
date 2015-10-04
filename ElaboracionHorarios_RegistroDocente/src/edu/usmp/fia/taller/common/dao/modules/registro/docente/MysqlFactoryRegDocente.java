package edu.usmp.fia.taller.common.dao.modules.registro.docente;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.usmp.fia.taller.common.bean.Docente;
import edu.usmp.fia.taller.common.bean.Persona;
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
	public String guardarDocente(Docente docente) throws Exception {
		String resultado="";
		try {
			Connection conexion = (Connection) getConnection();
			Statement stmt = conexion.createStatement();
			
			String consulta = "insert into docente(id_docente,id_Pais_nacionalidad,id_Departamento_nacionalidad,id_Provincia_nacionalidad,id_Distrito_nacionalidad,id_Departamento_direccion,id_Provincia_direccion,id_Distrito_direccion,nombre,apellido_materno,apellido_paterno,url_foto,telefono,correo,estado,estado_civil,sexo,fecha_nacimiento,referencia_direccion,tipo_documento,grado_academico,profecion,especialidad,institucion,fecha_ingreso)"
					+ "values('"+docente.getId_docente()+"','"+docente.getId_Pais_nacionalidad()+"','"+docente.getId_Departamento_nacionalidad()+"','"+docente.getId_Provincia_nacionalidad()+"','"+docente.getId_Provincia_nacionalidad()+"','"+docente.getId_Departamento_direccion()+"','"+docente.getId_Provincia_direccion()+"','"+docente.getId_Distrito_direccion()+"','"+docente.getNombre()+"','"+docente.getApellido_materno()+"','"+docente.getApellido_paterno()+"','"+docente.getUrl_foto()+"','"+docente.getTelefono()+"','"+docente.getCorreo()+"','"+docente.getEstado()+"','"+docente.getEstado_civil()+"','"+docente.getSexo()+"','"+docente.getFecha_nacimiento()+"','"+docente.getReferencia_direccion()+"','"+docente.getTipo_documento()+"','"+docente.getGrado_academico()+"','"+docente.getProfecion()+"','"+docente.getEspecialidad()+"','"+docente.getInstitucion()+"','"+docente.getFecha_ingreso()+"')";
			
			int filas=stmt.executeUpdate(consulta);
			
			if(filas==1){
				resultado="Datos Guardados";
			}else{
				resultado="Ocurrio un Error";
			}
			
			
		} catch (Exception e) {
			
			System.out.print(e.getMessage());
		}
		
		
		return resultado;
	}

	
	



}
