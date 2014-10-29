function editCustomer(action, id) {
	$.ajax({
	    url: action,
	    type: 'GET',
	    data: { id: id },
	    contentType: 'application/json; charset=utf-8', //send
	    dataType: 'json', // response
	    success: function(data) {
	    	clearFormInputs($('#formcustomer'));

	    	$('#formcustomer #id').val(data.id);
	    	$('#formcustomer #lastName').val(data.lastName);
	    	$('#formcustomer #firstName').val(data.firstName);
	    	$.each(data.customerGroups, function(i, id) {
	    		$('#formcustomer #customerGroups option[value="' + id + '"]').prop('selected', true);
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
		clearFormInputs($form);
		
		$('#divformcustomer .modal-title').html("Créer");
		$('#divformcustomer').modal('show');
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
			        if ($('#divformcustomer').data('triggered')) {
			        	$('#divformcustomer').modal('hide').data('triggered',false); // prevents multiple clicks from reopening
			        };
			    }, 3000); // milliseconds
				
				window.location.href = "/customer";
			}
		}, 'json');
		
		return false;
	});
	
	$('.customerGroup').bind('click', function(event) {
		$(this).unbind('click')
		$(this).popover({html:true,
			placement:'right',
			title:'<span>'+ $(this).html() + '</span>',
			content:"<span>Chargement ...</span>"
		}).popover('toggle');
		event.preventDefault();
	});
	
	$('.customerGroup').on('show.bs.popover', function () {
		var linkElem = $(this);

		$.ajax({
		    url: linkElem.attr('action'),
		    type: 'GET',
		    dataType: 'json',
		    data: { id: linkElem.attr('id') },
		    contentType: 'application/json; charset=utf-8',
		    mimeType: 'application/json',
		    success: function(data) {
		    	linkElem.popover('destroy').popover({
					html:true,
					placement:'right',
					title:'<span>'+ data.title + '</span>',
					content:'<span><div>Reduction de ' + data.discount + ' %</div><br /><div>' + data.customers + '</div></span>'
				}).popover('toggle');
		    }
		});
	});
	/*
	$('.customerGroup').on('hide.bs.popover', function () {
		alert('hide');
		//$(this).popover('destroy');
	});
	 */

	// sort col 0 asc and col 1 asc
	var sorting = [[0,0],[1,0]]; 
    $("table").trigger("sorton", [sorting]); 
    
    $("table").trigger("update");
});