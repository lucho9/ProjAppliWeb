function clearFormErrors(form) {
	form.find('span[id^="err-"]').html('');
	form.find('input, textarea').removeClass('error');
}

function clearFormInputs(form) {
	clearFormErrors(form);
	form.find('input:not([type=radio], [name="_csrf"], [name="id"]), textarea').val('');
	form.find('input[name="id"]').val(0);
	form.find('select option').prop('selected', false);
}

$(document).ready(function () {
	/* old menu active current
	try {
		var arrayPathNames = this.location.pathname.split("/");
	    $('a[href="/' + arrayPathNames[1] + '"]').parent().addClass('active');		
	} catch(e) {
	}
	 */
});