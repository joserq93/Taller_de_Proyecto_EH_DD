<%@page import="edu.usmp.fia.taller.common.action.SessionParameters"%>
<%@page import="edu.usmp.fia.taller.login.servlet.LoginServlet"%>
<%@page import="edu.usmp.fia.taller.common.bean.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% Persona oPersona = (Persona) request.getSession(false).getAttribute(SessionParameters.PERSONA.text()); %>
<body>
	<form action="login" method="get" >
	<input type="hidden" name="f" value="signup" /> 
		<div>
			<h2>
				Welcome!
				<label><%= oPersona.getNombre1() + " " + oPersona.getApePaterno()%></label>
			</h2>
		</div>
	</form>
	<a href="#">registro de docente</a>
	<br/>
	<a href="#">malla curricular</a>
	<br/>
	<br/>
	<br/>
	<a href="login?f=logout">logout</a>
</body>
</html>