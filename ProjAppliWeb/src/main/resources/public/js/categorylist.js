function clearCategoryFormInputs() {
	$('#name').removeClass('error');
	$('#err-name').html("");
	$('#TVA').removeClass('error');
	$('#err-TVA').html("");
	
	
	
	$('#formcategory #name').val("");
	$('#formcategory #TVA').val("");
	
}



function deleteCategory(action, id) {
	alert(action);
	//$.ajax({
		$.get(action, {id:id}, function(data) {
	   /* url: action,
	    type: 'GET',
	    dataType: 'json',
	    data: { id: id },
	    contentType: 'application/json; charset=utf-8',*/
	    //mimeType: 'application/json',
	   // success: function(data) {
	    	
	    	if (data.status == 'PASOK') {
				alert(" impossible de détruire une catégorie qui contient des produits !!!")
			} 
			else {
				window.location.href = "/category";
			}
	    
}, 'json');
	//});
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
	    	
	    	alert(data.name);
	    	
	    	clearCategoryFormInputs();
	    	$('#formcategory #tvaid').val(data.tvaid);
	    	$('#formcategory #id').val(data.id);
	    	$('#formcategory #name').val(data.name);
	    	$('#formcategory #tva').val(data.tva);
	    	
	    	
	    	$('#divformcategory .modal-title').html("Editer");
	    	$('#divformcategory').modal('show');

	    }
	   
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
		//alert(data);
		$.post($form.action, data, function(response) {
			
			//alert(response.status);
			
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
