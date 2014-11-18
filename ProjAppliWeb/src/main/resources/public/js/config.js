var hasPassword = "0";

function editConfig(form) {
	var $form = $(form);
	var $lastName = $form.find('#lastName').val();
	var $firstName = $form.find('#firstName').val();
	$.ajax({
	    url: $form.attr('action'),
	    type: $form.attr('method'),
	    data: { lastName: $lastName, firstName: $firstName },
	    dataType: 'json', // response
	    success: function(data) {
	    	if (data.status == 'FAIL') {
	    		clearFormErrors($form);
				for (var i = 0; i < data.errorMessageList.length; i++) {
					var item = data.errorMessageList[i];
					$('#' + item.fieldName).addClass('error');
					$('#err-' + item.fieldName).html(item.message);
				}
	    	}
	    	else {
		    	clearFormInputs($('#formconfig'));
		    	$('#formconfig #id').val(data.obj.id);
		    	$('#formconfig #lastName').val(data.obj.lastName);
		    	$('#formconfig #firstName').val(data.obj.firstName);
		    	$('#formconfig #login').val(data.obj.login);
		    	
		    	var $radiologinType = $('#formconfig input:radio[name=loginType]');
		    	$radiologinType.filter('[value=' + data.obj.loginType + ']').prop('checked', true);
		    	
		    	hasPassword = data.obj.hasPassword;
		    	checkValidationFields(data.obj.loginType);
		    	
		    	$('#divformconfig .modal-title').html("Configurer votre compte");
		    	$('#divformconfig').modal('show');
	    	}

	    }
	});
}

function checkValidationFields(loginType) {
	if (loginType == 1) {
		$('#formconfig #divPwd0').hide();
		$('#formconfig #divPwd').hide();
		$('#formconfig #divPwd2').hide();
	} else if (loginType == 0) {
		if (hasPassword == "0") {
			$('#formconfig #divPwd0').hide();
		}
		else {
			$('#formconfig #divPwd0').show();
		}
		$('#formconfig #divPwd').show();
		$('#formconfig #divPwd2').show();
	}
	else if (hasPassword == "0") {
		$('#formconfig #divPwd0').hide();
	}
}

$(document).ready(function() {
	var $formCallConfig = $('#formcallconfig');
	var $form = $('#formconfig');
	
	$formCallConfig.bind('submit', function(e) {
		e.preventDefault();
		editConfig(this);
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
			        if ($('#divformconfig').data('triggered')) {
			        	$('#divformconfig').modal('hide').data('triggered',false); // prevents multiple clicks from reopening
			        };
			    }, 3000); // milliseconds
				
				window.location.href = "/home";
			}
		}, 'json');
		
		return false;
	});
	var $radiologinType = $('#formconfig input:radio[name=loginType]');
	$radiologinType.bind('change', function(e) {
		e.preventDefault();
		checkValidationFields(this.value);
	});
});