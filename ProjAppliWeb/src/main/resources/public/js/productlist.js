function clearProductFormInputs() {
	$('#name').removeClass('error');
	$('#err-name').html("");
	$('#stock').removeClass('error');
	$('#err-stock').html("");
	$('#prix').removeClass('error');
	$('#err-prix').html("");
	$('#category').removeClass('error');
	$('#err-category').html("");
	
	//$('#formproduct #id').val(0);
	$('#formproduct #name').val("");
	$('#formproduct #stock').val("");
	$('#formproduct #prix').val("");
	$('#formproduct #category option').prop('selected', false);
}







function editProduct(action, id) {
	//alert("erreur");
	$.ajax({
	    url: action,
	    type: 'GET',
	    dataType: 'json',
	    data: { id: id },
	    contentType: 'application/json; charset=utf-8',
	    mimeType: 'application/json',
	    success: function(data) {
	    	clearProductFormInputs();
	    	
	    	$('#formproduct #id').val(data.id);
	    	$('#formproduct #name').val(data.name);
	    	$('#formproduct #stock').val(data.stock);
	    	$('#formproduct #prix').val(data.prix);
	    	$.each(data.category, function(i, item) {
	    		$('#formproduct #category option[value="' + item.id + '"]').prop('selected', true);
	    	});
	    	$('#divformproduct .modal-title').html("Editer");
	    	$('#divformproduct').modal('show');

	    }
	    /*,
	    error: function(data, status, err) {
	        alert("error: " + data + " status: " + status + " err:" + err);
	    }*/
	});
}

$(document).ready(function() {
	var $form = $('#formproduct');
	var $buttoncreateproduct = $('#buttoncreateproduct');
	var $reinitialise = $('#reinitialise');
	var $formnewcat= $('#formnewcat');
	var $buttoncreatecat = $('#buttoncreatecat');
	
	$("#ok").hide(); 


	$("#rch").bind('change',function(e) {
		
		if ($(this).val()=="case4") {
			$("#ok").show();
			$("#rchinput").hide();
			
		} else {
			$("#ok").hide(); 
			$("#rchinput").show();
		}
	}); 

	
	$reinitialise.bind('click', function(e) {
		e.preventDefault();
		
		$("#rchinput").val("");
		
		document.form2.submit();
	});


	
	
	
	$buttoncreateproduct.bind('click', function(e) {
		clearProductFormInputs();
		
		$('#divformproduct .modal-title').html("Créer un produit");
		$('#divformproduct').modal('show');
	
	});
	
	/*
	$formnewcat.bind('submit', function(e) {
		var data = $form.serialize();
		alert(data);
		$.post($form.action, data, function(response) {
			
			alert(response.status);
			
				window.location.href = "/product/newcat";
			
		}, 'json');
		e.preventDefault();
		return false;
	});*/
	

	
	$form.bind('submit', function(e) {
		var data = $form.serialize();
		alert(data);
		$.post($form.action, data, function(response) {
			
			alert(response.status);
			
			if (response.status == 'FAIL') {
				for (var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					$('#' + item.fieldName).addClass('error');
					$('#err-' + item.fieldName).html(item.message);
				}
			} 
			else {

				window.setTimeout(function() {
			        if ($('#divformproduct').data('triggered')) {
			        	$('#divformproduct').modal('hide').data('triggered',false); // prevents multiple clicks from reopening
			        };
			    }, 3000); // milliseconds
				
				window.location.href = "/product";
			}
		}, 'json');
		e.preventDefault();
		return false;
	});
});
