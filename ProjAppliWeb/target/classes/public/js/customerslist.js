function clearCustomerFormInputs() {
	$('#lastName').removeClass('error');
	$('#err-lastName').html("");
	$('#firstName').removeClass('error');
	$('#err-firstName').html("");
	$('#customerGroup').removeClass('error');
	$('#err-customerGroup').html("");
	
	$('#formcustomer #id').val(0);
	$('#formcustomer #lastName').val("");
	$('#formcustomer #firstName').val("");
	$('#formcustomer #customerGroups option').prop('selected', false);
}

function editCustomer(action, id) {
	$.ajax({
	    url: action,
	    type: 'GET',
	    dataType: 'json',
	    data: { id: id },
	    contentType: 'application/json; charset=utf-8',
	    mimeType: 'application/json',
	    success: function(data) {
	    	clearCustomerFormInputs();
	    	
	    	$('#formcustomer #id').val(data.id);
	    	$('#formcustomer #lastName').val(data.lastName);
	    	$('#formcustomer #firstName').val(data.firstName);
	    	$.each(data.customerGroups, function(i, item) {
	    		$('#formcustomer #customerGroups option[value="' + item.id + '"]').prop('selected', true);
	    	});
	    	$('#divformcustomer .modal-title').html("Editer");
	    	$('#divformcustomer').modal('show');

	    }
	    /*,
	    error: function(data, status, err) {
	        alert("error: " + data + " status: " + status + " err:" + err);
	    }*/
	});
}

$(document).ready(function() {
	var $form = $('#formcustomer');
	var $buttoncreatecustomer = $('#buttoncreatecustomer');

	$buttoncreatecustomer.bind('click', function(e) {
		clearCustomerFormInputs();
		
		$('#divformcustomer .modal-title').html("Créer");
		$('#divformcustomer').modal('show');
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
			        if ($('#divformcustomer').data('triggered')) {
			        	$('#divformcustomer').modal('hide').data('triggered',false); // prevents multiple clicks from reopening
			        };
			    }, 3000); // milliseconds
				
				window.location.href = "/customer";
			}
		}, 'json');
		e.preventDefault();
		return false;
	});
});