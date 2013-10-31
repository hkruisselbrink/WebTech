var login = function(username, password){
	
	var jsonObject = new Object();
	jsonObject['nickname'] = username;
	jsonObject['password'] = password;

	
	$.ajax({
		type: "POST",
		url: "resources/login",
		contentType: "application/json; charset=UTF-8",
		data: JSON.stringify(jsonObject),
		dataType:"json"
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.responseText);
	}).done(function(data){
		var accessToken = data.access_token;
		localStorage.setItem('accessToken', accessToken);
		$.mobile.changePage('#moviespage');
	});
};



$(document).on("pageinit", "#loginpage", function(){
	if(localStorage.getItem('accessToken') != null){
		$.mobile.changePage('#moviespage');
	}
	
});

$(document).on("pageinit", "#moviespage", function(){
	if(localStorage.getItem('accessToken') == null){
		alert('Not logged in');
		$.mobile.changePage('#loginpage');
	}
});

$(document).on('click', '#submit-button', function(){
	var username = $('#username-box').val();
	var password = $('#password-box').val();
	if(username == "" || password == ""){
		alert("Vul alle velden in");
		return;
	}
	
	login(username, password);
});

$(document).on('click', '#logout-button', function(){
	localStorage.removeItem('accessToken');
	$.mobile.changePage("#loginpage");
});