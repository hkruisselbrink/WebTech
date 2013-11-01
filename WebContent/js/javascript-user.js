


var setUser = function(id, accessToken){
	$.ajax({
		url: "resources/user/" + id,
		dataType: "json",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", accessToken);
        },
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.status);
	}).done(function(data){
		$('#user-nickname').text(data.nickname);
		var name = data.firstName;
		if(!(data.tussenvoegsel === "undefined")){
			name = name + " " + data.tussenvoegsel;
		}
		name = name + " " + data.lastName;
		$('#user-realname').text("Real name: " + name);
	});
};


$(document).ready(function(){
	var params = getParams();
	var id = params['id'];
	accessToken = localStorage.getItem("accessToken");
	if(accessToken === null){
		alert("Please log in to see this user");
	}
	else{
		setUser(id, accessToken);
	}
	
});