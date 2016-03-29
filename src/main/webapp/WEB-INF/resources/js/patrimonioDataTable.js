$(document).ready(function() {
	$('#tablePatrimonio').DataTable({
		"language": {
	        "url": "/gin/resources/js/Portuguese-Brasil.json"
	    },
	    "columnDefs": [ {
	          "targets": 'no-sort',
	          "orderable": false,
	    } ]
	});
	
});
