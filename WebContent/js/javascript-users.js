var getUsers = function(){
	accessToken = localStorage.getItem('accessToken');
	if(accessToken == null){
		alert("You're not logged in");
		return;
	}
	$.ajax({
		url: "resources/users",
		dataType: "json",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", accessToken);
        },
	}).fail(function(jgXHR, textStatus){
		if(jgXHR.responseText === "Invalid access token"){
			alert("You're not logged in");
		}
	}).done(function(data){
		accessToken = localStorage.getItem("accessToken");
		if(accessToken === null){
			$.each(data, function(key, userObj){
				$('<div class="object-line">')
				.append('<img src="img/noImage.png" alt="profile pic"</img>')
				.append('<a href="#"<p>' + userObj.nickname + '</p></a>')
				.append('<div class="clearfix></div></div>')
				.appendTo('#users');
			});
		}
		else{
			$.each(data, function(key, userObj){
				$('<div class="object-line">')
				.append('<img src="img/noImage.png" alt="profile pic"</img>')
				.append('<a href="user.html?id=' + userObj.id + '"<p>' + userObj.nickname + '</p></a>')
				.append('<div class="clearfix></div></div>')
				.appendTo('#users');
			});
		}
		
	});
};

$(document).ready(function(){
	getUsers();
});