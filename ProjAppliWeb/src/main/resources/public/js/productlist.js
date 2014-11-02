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
	
	$buttoncreateproduct.bind('click', function(e) {
		clearProductFormInputs();
		
		$('#divformproduct .modal-title').html("Créer un produit");
		$('#divformproduct').modal('show');
	
	});
	
	$form.bind('submit', function(e) {
		var data = $form.serialize();
		
		$.post($form.action, data, function(response) {
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