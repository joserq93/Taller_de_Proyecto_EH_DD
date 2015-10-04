<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=us-ascii">
	
	<meta name="viewport" content="width=device-width,initial-scale=1">

	<title>DataTables example - Bootstrap</title>

	<!--Estilos necesarios-->

	
	<!--  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/ElaboracionHorarios/css/bootstrap.min.css">-->
	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/ElaboracionHorarios/css/dataTables.bootstrap.css">

	<!--Java Script necesarios-->
	<script type="text/javascript" src="<%=request.getContextPath() %>/ElaboracionHorarios/js/site.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/ElaboracionHorarios/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/ElaboracionHorarios/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/ElaboracionHorarios/js/dataTables.bootstrap.js"></script>


	<script type="text/javascript" class="init">
		$(document).ready(function() {
			$('#example').dataTable();
		} );
	</script>

</head>
<body class="wide example">
<%@page import="edu.usmp.fia.taller.common.bean.HorariosBean"%>
<%@page import="java.util.List"%>
<% List<HorariosBean> datos=(List<HorariosBean>)request.getAttribute("listaHorario"); %>
<% String mensaje= (String)request.getAttribute("mensaje"); %>


	<div class="fw-container">
		<form action="cargarExcels" method="post" enctype="multipart/form-data">
		<input type="hidden" name="f" value="cargarExcel" /> <input type="hidden"
			name="p" id="p" value="" />
			
			Select File : <input type="file" name="filetoupload">
			<br/>
			<input type="submit" class="btn btn-danger" value="Subir Archivo">
		</form>
	</div>
	
<% if(datos!=null){ %>


	<div class="fw-container">

			<div class="content" style="overflow-x:scroll;">
				
				<div style="padding:5%; width:90% ">
				<table id="example" class="table table-striped table-bordered">
					<thead>
						<tr>
						<!-- 	<th>CODFAC</th> -->
						<!-- 	<th>C01</th> -->
								<th>CICLO</th>
								<th>TUR</th>
								<th>COD</th>
								<th>LAB</th>
								<th>PROFESOR</th>
								<th>CURSO</th>
						<!--		<th>DESRES</th> -->
								<th>SECC</th>
								<th>AULA</th>
								<th>CRED</th>
								<th>LUNES</th>
								<th>MARTES</th>
								<th>MIERCOLES</th>
								<th>JUEVES</th>
								<th>VIERNES</th>
								<th>SABADO</th>
						<!--  		<th>DOMINGO </th> -->

						</tr>
					</thead>

					<tbody>
					  <%for(HorariosBean dato:datos){ %>
						<tr>
						<!-- 	<td><%=dato.getCODFAC()%></td>-->
			     		<!--	<td><%=dato.getC01()%></td> -->
			            	<td><%=dato.getCICEST()%></td>
			            	<td><%=dato.getTUR()%></td>
			            	<td><%=dato.getCODCUR()%></td>
			            	<td><%=dato.getCODCURTEO()%></td>
			            	<td><%=dato.getPROFESOR()%></td>
			            	<td><%=dato.getCURSO()%></td>
			            <!--	<td><%=dato.getDESRES()%></td> -->
			            	<td><%=dato.getCODSEC()%></td>
			            	<td><%=dato.getAULA()%></td>
			            	<td><%=dato.getNUMCRE()%></td>
			            	<td><%=dato.getLUNES()%></td>
			            	<td><%=dato.getMARTES()%></td>
			            	<td><%=dato.getMIERCOLES()%></td>
			            	<td><%=dato.getJUEVES()%></td>
			            	<td><%=dato.getVIERNES()%></td>
			            	<td><%=dato.getSABADO()%></td>
			            <!--	<td><%=dato.getDOMINGO()%></td> -->
						</tr>
					<%} %>	
					
					</tbody>
				</table>
</div>
			</div>
	</div>
<%} %>
</body>
</html>