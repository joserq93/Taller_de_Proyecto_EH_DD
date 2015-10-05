<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String json = (String)request.getAttribute("json");
	String horas = (String)request.getAttribute("horas");
	String version = (String)request.getAttribute("version");
	String cycle = (String)request.getAttribute("cycler");
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
  background-color:#inherit;
  color: white;
  font-size;12px;
}
.hora-activa{
  background-color: #5C9FB8;
  color: white;
  cursor:pointer;
}
.con-curso{
  background-color: #5C9FB8;
  color: white;
}
</style>
  </head>
  <%@page import="edu.usmp.fia.taller.common.action.SessionParameters"%>
<%@page import="edu.usmp.fia.taller.login.servlet.LoginServlet"%>
<%@page import="edu.usmp.fia.taller.common.bean.Persona"%>
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
					<li><a data-toggle="modal" href="#myModal2">Elaboracion de Horarios</a></li>
				</ul>

			</div>
			
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
			        <button type="submit"  class="btn btn-success" style:"float:left;">Agregar archivo</button>
			     </form>
			     <form action="ElaboracionHorariosServlet" method="get" > 
			     <input type="hidden" name="f" value="leerHorario" /> <input type="hidden"
						name="p" id="p" value="" /> 
			        <button type="submit"  class="btn btn-info" style:"float:right;">No agregar archivo</button>
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

  
  
  <div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <div class="list-group profesores">
          <a href="#" class="list-group-item active">Elige un Profesor</a>
        </div>
    </div>
  </div>
