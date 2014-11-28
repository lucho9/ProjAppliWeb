function print() {
	var win = window.open("", "popup", "width=1000,height=600,scrollbars=yes,resizable=yes,toolbar=no,directories=no,location=no,menubar=no,status=no,left=0,top=0");
	var doc = win.document;
	doc.open();
	doc.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
    doc.write("<html>");
    doc.write("<head>");
    doc.write('<link rel="stylesheet" href="/ThemeTemplate/assets/css/bootstrap.css" />');
    doc.write('<link rel="stylesheet" href="/ThemeTemplate/assets/css/font-awesome.min.css" />');
    doc.write("</head>");
    doc.write("<body>");
    doc.write($("#printdiv").html());
    doc.write("</body>");
    doc.write("</html>");
    win.print();
    win.close();
}

$(document).ready(function() {

});