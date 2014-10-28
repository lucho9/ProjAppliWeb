function clearFormErrors(form) {
	//$('#lastName').removeClass('error');
	//$('#err-lastName').html("");
	form.find('span[id^="err-"]').html('');
	form.find('input, textarea').removeClass('error');
}

function clearFormInputs(form) {
	clearFormErrors(form);
	form.find('input:not([name="_csrf"], [name="id"]), textarea').val('');
	form.find('input[name="id"]').val(0);
	form.find('select option').prop('selected', false);
}

$(document).ready(function () {

	try {
		var arrayPathNames = this.location.pathname.split("/");
	    $('a[href="/' + arrayPathNames[1] + '"]').parent().addClass('active');		
	} catch(e) {
		
	}
	
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
		widgets : [ "uitheme", "filter", "zebra" ],
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

		}
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
});