</div>
  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
  <h1>Usted está trabajando en la versión: <%=version %></h1>


    <div style="padding:0 100px 0 100px;">
      <div class="row" style="text-align:center;">
        <ul class="nav nav-tabs">
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("01")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=01">Ciclo I</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("02")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=02">Ciclo II</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("03")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=03">Ciclo III</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("04")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=04">Ciclo IV</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("05")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=05">Ciclo V</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("06")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=06">Ciclo VI</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("07")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=07">Ciclo VII</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("08")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=08">Ciclo VIII</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("09")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=09">Ciclo IX</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("10")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=10">Ciclo X</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("E1")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=E1">Electivos 1</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("E2")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=E2">Electivos 2</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("E3")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=E3">Electivos 3</a></li>
		  <li role="presentation" <% if(cycle.equalsIgnoreCase("E4")){ %>class="active"<% } %>><a href="ElaboracionHorariosServlet?f=leerHorario&cycle=E4">Electivos 4</a></li>
		</ul>
      </div>
      <div class="row" style="text-align:center;">
        <ul class="nav nav-tabs listado-secciones">
		 
		</ul>
      </div>
      <br clear="all" />
      <div class="row">
        <div class="col-md-3 cursos" style=" overflow: auto; position: relative;"></div>
        <div class="col-md-9">
           <table class="table table-striped table-bordered">
              <tr>
                  <th width="30px">Horas</th>
                  <th width="100px">Lunes</th>
                  <th width="100px">Martes</th>
                  <th width="100px">Miércoles</th>
                  <th width="100px">Jueves</th>
                  <th width="100px">Viernes</th>
                  <th width="100px">Sábado</th>
                  <th width="100px">Domingo</th>
              </tr>
              <tr>
                  <td>8:00</td>
                  <td class="hora" hora-data="1-1"></td>
                  <td class="hora" hora-data="2-1"></td>
                  <td class="hora" hora-data="3-1"></td>
                  <td class="hora" hora-data="4-1"></td>
                  <td class="hora" hora-data="5-1"></td>
                  <td class="hora" hora-data="6-1"></td>
                  <td class="hora" hora-data="7-1"></td>
              </tr>
              <tr>
                  <td>8:45</td>
                  <td class="hora" hora-data="1-2"></td>
                  <td class="hora" hora-data="2-2"></td>
                  <td class="hora" hora-data="3-2"></td>
                  <td class="hora" hora-data="4-2"></td>
                  <td class="hora" hora-data="5-2"></td>
                  <td class="hora" hora-data="6-2"></td>
                  <td class="hora" hora-data="7-2"></td>
              </tr>
              <tr>
                  <td>9:30</td>
                  <td class="hora" hora-data="1-3"></td>
                  <td class="hora" hora-data="2-3"></td>
                  <td class="hora" hora-data="3-3"></td>
                  <td class="hora" hora-data="4-3"></td>
                  <td class="hora" hora-data="5-3"></td>
                  <td class="hora" hora-data="6-3"></td>
                  <td class="hora" hora-data="7-3"></td>
              </tr>
              <tr>
                  <td>10:15</td>
                  <td class="hora" hora-data="1-4"></td>
                  <td class="hora" hora-data="2-4"></td>
                  <td class="hora" hora-data="3-4"></td>
                  <td class="hora" hora-data="4-4"></td>
                  <td class="hora" hora-data="5-4"></td>
                  <td class="hora" hora-data="6-4"></td>
                  <td class="hora" hora-data="7-4"></td>
              </tr>
              <tr>
                  <td>11:00</td>
                  <td class="hora" hora-data="1-5"></td>
                  <td class="hora" hora-data="2-5"></td>
                  <td class="hora" hora-data="3-5"></td>
                  <td class="hora" hora-data="4-5"></td>
                  <td class="hora" hora-data="5-5"></td>
                  <td class="hora" hora-data="6-5"></td>
                  <td class="hora" hora-data="7-5"></td>
              </tr>
              <tr>
                  <td>11:45</td>
                  <td class="hora" hora-data="1-19"></td>
                  <td class="hora" hora-data="2-19"></td>
                  <td class="hora" hora-data="3-19"></td>
                  <td class="hora" hora-data="4-19"></td>
                  <td class="hora" hora-data="5-19"></td>
                  <td class="hora" hora-data="6-19"></td>
                  <td class="hora" hora-data="7-19"></td>
              </tr>
              <tr>
                  <td>12:30</td>
                  <td class="hora" hora-data="1-6"></td>
                  <td class="hora" hora-data="2-6"></td>
                  <td class="hora" hora-data="3-6"></td>
                  <td class="hora" hora-data="4-6"></td>
                  <td class="hora" hora-data="5-6"></td>
                  <td class="hora" hora-data="6-6"></td>
                  <td class="hora" hora-data="7-6"></td>
              </tr>
              <tr>
                  <td>1:15</td>
                  <td class="hora" hora-data="1-7"></td>
                  <td class="hora" hora-data="2-7"></td>
                  <td class="hora" hora-data="3-7"></td>
                  <td class="hora" hora-data="4-7"></td>
                  <td class="hora" hora-data="5-7"></td>
                  <td class="hora" hora-data="6-7"></td>
                  <td class="hora" hora-data="7-7"></td>
              </tr>
              <tr>
                  <td>2:00</td>
                  <td class="hora" hora-data="1-8"></td>
                  <td class="hora" hora-data="2-8"></td>
                  <td class="hora" hora-data="3-8"></td>
                  <td class="hora" hora-data="4-8"></td>
                  <td class="hora" hora-data="5-8"></td>
                  <td class="hora" hora-data="6-8"></td>
                  <td class="hora" hora-data="7-8"></td>
              </tr>
              <tr>
                  <td>2:45</td>
                  <td class="hora" hora-data="1-9"></td>
                  <td class="hora" hora-data="2-9"></td>
                  <td class="hora" hora-data="3-9"></td>
                  <td class="hora" hora-data="4-9"></td>
                  <td class="hora" hora-data="5-9"></td>
                  <td class="hora" hora-data="6-9"></td>
                  <td class="hora" hora-data="7-9"></td>
              </tr>
              <tr>
                  <td>3:30</td>
                  <td class="hora" hora-data="1-10"></td>
                  <td class="hora" hora-data="2-10"></td>
                  <td class="hora" hora-data="3-10"></td>
                  <td class="hora" hora-data="4-10"></td>
                  <td class="hora" hora-data="5-10"></td>
                  <td class="hora" hora-data="6-10"></td>
                  <td class="hora" hora-data="7-10"></td>
              </tr>
              <tr>
                  <td>4:15</td>
                  <td class="hora" hora-data="1-11"></td>
                  <td class="hora" hora-data="2-11"></td>
                  <td class="hora" hora-data="3-11"></td>
                  <td class="hora" hora-data="4-11"></td>
                  <td class="hora" hora-data="5-11"></td>
                  <td class="hora" hora-data="6-11"></td>
                  <td class="hora" hora-data="7-11"></td>
              </tr>	
              <tr>
                  <td>5:00</td>
                  <td class="hora" hora-data="1-12"></td>
                  <td class="hora" hora-data="2-12"></td>
                  <td class="hora" hora-data="3-12"></td>
                  <td class="hora" hora-data="4-12"></td>
                  <td class="hora" hora-data="5-12"></td>
                  <td class="hora" hora-data="6-12"></td>
                  <td class="hora" hora-data="7-12"></td>
              </tr>
              <tr>
                  <td>5:45</td>
                  <td class="hora" hora-data="1-13"></td>
                  <td class="hora" hora-data="2-13"></td>
                  <td class="hora" hora-data="3-13"></td>
                  <td class="hora" hora-data="4-13"></td>
                  <td class="hora" hora-data="5-13"></td>
                  <td class="hora" hora-data="6-13"></td>
                  <td class="hora" hora-data="7-13"></td>
              </tr>
              <tr>
                  <td>6:30</td>
                  <td class="hora" hora-data="1-14"></td>
                  <td class="hora" hora-data="2-14"></td>
                  <td class="hora" hora-data="3-14"></td>
                  <td class="hora" hora-data="4-14"></td>
                  <td class="hora" hora-data="5-14"></td>
                  <td class="hora" hora-data="6-14"></td>
                  <td class="hora" hora-data="7-14"></td>
              </tr>
              <tr>
                  <td>7:15</td>
                  <td class="hora" hora-data="1-15"></td>
                  <td class="hora" hora-data="2-15"></td>
                  <td class="hora" hora-data="3-15"></td>
                  <td class="hora" hora-data="4-15"></td>
                  <td class="hora" hora-data="5-15"></td>
                  <td class="hora" hora-data="6-15"></td>
                  <td class="hora" hora-data="7-15"></td>
              </tr>
              <tr>
                  <td>8:00</td>
                  <td class="hora" hora-data="1-16"></td>
                  <td class="hora" hora-data="2-16"></td>
                  <td class="hora" hora-data="3-16"></td>
                  <td class="hora" hora-data="4-16"></td>
                  <td class="hora" hora-data="5-16"></td>
                  <td class="hora" hora-data="6-16"></td>
                  <td class="hora" hora-data="7-16"></td>
              </tr>
              <tr>
                  <td>8:45</td>
                  <td class="hora" hora-data="1-17"></td>
                  <td class="hora" hora-data="2-17"></td>
                  <td class="hora" hora-data="3-17"></td>
                  <td class="hora" hora-data="4-17"></td>
                  <td class="hora" hora-data="5-17"></td>
                  <td class="hora" hora-data="6-17"></td>
                  <td class="hora" hora-data="7-17"></td>
              </tr>
              <tr>
                  <td>9:30</td>
                  <td class="hora" hora-data="1-18"></td>
                  <td class="hora" hora-data="2-18"></td>
                  <td class="hora" hora-data="3-18"></td>
                  <td class="hora" hora-data="4-18"></td>
                  <td class="hora" hora-data="5-18"></td>
                  <td class="hora" hora-data="6-18"></td>
                  <td class="hora" hora-data="7-18"></td>
              </tr>
           </table>
        </div>
        <div class="col-md-10 pull-right">
            <div class="row">
                <table class="table table-bordered table-striped">
                  <tr>
                  	<th>Curso</th>
                  	<td class="curso-tabla"></td>
                    <th>Horas Teoría</th>
                    <td class="hora-curso-tabla-teoria"></td>
                  </tr>
                  <tr>
                  	<th>Profesor</th>
                  	<td class="profe-tabla"></td>
                    <th>Horas Laboratorio</th>
                    <td class="hora-curso-tabla-laboratorio"></td>
                  </tr>
                  <tr>
                  	<th></th><td></td>
                    <th>Horas Práctica</th>
                    <td class="hora-curso-tabla-practica"></td>
                  </tr>
                </table>
            </div>
        </div>
      </div>
      <div class="row" style="text-align:right">
      	<form action="" method="post">
      		<div class="inputs-horarios" style="display:none;">
      			<input type="text" name="horas-horario" class="horas-horario"/>
      		</div>
			<button class="btn btn-success" onclick="$('.horas-horario').val(JSON.stringify(horasHorarios));return confirm('¿Estas seguro que deseas guardar?');">Guardar Versión</button>
			<a href="?borrarVersion=ok" class="btn btn-danger" onclick="return confirm('¿Deseas salir del elaborador de horarios?');">Crear Nueva Versión</a>
		</form>
      </div>
    </div> <!-- /container -->
  </div>
	
		</div>

	</div>

	
