$(document).ready(function() {
	
	//var $caissecat = $('#caissecat');

	
	$("#changeCustomer").bind('change',function(e) {
		
		var item = $(this).val();
		var url = '/custCaisse?id=' + item;
		window.location.href = url;
		
	
	}); 
	

});