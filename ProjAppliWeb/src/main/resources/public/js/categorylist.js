function clearCategoryFormInputs() {
	$('#name').removeClass('error');
	$('#err-name').html("");
	$('#stock').removeClass('error');
	$('#err-TVA').html("");
	
	
	
	$('#formcategory #name').val("");
	$('#formcategory #TVA').val("");
	
}







function editCategory(action, id) {
	alert("erreur");
	$.ajax({
	    url: action,
	    type: 'GET',
	    dataType: 'json',
	    data: { id: id },
	    contentType: 'application/json; charset=utf-8',
	    mimeType: 'application/json',
	    success: function(data) {
	    	
	    	alert("erruer");
	    	clearCategoryFormInputs();
	    	
	    	
	    	$('#formcategory #name').val(data.name);
	    	$('#formcategory #TVA').val(data.TVA);
	    	
	    	
	    	$('#divformcategory .modal-title').html("Editer");
	    	$('#divformcategory').modal('show');

	    }
	    /*,
	    error: function(data, status, err) {
	        alert("error: " + data + " status: " + status + " err:" + err);
	    }*/
	});
}

$(document).ready(function() {
	var $form = $('#formcategory');
	var $buttoncreatecategory = $('#buttoncreatecategory');

	
	
	$buttoncreatecategory.bind('click', function(e) {
		clearCategoryFormInputs();
		alert("erreur create cat");
		$('#divformcategory .modal-title').html("Créer une catégorie");
		$('#divformcategory').modal('show');
	
	});
	
	
	

	
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
			        if ($('#divformcategory').data('triggered')) {
			        	$('#divformcategory').modal('hide').data('triggered',false); // prevents multiple clicks from reopening
			        };
			    }, 3000); // milliseconds
				
				window.location.href = "/category";
			}
		}, 'json');
		e.preventDefault();
		return false;
	});
});
