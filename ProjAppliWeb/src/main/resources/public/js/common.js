$(document).ready(function () {

	try {
		var arrayPathNames = this.location.pathname.split("/");
	    $('a[href="/' + arrayPathNames[1] + '"]').parent().addClass('active');		
	} catch(e) {
		
	}

});

function collectFormData(fields) {
	var data = {};
	for (var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val();
	}
	return data;
}