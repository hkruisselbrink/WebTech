var movies = new Object();

var login = function(username, password){
	
	var jsonObject = new Object();
	jsonObject['nickname'] = username;
	jsonObject['password'] = password;
	
	$.ajax({
		type: "POST",
		url: "../resources/login",
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

var refreshMovieList = function(){
	$('#movieList').empty();
	var accessToken = localStorage('accessToken');
	if(accessToken === null){
		alert("Sessie verlopen");
		$.mobile.changePage('#loginpage');
	}
	
	$.ajax({
		url: "../resources/movies",
		dataType:"json",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", accessToken);
        },
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.responseText);
	}).done(function(data){
		movies = data;
		alert("gpot ");
		$.each(data, function(key, movieObj){
			$('#movieList').append('<li><a href="#" onclick="setCurrentMovie('+key+');">' + movieObj.title + '</a></li>');
			
		});
		
		$('#movieList').listview('refresh');
	});
};

$(document).on("pageinit", "#loginpage", function(){
	
	
});

$(document).on("pageshow", "#moviespage", function(){
	refreshMovieList();
	
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

