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
	$("body").popover({ selector: '[data-toggle=popover]' });
	
	
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
	
	
	$('.customerGroup').on('hide.bs.popover', function () {
		alert("hide=" + $(this).attr('id') );
	});
	$('.customerGroup').on('show.bs.popover', function () {
		alert("show=" + $(this).attr('id') );
	});
	
	
	/*
	 $.extend($.tablesorter.themes.bootstrap, {
		    // these classes are added to the table. To see other table classes available,
		    // look here: http://twitter.github.com/bootstrap/base-css.html#tables
		    table      : 'table table-bordered',
		    caption    : 'caption',
		    header     : 'bootstrap-header', // give the header a gradient background
		    footerRow  : '',
		    footerCells: '',
		    icons      : '', // add "icon-white" to make them white; this icon class is added to the <i> in the header
		    sortNone   : 'bootstrap-icon-unsorted',
		    sortAsc    : 'icon-chevron-up glyphicon glyphicon-chevron-up',     // includes classes for Bootstrap v2 & v3
		    sortDesc   : 'icon-chevron-down glyphicon glyphicon-chevron-down', // includes classes for Bootstrap v2 & v3
		    active     : '', // applied when column is sorted
		    hover      : '', // use custom css here - bootstrap class may not override it
		    filterRow  : '', // filter row class
		    even       : '', // odd row zebra striping
		    odd        : ''  // even row zebra striping
		  });

		  // call the tablesorter plugin and apply the uitheme widget
		  $("table").tablesorter({
		    // this will apply the bootstrap theme if "uitheme" widget is included
		    // the widgetOptions.uitheme is no longer required to be set
		    theme : "bootstrap",

		    widthFixed: true,

		    headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!

		    // widget code contained in the jquery.tablesorter.widgets.js file
		    // use the zebra stripe widget if you plan on hiding any rows (filter widget)
		    widgets : [ "uitheme", "filter", "zebra" ],

		    widgetOptions : {
		      // using the default zebra striping class name, so it actually isn't included in the theme variable above
		      // this is ONLY needed for bootstrap theming if you are using the filter widget, because rows are hidden
		      zebra : ["even", "odd"],

		      // reset filters button
		      filter_reset : ".reset"

		      // set the uitheme widget to use the bootstrap theme class names
		      // this is no longer required, if theme is set
		      // ,uitheme : "bootstrap"

		    }
		  })
		  .tablesorterPager({

		    // target the pager markup - see the HTML block below
		    container: $(".ts-pager"),

		    // target the pager page select dropdown - choose a page
		    cssGoto  : ".pagenum",

		    // remove rows from the table to speed up the sort of large tables.
		    // setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
		    removeRows: false,

		    // output string - default is '{page}/{totalPages}';
		    // possible variables: {page}, {totalPages}, {filteredPages}, {startRow}, {endRow}, {filteredRows} and {totalRows}
		    output: '{startRow} - {endRow} / {filteredRows} ({totalRows})'

		  });
*/
	
	
	
	
});