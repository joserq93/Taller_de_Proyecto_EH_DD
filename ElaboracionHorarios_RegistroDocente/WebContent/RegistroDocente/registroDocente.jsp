<%@page import="edu.usmp.fia.taller.common.action.SessionParameters"%>
<%@page import="edu.usmp.fia.taller.docente.servlet.Listar_DocenteServlet"%>
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
 <script src="js/combos.js"></script>
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
					<li><a href="Registrar_Docente?f=registrarDocente">Registro de docentes</a></li>
					<li><a data-toggle="modal" href="#myModal">Elaboracion de Horarios</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<a data-toggle="modal" href="#RegistrarDocenteModal">Registrar Docente</a>
			</div>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<form action="<%=getServletContext().getContextPath() %>/Guardar_Docente" method="post" class="form-horizontal" role="form">
		<input type="hidden" name="f" value="GuardarDocente" /> 
				
				<fieldset>
				<legend align= "left" style="width:400px">Datos Generales</legend>
				<div class="form-group">
				<label for="foto"  class="col-lg-1 control-label" >Url Foto: </label>
				<div class="col-lg-10">
				<input type="text" name="urlfoto" />
				</div>
				</div>
				
				<div class="form-group">
				<label for="Nombres" class="col-lg-1 control-label"  >Nombres: </label>
				<div class="col-lg-10">
				<input type="text" name="nombres" />
				</div>
				</div>
				
				<div class="form-group">
				<label for="apellido_paterno" class="col-lg-1 control-label" >Apellido Paterno:</label>
				<div class="col-lg-10">
				 <input type="text" name="apellidopaterno" />
				 </div>
				 </div>
				 
				 <div class="form-group">
				<label for="apellido_materno" class="col-lg-1 control-label">Apellido Materno:</label>
				<div class="col-lg-10">
				 <input type="text" name="apellidomaterno" />
				 </div>
				 </div>
				 
				 <div class="form-group">
				<label for="sexo" class="col-lg-1 control-label">Sexo:</label>
				 <div class="col-lg-10">
				 <select size="1" name="sexo" class="form-control" style="width:150px">
	                	<option value="M">Masculino</option>
	                    <option value="F">Femenino</option>
	                     </select>
	             </div>
	             </div>
	             
	              <div class="form-group">
	             <label for="estado_civil" class="col-lg-1 control-label">Estado Civil:</label>
				 <div class="col-lg-10">
				 <select size="1" name="estadocivil" class="form-control" style="width:150px">
	                	<option value="S">Soltero</option>
	                    <option value="C">Casado</option>
	                    <option value="D">Divorciado</option>
	                     </select>
	             </div>
	             </div>
	             
				 <div class="form-group">
				<label for="paisnacimiento" class="col-lg-1 control-label">Pais de Nacimiento:</label>
				<div class="col-lg-10">
				 <select size="1" name="pais" class="form-control" style="width:150px">
	                	<option value="1">Peru</option>
	                    <option value="2">Chile</option>
	                    <option value="3">Colombia</option>
	                     </select>
	              </div>
	              </div>
	              
				</fieldset>
				
				<fieldset>
				<legend align= "left" style="width:400px">Datos de Nacimiento</legend>
				
				<div class="form-group">
				<label for="fechanacimiento" class="col-lg-1 contro1-label">Fecha de Nacimiento:</label> 
				<div class="col-lg-10">
				<input type="date" name="fechanacimiento">
				</div>
				</div>
				
			  <div class="form-group">
			  <label for="combo_departamento" class="col-lg-1 contro1-label">Departamento:</label>
			  <div class="col-lg-10">
			  <select id="combo_departamento" class="form-control" name="departamento1" style="width:150px">
				  <option>Seleccionar</option>
			  </select>
			  </div>
			  </div>
			  
			  <div class="form-group">
			  <label for="combo_provincias" class="col-lg-1 contro1-label">Provincia</label>
			   <div class="col-lg-10">
			  <select id="combo_provincias" class="form-control" disabled="true" name="provincia1" style="width:150px">
				  <option>Seleccionar</option>
			  </select>
			  </div>
			  </div>
			  
			  <div class="form-group">
			  <label for="combo_distritos" class="col-lg-1 contro1-label">Distrito</label>
			   <div class="col-lg-10">
			  <select id="combo_distritos" class="form-control" disabled="true" name="distrito1" style="width:150px">
				  <option>Seleccionar</option>
			  </select>
			  </div>
			  </div>
			  
			  </fieldset>
			  
			  
			  <fieldset>
			 <legend align= "left" style="width:400px">Direccion</legend>
				
				<div class="form-group">
				 <label for="combo_departamento" class="col-lg-1 contro1-label">Departamento:</label>
				 <div class="col-lg-10">
				  <select size="1" name="departamento2" style="width:150px">
	                	<option value="1">Lima</option>
	                    <option value="2">Piura</option>
	                     </select>
	              </div>
	              </div>
	             
	              <div class="form-group">
				 <label for="combo_provincia" class="col-lg-1 contro1-label">Provincia:</label>
				 <div class="col-lg-10">
				 <select size="1" name="provincia2" style="width:150px">
	                	<option value="1">Canta</option>
	                    <option value="2">Cañete</option>
	                    </select>
	               </div>
	               </div>
	                          
				 <div class="form-group">
				 <label for="combo_distrito" class="col-lg-1 contro1-label">Distrito:</label>
				<div class="col-lg-10">
				 <select size="1" name="distrito2" style="width:150px">
	                	<option value="1">Canta</option>
	                    <option value="2">Asia</option>
	                    </select>
	                    </div>
	                    </div>
	                         
				 <div class="form-group">
				 <label for="referencia" class="col-lg-1 contro1-label">Referencia:</label> 
				 <div class="col-lg-10">
				 <input type="text" name="referencia" />
				 </div>
				 </div>
				 
				</fieldset>
				
				<fieldset>
				 		<legend align= "left" style="width:400px">Datos de Contacto</legend>
				 		
			
				<div class="form-group">
				 <label for="telefono" class="col-lg-1 contro1-label">Telefono:</label>
				  <div class="col-lg-10">
				  <input type="text" name="telefono" /><input type="submit"value="+"><input type="submit"value="-">
				</div>
				</div>
								
				<table class="table table-hover" style="width:100px">
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
				
				<div class="form-group">
				 <label for="email" class="col-lg-1 contro1-label">Email:</label>
				 <div class="col-lg-10">
				 <input type="text" name="email" /><input type="submit"value="+"><input type="submit"value="-">
				</div>
				</div>
				
				<table class="table table-hover" style="width:200px">
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
				
			
				
				<div class="form-group">
				 <label for="email" class="col-lg-1 contro1-label">Tipo Doc.:</label> 
				<div class="col-lg-10">
				 <select size="1" name="tipodoc" class="form-control" style="width:150px">
	                	<option value="DNI">DNI</option>
	                    <option value="Licencia de Conducir">Licencia de Conducir</option>
	                           </select>
	                    </div>
	                    </div>
	             
	              <div class="form-group">
				 <label for="email" class="col-lg-1 contro1-label">Numero:</label>
				 <div class="col-lg-10">
				 <input type="text" name="numero" /><input type="submit"value="+"><input type="submit"value="-">
				</div>
				</div>
				
				<table class="table table-hover" style="width:300px">
					<thead>
						<tr>
							<th>Tipo Documento</th>
							<th>Nro de Documento</th>
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
				 
				 </fieldset>
				 
				 		
				 		<fieldset>
				 		<legend align= "left" style="width:400px">Datos Academicos</legend>
				 		
				 		
				 <div class="form-group">
				 <label for="email" class="col-lg-1 contro1-label">Grado Academico:</label>
				 <div class="col-lg-10">
				 <select size="1" name="gradoacademico" class="form-control" style="width:150px">
	                	<option value="Master">Master</option>
	                    <option value="Doctor">Doctor</option>
	                    </select>
	                    </div>
	                    </div>
	                                 
	                 <div class="form-group">
				 <label for="email" class="col-lg-1 contro1-label">Profecion:</label>
				 <div class="col-lg-10">
				 <select size="1" name="profecion" class="form-control" style="width:150px">
	                	<option value="Ing.Sistemas">Ing.Sistemas</option>
	                    <option value="Ing.Industrial">Ing.Industrial</option>
	                    </select>
	                    </div>
	                    </div>   
	                             
	              <div class="form-group">
				 <label for="email" class="col-lg-1 contro1-label">Especialidad:</label>
				 <div class="col-lg-10">
				 <select size="1" name="especialidad" class="form-control" style="width:150px">
	                	<option value="S.I">S.I</option>
	                    <option value="T.I">T.I</option>
	                    </select>
	                    </div>
	                    </div>
	                    
	               <div class="form-group">
				 <label for="email" class="col-lg-1 contro1-label">Institucion:</label>
				 <div class="col-lg-10">
				 <input type="text" name="institucion" />  
	              </div>
	              </div>
	              
	               <div class="form-group">
				 <label for="email" class="col-lg-1 contro1-label">Fecha de Ingreso:</label>
				 <div class="col-lg-10">
				 <input type="date" name="fechaingreso" /><input type="submit"value="+"><input type="submit"value="-"></p>
				 </div>
				 </div>
				 <table class="table table-hover" style="width:500px">
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
				 
				 
				<p><input type="submit"value="Agregar" class="btn btn-primary"></p>
				</form>
			</div>
	</div>
				<!-- Inicio Modal importar excel jose-->
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
		
		
		<!-- Inicio Modal -->
		<div id="myModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">

		    <!-- Modal content-->
		    <div class="modal-content" style="margin-top:50%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Sistema Elaboracion de Horarios</h4>
		      </div>
		      <div class="modal-body">
		        <p>¿Desea agregar el archivo excel consolidado?.</p>
		        </br>
		        <center>
				<form action="cargarExcels" method="get" >
						<input type="hidden" name="f" value="leerExcel" /> <input type="hidden"
			name="p" id="p" value="" />
			        <button type="submit"  class="btn btn-success">Agregar archivo</button>
			     </form>
			     <form action="ElaboracionHorariosServlet" method="get" > 
			     <input type="hidden" name="f" value="leerHorario" /> <input type="hidden"
						name="p" id="p" value="" /> 
			        <button type="submit"  class="btn btn-info">No agregar archivo</button>
				</form>
			     </center>
			     <p>.</p>
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