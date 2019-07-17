function populateTable(data){	

    var removeMe = document.getElementById('removeMe');
	removeMe.innerHTML = "Loading your table...";
	
	//create the table
	  var table = document.getElementById("tab");
	  var row = table.insertRow(0);
	  var cell1 = row.insertCell(0);
	  var cell2 = row.insertCell(1);
	  var cell3 = row.insertCell(2);
	  var cell4 = row.insertCell(3);
	  var cell5 = row.insertCell(4);
	  var cell6 = row.insertCell(5);
	  var cell7 = row.insertCell(6);
	  var cell8 = row.insertCell(7);
	  var cell9 = row.insertCell(8);
	  var cell10 = row.insertCell(9);
	  cell1.innerHTML = '<strong>ID</strong>';
	  cell2.innerHTML = '<strong>Amount</strong>';
	  cell3.innerHTML = '<strong>Submitted time</strong>';
	  cell4.innerHTML = '<strong>Resolved time</strong>';
	  cell5.innerHTML = '<strong>Description</strong>';
	  cell6.innerHTML = '<strong>Receipt</strong>';
	  cell7.innerHTML = '<strong>Submitter ID</strong>';
	  cell8.innerHTML = '<strong>Resolver ID</strong>';
	  cell9.innerHTML = '<strong>Type</strong>';
	  cell10.innerHTML = '<strong>Status</strong>';

	  
	//sort data by id, descending
	data.sort(function(a, b) {
	    return parseFloat(b.reimd_id) - parseFloat(a.reimd_id);
	});
	
	//go through the json
	for(var i = 0; i < data.length; i++) {
	  
	  //create a new row at the bottom of the table
	  var row = table.insertRow(-1);
	  var cell1 = row.insertCell(0);
	  var cell2 = row.insertCell(1);
	  var cell3 = row.insertCell(2);
	  var cell4 = row.insertCell(3);
	  var cell5 = row.insertCell(4);
	  var cell6 = row.insertCell(5);
	  var cell7 = row.insertCell(6);
	  var cell8 = row.insertCell(7);
	  var cell9 = row.insertCell(8);
	  var cell10 = row.insertCell(9);
	  
	  //populate cells
	  
	  cell1.innerHTML = data[i].reimd_id;
	  cell2.innerHTML = "$" + data[i].reimb_amount + ".00";
	  cell3.innerHTML = data[i].reimb_submitted;
	  
	  //if it has not been resolved this will show 'Not resolved'
	  cell4.innerHTML = data[i].reimb_resolved;
	  cell5.innerHTML = data[i].reimb_description;
	  
	  //get the image from the array, and add link to it on click
	  var j = i+1;
	  cell6.innerHTML = '<img width=40px src="http://localhost:8080/static/' + data[i].reimd_id +'.png"'
	  + 'onclick="window.open(\'http://localhost:8080/static/' + data[i].reimd_id +'.png\', \'_blank\');" />';
  				  
	  cell7.innerHTML = data[i].reimb_author;
	  
	  //switch if it has been resolved or not
	  var resolver = data[i].reimb_resolver;
	  if(resolver == 0){
		  cell8.innerHTML = 'N/A';
	  }else{
		  cell8.innerHTML = data[i].reimb_resolver;
	  }
	  
	  //type switch
	  var type = data[i].reimb_type_id;
	  switch(type) {
	  case 1: cell9.innerHTML = 'Lodging';
	    break;
	  case 2: cell9.innerHTML = 'Travel';
	    break;
	  case 3: cell9.innerHTML = 'Food';
	    break;
	  case 4: cell9.innerHTML = 'Other';
	    break;
	  }
	  
	  //status switch
	  var status = data[i].reimb_status_id;
	  switch(status) {
	  case 1: cell10.innerHTML = 'Pending';
	    break;
	  case 2: cell10.innerHTML = 'Approved';
	    break;
	  case 3: cell10.innerHTML = 'Denied';
	    break;
	  }

	}//end of for loop

	

	if (data.length === 0){
		removeMe.innerHTML = "No results to display"; 
		tab.innerHTML =""
		//if there are no results in the returned array
	}else{
		removeMe.innerHTML = "";
		//if there are results remove the loading text
	}
}