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
    <script src="ElaboracionHorarios/js/bootstrap.min.js"></script>
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
  </head>
<body>
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