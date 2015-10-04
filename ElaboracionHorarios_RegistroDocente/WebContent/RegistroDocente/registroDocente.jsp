<%@page import="edu.usmp.fia.taller.common.action.SessionParameters"%>

<%@page import="edu.usmp.fia.taller.common.bean.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/boostrap/bootstrap.min.css" rel="stylesheet" />
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="css/dashboard.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<%
	Persona oPersona = (Persona) request.getSession(false)
			.getAttribute(SessionParameters.PERSONA.text());
%>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Taller</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><%= oPersona.getNombre1() + " " + oPersona.getApePaterno()%></a></li>
				<li><a href="login?f=logout">Salir</a></li>
			</ul>

		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Malla curricular</a></li>
					<li><a href="#">Registro de docentes</a></li>
					<li><a data-toggle="modal" href="#myModal">Elaboracion de Horarios</a></li>
				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<a data-toggle="modal" href="#RegistrarDocenteModal">Registrar Docente</a>
			</div>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<form action="<%=getServletContext().getContextPath() %>/Guardar_Docente" method="post">
		<input type="hidden" name="f" value="GuardarDocente" /> 
				<p>Datos Generales</p>
				<p>Codigo: <input type="text" name="codigo" disabled /></p>
				<p>Url Foto: <input type="text" name="urlfoto" /></p>
				<p>Nombres: <input type="text" name="nombres" /></p>
				<p>Apellido Paterno: <input type="text" name="apellidopaterno" /></p>
				<p>Apellido Materno: <input type="text" name="apellidomaterno" /></p>
				<p>Sexo: <select size="1" name="sexo" >
	                	<option value="M">Masculino</option>
	                    <option value="F">Femenino</option>
	                     </select>
				<p>Estado Civil: <select size="1" name="estadocivil" >
	                	<option value="S">Soltero</option>
	                    <option value="C">Casado</option>
	                    <option value="D">Divorciado</option>
	                     </select>
				<p>Pais de Nacimiento: <select size="1" name="pais" >
	                	<option value="1">Peru</option>
	                    <option value="2">Chile</option>
	                    <option value="3">Colombia</option>
	                     </select>
				
				
						<p>Datos de Nacimiento</p>
				
				<p>Fecha de Nacimiento: <input type="text" name="fechanacimiento" /></p>
				<p>Departamento: <select size="1" name="departamento1" >
	                	<option value="1">Lima</option>
	                    <option value="2">Piura</option>
	                     </select>
				<p>Provincia: <select size="1" name="provincia1" >
	                	<option value="1">Canta</option>
	                    <option value="2">Cañete</option>
	                          </select>
				<p>Distrito: <select size="1" name="distrito1" >
	                	<option value="1">Canta</option>
	                    <option value="2">Asia</option>
	                         </select>
	                     
	                     <p>Direccion</p>
				
				
				<p>Departamento: <select size="1" name="departamento2" >
	                	<option value="1">Lima</option>
	                    <option value="2">Piura</option>
	                     </select>
				<p>Provincia: <select size="1" name="provincia2" >
	                	<option value="1">Canta</option>
	                    <option value="2">Cañete</option>
	                          </select>
				<p>Distrito: <select size="1" name="distrito2" >
	                	<option value="1">Canta</option>
	                    <option value="2">Asia</option>
	                         </select>
				<p>Referencia: <input type="text" name="referencia" /></p>
				
				
				 		<p>Datos de Contacto</p>
				 		
				 		
				<p>Telefono: <input type="text" name="telefono" /><input type="submit"value="+"><input type="submit"value="-"></p>
				<table>
					<thead>
						<tr>
							<th>Telefono</th>
						</tr>
					</thead>
						<tr>
							<td>966251235</td>
						</tr>
						<tr>
							<td>966251235</td>
						</tr>
				</table>
				
				<p>Email: <input type="text" name="email" /><input type="submit"value="+"><input type="submit"value="-"></p>
				<table>
					<thead>
						<tr>
							<th>Email</th>
						</tr>
					</thead>
						<tr>
							<td>vasronald_200@hotmail.com</td>
						</tr>
						<tr>
							<td>jose_r@gmail.com</td>
						</tr>
				</table>
				
				<p>Tipo Doc.: <select size="1" name="tipodoc" >
	                	<option value="DNI">DNI</option>
	                    <option value="Licencia de Conducir">Licencia de Conducir</option>
	                           </select>
	               Numero: <input type="text" name="numero" /><input type="submit"value="+"><input type="submit"value="-"></p>
				<table>
					<thead>
						<tr>
							<th>Tipo Documento</th>
							<th>Numero de Documento</th>
						</tr>
					</thead>
						<tr>
							<td>DNI</td>
							<td>70312419</td>
						</tr>
						<tr>
							<td>Licencia de Conducir</td>
							<td>56213597</td>
						</tr>
				</table>
				 
				 
				 		<p>Datos Academicos</p>
				 		
				 		
				 <p>Grado Academico: <select size="1" name="gradoacademico" >
	                	<option value="Master">Master</option>
	                    <option value="Doctor">Doctor</option>
	                                 </select>
	                Profecion: <select size="1" name="profecion" >
	                	<option value="Ing.Sistemas">Ing.Sistemas</option>
	                    <option value="Ing.Industrial">Ing.Industrial</option>
	                           </select>     
	              </p>
	              
	              
	              <p>Especialidad: <select size="1" name="especialidad" >
	                	<option value="S.I">S.I</option>
	                    <option value="T.I">T.I</option>
	                               </select>
	                Institucion: <input type="text" name="institucion" />  
	              </p>
	              <p>Fecha de Ingreso: <input type="text" name="fechaingreso" /><input type="submit"value="+"><input type="submit"value="-"></p>
				 <table>
					<thead>
						<tr>
							<th>Grado Academico</th>
							<th>Profecion</th>
							<th>Especialidad</th>
							<th>Institucion</th>
							<th>Fecha</th>
						</tr>
					</thead>
						<tr>
							<td>DNI</td>
							<td>70312419</td>
							<td>DNI</td>
							<td>DNI</td>
							<td>70312419</td>
						</tr>
						<tr>
							<td>Licencia de Conducir</td>
							<td>56213597</td>
							<td>DNI</td>
							<td>DNI</td>
							<td>70312419</td>
						</tr>
				</table>
				 
				 
				<p><input type="submit"value="Agregar"></p>
				</form>
			</div>
	</div>

				<!-- Inicio Modal -->
		<div id="RegistrarDocenteModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">

		    <!-- Modal content-->
		    <div class="modal-content" style="margin-top:50%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">REGISTRAR DOCENTE</h4>
		      </div>
		      <div class="modal-body">
		        		    
			
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>

		  </div>
		</div>
		<!-- Fin Modal -->


	<script src="../Scripts/jquery-1.9.1.js"></script>
	<script src="../Scripts/bootstrap.min.js"></script>
</body>
</html>