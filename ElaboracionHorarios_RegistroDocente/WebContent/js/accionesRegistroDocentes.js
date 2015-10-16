$( document ).ready(function() {
	
	function initData ()  {
        var rows = [];
        /*for (var i = 0; i < 0; i++) {
        	rows.push({
                id_telefono: i,
                telefono: ''
            });
        }*/
        return rows;
    }
	
	// init table use data
    $table = $('#table_telefono,#table_email,#table_documento').bootstrapTable({
        data: initData()
    });
    
	function append_telefono(){
		var telefono=$('#telefono').val();
		if(telefono!=''){
			var rows = [];
			rows.push({
	            id: -1,
	            telefono: telefono,
	            id_local: telefono
	        });
			$('#telefono').val('');
			return rows;
		}
		else
			alert("Complete el campo telefono");
		return null;
	}
	function append_documento(){
		var numero=$('#numero').val();
		var tipodoc=$('#tipodoc').val();
		if(numero!=''&&tipodoc!=''){
			var rows = [];
			rows.push({
	            id: -1,
	            numero: numero,
	            tipodoc:tipodoc,
	            id_local: numero
	        });
			$('#numero').val('');
			return rows;
		}
		else
			alert("Complete todos los campos de documento");
		return null;
	}
	function append_email(){
		var email=$('#email').val();
		if(email!=''){
			var rows = [];
			rows.push({
	            id: -1,
	            email: email,
	            id_local: email
	        });
			$('#email').val('');
			return rows;
		}
		else
			alert("Complete el campo E-mail");
		return null;
	}
	
	$(".btn").on('click', function() {
		var tabla=$(this).data('table');
		var table=$('#table_'+tabla);
		var accion = $(this).attr('data-method');
		var valueInput=$('#'+tabla).val();
		if(accion=='append'){
			var data=eval(accion+"_"+tabla).call();
			var dataTable = table.bootstrapTable('getData');
			//comprobar que e valor no exista en la tabla
			for(var i=0;i<dataTable.length;i++){
				if(dataTable[i].id_local==valueInput)
					data=null;
			}
			if(data!=null)
			table.bootstrapTable(accion,data);
		}else{
			
			var selects = table.bootstrapTable('getSelections');

            ids = $.map(selects, function (row) {
                return row.id_local;
            });

            table.bootstrapTable('remove', {
                field: 'id_local',
                values: ids
            });
		}
		
	});

});