<script type="text/javascript">
$(".cicloo").on("change", function(){
	window.location.href = "ElaboracionHorariosServlet?f=leerHorario&cycle="+$(this).val();
});

var horasHorarios = [];
var objHorario = [];
var cursoGlobal = {};

var json = '<%=json %>';
var obj = JSON.parse(json);

seccion = "";
seccionAgregada = [];
aumentandoSeccion = 0;
$.each(obj.cursos, function(i, curso){
	cursoGlobal[i+""] = curso;
	
	seccionAgregada[aumentandoSeccion++] = curso.se;
	
  html = '<div class="btn btn-success curso" curso-id="'+i+'" curso-seccion="'+curso.se+'" data-toggle="modal" data-target=".bs-example-modal-sm" style="white-space:normal; margin-bottom:2px;font-size:12px; width:170px;">'+curso.seccion+'</div>';
  $(".cursos").append(html);
});

seccionUnica = [];
$.each(seccionAgregada, function(i, el){
    if($.inArray(el, seccionUnica) === -1) seccionUnica.push(el);
});
seccionUnica.sort();

$.each(seccionUnica, function(i, el){
	seccion = '<li role="presentation"><a href="#" onclick="filtroSeccion(\''+el+'\')">'+el+'</a></li>';
	$(".listado-secciones").append(seccion);
});

