var user = new Object();

var logout = function(){
	if(localStorage.getItem('accessToken') != null){
		localStorage.removeItem('accessToken');
		$('#login-form').show();
		$('#logged-in-div').hide();
	}
};

var getUser = function(accessToken)
{
	$.ajax({
		url: "resources/user/me",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", 1);
        },
		dataType: "json",
	}).fail(function(jqXHR, textStatus){
		if(jqXHR === 404){
			alert("not found");
		}
	}).done(function(data){
		user = data;
		$('#logged-in-as').text("Logged in as: " + user.nickname);
		$('#logged-in-div').show();
		$('#login-form').hide();
		
	});
};

var getAccessToken = function(username, password){
	var jsonObject = new Object();
	jsonObject['nickname'] = username;
	jsonObject['password'] = password;

	
	$.ajax({
		type: "POST",
		url: "resources/login",
		contentType: "application/json; charset=UTF-8",
		data: JSON.stringify(jsonObject),
		dataType:"json",
		async: false
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.status);
	}).done(function(data){
		var accessToken = data.access_token;
		localStorage.setItem('accessToken', accessToken);
		getUser(accessToken);
	});
};

$(document).ready(function(){
	var accessToken = localStorage.getItem('accessToken');
	
	if(accessToken === null){
		$('#login-form').show();
		$('#logged-in-div').hide();
	}else{
		getUser(accessToken);
	};	
	
});

$('#login-form').submit(function(){
	var username = $('#username-box').val();
	var password = $('#password-box').val();
	
	if(username === "" || password === ""){
		alert("null homo");
	}else{
		getAccessToken(username, password);
	};
});

$('#logout-button').click(function(){
	logout();
});


