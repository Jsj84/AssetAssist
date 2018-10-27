function getTableData(){
	var table = $('#myTable');
	var columnHeaders = table.children(0).find('th:not(:first-child, :last-child)');
	var columns = [];
	$.each(columnHeaders, function(){
		columns.push($(this).text());
	});

	var rows = [];
	var tableRows = $('#myTable tr:not(thead tr)');
	$.each(tableRows, function(){
		var cellData = [];
		var rowColumns = $(this).find('td:not(:first-child, :last-child)');
		$.each(rowColumns, function(){
			cellData.push($(this).text());
		});
		rows.push(cellData);
	});
    
$.ajax({
  url:"fileExport",
  type:"POST",
  data:{
			'columns' : JSON.stringify(columns),
			'rowData' : JSON.stringify(rows)
		},
		
  success: function(response){
  	
    debugger;
  },
  error: function(error){
  	debugger;
  }
});
}

(function() {'use strict'; window.addEventListener('load', function() {
	
	// Fetch all the forms we want to apply custom Bootstrap validation styles to
	var forms = document.getElementsByClassName('needs-validation');
	
	// Loop over them and prevent submission
	var validation = Array.prototype.filter.call(forms, function(form) { form.addEventListener('submit',function(event) {
	if (form.checkValidity() === false) {
		event.preventDefault();
		event.stopPropagation();
		}
form.classList.add('was-validated'); },false);}); }, false);
})();

function hpapi(){
	
	var sn = $('#serialnumber').val();
	var mn = $('#modelnumber').val();
	
	if (sn != "" && mn != "") {
		
		 $.get('hpapi', {
	          	'serialnumber' : sn,  
				'modelnumber' : mn
			},
			function(hpresponse){
				var status = hpresponse['status'];
				var startD = hpresponse['startDate'];
				var endD = hpresponse['endDate'];
				
				if (startD != '' || startD != null){
				
				$('#status').show();
				$('#start').show();
				$('#end').show();
				
				$('#warrenteeStatus').val(status);
				$('#warrenteeStartDate').val(startD);
				$('#warrenteeEndDate').val(endD);
			}
		});
	}
}