var horas = 0;
var horita = 0;
var nombre = '';
var id = 0;
var profesoor = '';
$(".curso").on("click", function(){
  id = $(this).attr("curso-id");

  objCurso = GetObjectKeyIndex(obj.cursos, id);
  $(".curso-tabla").html(objCurso.seccion);

  $(".profesores").html('<a href="#" class="list-group-item active">Elige un Profesor</a>');
  $.each(objCurso.profesores, function(i, o){
      profe = '<a href="#" class="list-group-item profe" onclick="profeHorario('+i+', '+id+')">'+o.nombre+'</a>';
      $(".profesores").append(profe);
  });
});

function profeHorario(profe, curso)
{
  $('#myModal').modal('hide');
  objCurso = GetObjectKeyIndex(obj.cursos, curso); 
  objProfe = GetObjectKeyIndex(objCurso.profesores, profe);  

  horas = objCurso.horas;
  
  nombre = objCurso.seccion;
  profesoor = objProfe.nombre;

  $(".hora-curso-tabla").html(horas);
  $(".hora-curso-tabla-teoria").html(objCurso.horasTeoria);
  $(".hora-curso-tabla-laboratorio").html(objCurso.horasLaboratorio);
  $(".hora-curso-tabla-practica").html(objCurso.horasPractica);
  $(".profe-tabla").html(objProfe.nombre);
  
  
  horasProfe = 0;
  $(".hora").removeClass("hora-activa");  
  $.each(objProfe.disponibilidad, function(key, value){
    $(".hora").each(function(index){
        if(value == $(this).attr("hora-data"))
        {
          $(this).addClass("hora-activa");
          horasProfe++;
        }
    });
  }); 
  $(".hora-profe-tabla").html(horasProfe+'');
}

