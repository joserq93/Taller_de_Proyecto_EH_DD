<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<String> versiones = (List<String>)request.getAttribute("versiones");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Elaboración de Horarios</title>

    <link href="ElaboracionHorarios/css/bootstrap.css" rel="stylesheet">

    <link href="ElaboracionHorarios/css/signin.css" rel="stylesheet">

    <script src="ElaboracionHorarios/js/jquery.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="ElaboracionHorarios/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" />
<style>
.hora{
  background-color:#777;
  font-size;12px;
}
.hora-activa{
  background-color: #fff;
  cursor:pointer;
}
.con-curso{
  background-color: #ccc;
}
</style>
  <%@page import="edu.usmp.fia.taller.common.action.SessionParameters"%>
<%@page import="edu.usmp.fia.taller.login.servlet.LoginServlet"%>
<%@page import="edu.usmp.fia.taller.common.bean.Persona"%>
<%
	Persona oPersona = (Persona) request.getSession(false)
			.getAttribute(SessionParameters.PERSONA.text());
%>
  </head>
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
	
	<!-- Inicio Modal -->
		<div id="myModal2" class="modal fade" role="dialog">
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
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Malla curricular</a></li>
					<li><a href="Registrar_Docente?f=registrarDocente">Registro de docentes</a></li>
					<li><a data-toggle="modal" href="#myModal2">Elaboracion de Horarios</a></li>
				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">	
<div class="container">
	<div class="list-group profesores">
		<a href="#" class="list-group-item disabled">Elige una versión</a>
		
		<%
			for(int i = 0; i < versiones.size(); ++i){
		%>
		<input type="hidden" name="version" value="<%=versiones.get(i) %>"/>
		<a href="?f=leerHorario&version=<%=versiones.get(i) %>" class="list-group-item"><%=versiones.get(i) %></a>	
		<% } %>

		<a href="#" class="list-group-item">
			<div class="input-group">
      			<input type="text" class="form-control" name="version" placeholder="Nombre de la Versión">
      			<span class="input-group-btn">
        			<button class="btn btn-default crear-version" type="button">Crear Versión</button>
      			</span>
    		</div><!-- /input-group -->
		</a>
	</div>
</div>
			</div>
		</div>

	</div>
<script type="text/javascript">
$(".crear-version").click(function(){
	version = $("[name=\"version\"]").val();
	if(version.length > 0)
	{
		window.location.href="?version="+version;
	}
});
</script>
</body>
</html>