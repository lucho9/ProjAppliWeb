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
	    	$('#formcustomer #phoneNr').val(data.phoneNr);
	    	$('#formcustomer #address').val(data.address);
	    	$('#formcustomer #email').val(data.email);
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

function initFilter() {
	$('#formfilter #filtername').val('');
	$('#formfilter').submit();
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

	$.extend($.tablesorter.themes.bootstrap, {
		// these classes are added to the table. To see other table classes
		// available,
		// look here: http://twitter.github.com/bootstrap/base-css.html#tables
		table : 'table table-bordered',
		caption : 'caption',
		header : 'bootstrap-header', // give the header a gradient background
		footerRow : '',
		footerCells : '',
		icons : '', // add "icon-white" to make them white; this icon class is
					// added to the <i> in the header
		sortNone : 'bootstrap-icon-unsorted',
		sortAsc : 'icon-chevron-up glyphicon glyphicon-chevron-up', // includes
																	// classes
																	// for
																	// Bootstrap
																	// v2 & v3
		sortDesc : 'icon-chevron-down glyphicon glyphicon-chevron-down', // includes
																			// classes
																			// for
																			// Bootstrap
																			// v2 &
																			// v3
		active : '', // applied when column is sorted
		hover : '', // use custom css here - bootstrap class may not override it
		filterRow : '', // filter row class
		even : '', // odd row zebra striping
		odd : '' // even row zebra striping
	});

	// call the tablesorter plugin and apply the uitheme widget
	$("table").tablesorter({
		
		// this will apply the bootstrap theme if "uitheme" widget is included
		// the widgetOptions.uitheme is no longer required to be set
		theme : "bootstrap",
		widthFixed : true,
		headerTemplate : '{content} {icon}', // new in v2.7. Needed to add
												// the bootstrap icon!

		// widget code contained in the jquery.tablesorter.widgets.js file
		// use the zebra stripe widget if you plan on hiding any rows (filter
		// widget)
		widgets : [ "uitheme", "filter", "zebra", "resizable" ],
		widgetOptions : {
			// using the default zebra striping class name, so it actually isn't
			// included in the theme variable above
			// this is ONLY needed for bootstrap theming if you are using the
			// filter widget, because rows are hidden
			zebra : [ "even", "odd" ],

			// reset filters button
			filter_reset : ".reset"

		// set the uitheme widget to use the bootstrap theme class names
		// this is no longer required, if theme is set
		// ,uitheme : "bootstrap"

		},
		
		sortList : [[0,0],[1,0]]
	}).tablesorterPager({
		// target the pager markup - see the HTML block below
		container : $(".ts-pager"),
        positionFixed: false,

        // target the pager page select dropdown - choose a page
		cssGoto : ".pagenum",

		// remove rows from the table to speed up the sort of large tables.
		// setting this to false, only hides the non-visible rows; needed if you
		// plan to add/remove rows with the pager enabled.
		removeRows : false,

		// output string - default is '{page}/{totalPages}';
		// possible variables: {page}, {totalPages}, {filteredPages},
		// {startRow}, {endRow}, {filteredRows} and {totalRows}
		output : '{startRow} - {endRow} / {filteredRows} ({totalRows})',
		
		//
		size : 10,
		
		// starting page of the pager (zero based index)
	    page: 0
	});
	
	// sort col 0 asc and col 1 asc
	//var sorting = [[0,0],[1,0]]; 
    //$("table").trigger("sorton", [sorting]); 
    //$("table").trigger("update");
});