$(".hora").on("click", function(e){
	horita = horas-$("[curso-id-here=\""+id+"\"]").length;
  if($(this).attr("class") == 'hora hora-activa')
  {
    if(horita > 0)
    {
      $(this).html(nombre+'<br/><br/>'+profesoor+"<br/><br/><select name='aulas'style='color: black;'></select>");
      $(this).addClass("con-curso");
      $(this).attr("profesor-asignado", profesoor);
      $(this).attr("curso-asignado", nombre);
      $(this).attr("curso-id-here", id);
      
      horasHorarios[horasHorarios.length] = nombre+'=='+profesoor+'=='+$(this).attr("hora-data");
      
      el = $(this);
      $.post("ElaboracionHorariosServlet?f=leerSeccion", {horas: $(this).attr("hora-data")}, function(data){
    	  	var html = '';
    	  	json = JSON.parse(data);
    	    $.each(json.aulas, function(i, o){
    	        html += '<option value="' + o.id + '">' + o.data + '</option>';
    	    });
    	    $(el).find("select").html(html);
		});
      
      horita--;
    }
    
    if(horita == 0)
    {
    	$("[curso-id=\""+id+"\"]").attr("class", 'btn btn-danger');
    	$("[curso-id=\""+id+"\"]").attr("data-toggle", false);
    }
  }
  else if($(this).attr("class") == 'hora hora-activa con-curso' || $(this).attr("class") == 'hora con-curso hora-activa')
  {
	  if($(e.target).is('select'))
		{
			return ;  
		}
	  horaDataEsto = $(this).attr("hora-data");
	  $.each(horasHorarios, function(key, value){
		  	if(value == nombre+'=='+profesoor+'=='+horaDataEsto)
			{
		  		horasHorarios.splice(key, 1); 
			}
	  });
	  
     $(this).html("");
     $(this).removeClass("con-curso");
     $(this).removeAttr("profesor-asignado");
     $(this).removeAttr("curso-asignado");
     $(this).removeAttr("curso-id-here");
     
    $("[curso-id=\""+id+"\"]").attr("class", 'btn btn-success');
 	$("[curso-id=\""+id+"\"]").attr("data-toggle", 'modal');
     
 	horita++;
  }
  
  if($(this).attr("class") == 'hora con-curso')
	{
		if(confirm("¿Desea eliminar esta asignación?"))
		{
			el = this;
			 $.each(horasHorarios, function(key, value){
				  	if(value == $(el).attr("curso-asignado")+'=='+$(el).attr("profesor-asignado")+'=='+$(el).attr("hora-data"))
					{
				  		horasHorarios.splice(key, 1); 
					}
			  });
			$(this).attr('class',"hora");
			$(this).html('');
			$(this).removeAttr("profesor-asignado");
			$(this).removeAttr("curso-asignado");
			idHere = $(this).attr("curso-id-here");
			 $("[curso-id=\""+idHere+"\"]").attr("class", 'btn btn-success');
			 $("[curso-id=\""+idHere+"\"]").attr("data-toggle", 'modal');
			 $(this).removeAttr("curso-id-here");
		}
  	}
  //$(".hora-falta-curso").html(horas+" horas");
});

function filtroSeccion(se)
{
	$(".curso").each(function(index){		
		if($(this).attr("curso-seccion") != se)
		{
			$(this).hide();	
		}
		else
		{
			$(this).show();
		}
	});
}


function GetObjectKeyIndex(obj, keyToFind) {
  var r = null;
  $.each(obj, function(i, o){
      if(i == keyToFind)
      {
        r = o;
      }
  });
    return r;
}



////////////////////////////////////////////////////////////////////////////////////////////////////////

jsonHoras = '<%=horas %>';
var objHorario = JSON.parse(jsonHoras);

$.each(objHorario.horas, function(key, value){
	idBuscado = 0;	
	 $.each(cursoGlobal, function(i, o){
	      if(o.seccion == value.curso)
	      {
	        idBuscado = i;
	      }
	  });
	
	if(idBuscado == 0)
	{
		 return true;
	}
	 
	horasHorarios[horasHorarios.length] = value.curso+'=='+value.nombre+'=='+value.dia+'-'+value.hora;
	
	$("[hora-data=\""+value.dia+"-"+value.hora+"\"]").html(value.curso+"<br/><br/>"+value.nombre);
	$("[hora-data=\""+value.dia+"-"+value.hora+"\"]").addClass("con-curso");
	$("[hora-data=\""+value.dia+"-"+value.hora+"\"]").attr("profesor-asignado", value.nombre);
	$("[hora-data=\""+value.dia+"-"+value.hora+"\"]").attr("curso-asignado", value.curso);
	$("[hora-data=\""+value.dia+"-"+value.hora+"\"]").attr("curso-id-here", idBuscado);
});
</script>
  </body>
</html>
