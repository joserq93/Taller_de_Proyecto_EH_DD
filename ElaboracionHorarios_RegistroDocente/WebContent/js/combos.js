$( document ).ready(function() {
	var global_coddpto="";
	var optionVacio='<option value="0" selected="true" disabled="disabled">Seleccionar</option>';
	//cargar combo departamentos
	var combo_departamento = $('#combo_departamento');
	var combo_provincias = $('#combo_provincias');
	var combo_distritos = $('#combo_distritos');
	var request = $.ajax({
		  url: "ubigeo",
		  method: "POST",
		  data: { f : "listarDepartamentos" },
		  dataType: "json",
		  
		});
		request.done(function( departamentos ) {
			$(departamentos).each(function(i, v){ 
				if(i==0)
					combo_departamento.append(optionVacio);
					combo_departamento.append('<option value="' + v.coddpto + '">' + v.nombre + '</option>');
            })
		});
	//cargar combo provincias
	combo_departamento.on('change', function() {
	global_coddpto=this.value;
	var request = $.ajax({
				  url: "ubigeo",
				  method: "POST",
				  data: { f : "listarProvincias",
					  coddpto:global_coddpto},
				  dataType: "json",
				  
		});
		request.done(function( provincias ) {
			combo_distritos.find('option').remove();
			combo_provincias.find('option').remove();;
			combo_provincias.prop('disabled', false);
			combo_distritos.prop('disabled', true);
			$(provincias).each(function(i, v){ 
				{
					if(i==0){
						combo_provincias.append(optionVacio);
						combo_distritos.append(optionVacio);
					}
				}
				combo_provincias.append('<option value="' + v.codprov + '">' + v.nombre + '</option>');
				});
			});
	});

	combo_provincias.on('change', function() {
	var request = $.ajax({
				  url: "ubigeo",
				  method: "POST",
				  data: { f : "listarDistritos",
					  coddpto:global_coddpto,
					  codprov:this.value},
				  dataType: "json",
				  
		});
		request.done(function( distritos ) {
			combo_distritos.prop('disabled', false);
			combo_distritos.find('option').remove();
			$(distritos).each(function(i, v){ 
				if(i==0)
					combo_distritos.append(optionVacio);
					combo_distritos.append('<option value="' + v.coddist + '">' + v.nombre + '</option>');
				});
			});
	});
});

function cargarProvincias(){
	
}