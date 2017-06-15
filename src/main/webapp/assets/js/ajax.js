$(document).ready(function() {
    
	$("#gastos").click(function() {
      //$('#container').load("show-gastos.jsp");
       $.get('GastosServlet',{ action: null }, function(data) {
    	   $('#container').html(data);         
    	});
    });
	
	$("#ingreso").click(function() {
	      //$('#container').load("show-gastos.jsp");
	       $.get('GastosServlet',{ action: null }, function(data) {
	    	   $('#container').html(data);         
	    	});
	});
	
	$( "#container" ).on( "click", "a", function( event ) {
	    event.preventDefault();
	    var action = $( this ).text();
	    if (action == "Update") {
	    	var id = $(this).attr("data-pid");
	    	$.get('GastosServlet',{ action: "edit", gastoId : id }, function(data) {
	    		$("#addGastoModal").modal("show");
	     	});
	    }
	    if (action == "Delete") {
	    	var id = $(this).attr("data-pid");
	    	$.get('GastosServlet',{ action: "delete", gastoId : id }, function(data) {
		     	   $('#container').html(data);         
		    });
	    }
	});
	
});


