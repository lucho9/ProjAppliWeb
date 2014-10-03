$(document).ready(function () {

	try {
		var arrayPathNames = this.location.pathname.split("/");
	    $('a[href="/' + arrayPathNames[1] + '"]').parent().addClass('active');		
	} catch(e) {
		
	}

});