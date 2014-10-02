$(document).ready(function () {
	$('#formCustomer').submit(function(event) {
	       alert($('#customer').val());
	       alert($('#model').val());
	      var customer = $('#customer').val();
	      var model = $('#model').val();
	      var json = { "producer" : producer, "model" : model};
	       
	    $.ajax({
	        url: "/customer",
	        data: JSON.stringify(json),
	        type: "POST",
	         
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(smartphone) {
	            var respContent = "";
	             
	            respContent += "<span class='success'>Smartphone was created: [";
	            respContent += smartphone.producer + " : ";
	            respContent += smartphone.model + " : " ;
	            respContent += smartphone.price + "]</span>";
	             
	            $("#sPhoneFromResponse").html(respContent);        
	        }
	    });
	      
	    event.preventDefault();
	  });
});