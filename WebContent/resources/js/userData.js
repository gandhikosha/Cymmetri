$(document).ready(function(){
	$("#userListLink").click(function(){
		$.ajax({
	        "url": "userList",
	        "type": "GET",
	        dataType:'json' ,
	        "accepts": {
	            json: "application/json"
	        },
	        "success": function (data) {
	        	alert("GOING");
	        	console.log(data);
	            $('#userTable').DataTable({
	                data: data,
	                
	                columns: [
	                    { "data": "userId","title":"User ID" },
	                    { "data": "email","title":"Email" },
	                    { "data": "login","title":"Login" },
	                    { "data": "firstName","title":"First Name" },
	                    { "data": "lastName","title":"Last Name" },
	                    { "data": "mobileNo","title":"Mobile No." },
	                    {"title":"Action", mRender: function (data, type, row){ 	
							var str="<a href='showFormForUpdateUser'>Update User</a>"	;
							return str;
                      	}},
	                ]
	            });
	        }
	    });
	});
});
