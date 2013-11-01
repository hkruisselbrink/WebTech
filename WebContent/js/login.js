
var user = new Object();

var logout = function(){
	if(localStorage.getItem('accessToken') != null){
		localStorage.removeItem('accessToken');
		localStorage.removeItem('username');
		$('#login-form').show();
		$('#logged-in-div').hide();
		$('#my-ratings').hide();
		
	}
};

var getUser = function(accessToken)
{
	$.ajax({
		url: "resources/user/me",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", accessToken);
        },
		dataType: "json",
	}).fail(function(jqXHR, textStatus){
		if(jqXHR === 404){
			alert("not found");
		}
	}).done(function(data){
		user = data;
		$('#logged-in-as').text("Logged in as: " + user.nickname);
		localStorage.setItem('username', user.nickname);
		
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
		$('#my-ratings').hide();
	}else{
		$('#login-form').hide();
		$('#logged-in-div').show();
		var username = localStorage.getItem('username');
		if(username === null){
			getUser(accessToken);
		}
		else{
			$('#logged-in-as').text("Logged in as: " + username);
		}
		
		
		
	};	
	
});

$('#submit-login').click(function(){
	var username = $('#username-box').val();
	var password = $('#password-box').val();
	
	if(username === "" || password === ""){
		alert("Vul alle velden in");
	}else{
		getAccessToken(username, password);
		$('#my-ratings').show();
		location.reload();
	};
});

$('#logout-button').click(function(){
	logout();
	window.location.href = "http://localhost:8080/Webtech3/";
});


