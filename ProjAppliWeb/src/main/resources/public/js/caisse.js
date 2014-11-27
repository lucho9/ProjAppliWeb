$(document).ready(function() {
	
	//var $caisselistener = $('#caisselistener');
	//if($(this).val())
	
	
	$("#changeCustomer").bind('change',function(e) {
		
		var item = $(this).val();
		var url = '/custCaisse?id=' + item;
		window.location.href = url;
		
	
	}); 
	
	$('input[name=MP]').bind('change',function(e){
	    
		var item = $(this).val();
		var url = '/cbCaisse?id=' + item;
		window.location.href = url;
		

	});

});