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

var getMovies = function(accessToken){
	$.ajax({
		url: "../resources/movies",
		dataType:"json"
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.responseText);
	}).done(function(data){
		movies = data;
		
		$.each(data, function(key, movieObj){
			$('#movieList').append('<li><a href="#">' + movieObj.title + '</a></li>');
		});
		
		$('#movieList').listview('refresh');
	});
};

var rateMovie = function(index){
	var accessToken = localStorage.getItem("accessToken");
	if(accessToken === null){
		alert("Session expired");
		$.mobile.changePage('#loginpage');
	}

	var isRated = movies[index].ratedByMe;
	
	if(isRated){
		$.ajax({
			url: "../resources/user",
		})
	}
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
	
	getMovies();
	
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

$(document).on('click', '#rating', function(){
	alert("hahe");
	alert($(this).text());
});