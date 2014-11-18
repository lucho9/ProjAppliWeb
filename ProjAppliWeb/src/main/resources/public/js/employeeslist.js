function editEmployee(action, id) {
	$.ajax({
		url: action,
	    type: 'GET',
	    data: { id: id },
	    contentType: 'application/json; charset=utf-8', //send
	    dataType: 'json', // response
	    success: function(data) {
	    	clearFormInputs($('#formemployee'));
	    	
	    	$('#formemployee #id').val(data.id);
	    	$('#formemployee #lastName').val(data.lastName);
	    	$('#formemployee #firstName').val(data.firstName);
	    	$('#formemployee #address').val(data.address);
	    	$('#formemployee #phoneNumber').val(data.phoneNumber);
	    	$('#formemployee #mobileNumber').val(data.mobileNumber);
	    	$('#formemployee #email').val(data.email);
	    	$('#formemployee #birth').val(data.birth);
	    	$('#formemployee #login').val(data.login);
	    	$('#formemployee #password').val(data.password);
	    	$('#formemployee #role option[value="' + data.role + '"]').prop('selected', true);
	    	
	    	//$('#formemployee #password').hide();
	    	$('#divformemployee .modal-title').html("Editer");
	    	$('#divformemployee').modal('show');
	    }
	    /*,
	    error: function(data, status, err) {
	        alert("error: " + data + " status: " + status + " err:" + err);
	    }*/
	});
}

function initFilter() {
	$('#formfilter #filtername').val('');
	$('#formfilter').submit();
}

$(document).ready(function() {
	var $form = $('#formemployee');
	var $buttoncreateemployee = $('#buttoncreateemployee');

	$buttoncreateemployee.bind('click', function(e) {
		clearFormInputs($form);
		
		$('#divformemployee .modal-title').html("Créer");
		$('#divformemployee').modal('show');
	});
	
	$form.bind('submit', function(e) {
		e.preventDefault();
		
		var data = $form.serialize();
		$.post($form.attr('action'), data, function(response) {
			if (response.status == 'FAIL') {
				clearFormErrors($form);
				for (var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					$('#' + item.fieldName).addClass('error');
					$('#err-' + item.fieldName).html(item.message);
				}
			} 
			else {
				window.setTimeout(function() {
			        if ($('#divformemployee').data('triggered')) {
			        	$('#divformemployee').modal('hide').data('triggered',false); // prevents multiple clicks from reopening
			        };
			    }, 3000); // milliseconds
				
				window.location.href = "/employee";
			}
		}, 'json');

		return false;
	});
});