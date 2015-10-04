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
		</div>
	</div>


		<!-- Inicio Modal -->
		<div id="myModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">

		    <!-- Modal content-->
		    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<form action="<%=getServletContext().getContextPath() %>/AgregarAdministrador" method="post">
				<p>Usuario: <input type="text" name="usuario" /></p>
				<p>Clave: <input type="text" name="clave" /></p>
				<p>Nombres: <input type="text" name="nombres" /></p>
				<p>Apellidos: <input type="text" name="apellidos" /></p>
				<p>Direccion: <input type="text" name="direccion" /></p>
				<p>Celular: <input type="text" name="celular" /></p>
				<p>Correo: <input type="text" name="correo" /></p>
				<p>sueldo: <input type="text" name="sueldo" /></p>
				<p><input type="submit"value="Agregar"></p>
				</form>
			</div>
		<!-- Fin Modal -->

	<script src="../Scripts/jquery-1.9.1.js"></script>
	<script src="../Scripts/bootstrap.min.js"></script>
</body>
</html>