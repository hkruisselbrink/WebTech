var movies = new Object();
var currentMovieIndex = -1;
var currentMovie = new Object();

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
		dataType:"json",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", accessToken);
        },
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.responseText);
	}).done(function(data){
		movies = data;
		
		$.each(data, function(key, movieObj){
			$('#movieList').append('<li><a href="#" onclick="setCurrentMovie('+key+');">' + movieObj.title + '</a></li>');
			
		});
		
		$('#movieList').listview('refresh');
	});
};

var deleteRating = function(index){
	var id = movies[index].ttNumber;
	var accessToken = localStorage.getItem('accessToken');
	if(accessToken === null){
		alert("Session expired");
		$.mobile.changePage('#loginpage');
	}
	
	$.ajax({
		url: "../resources/movie/" + id + "/rate",
		dataType: "json",
		type: "DELETE",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", accessToken);
        },
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.status);
	}).done(function(data){
		alert("deleted");
	});
};

var setCurrentMovie = function(index){
	currentMovieIndex = index;
	$.mobile.changePage('#moviepage');
};

var rateMovie = function(rating){
	var accessToken = localStorage.getItem("accessToken");
	if(accessToken === null){
		alert("Session expired");
		$.mobile.changePage('#loginpage');
	}

	var isRated = currentMovie.ratedByMe;
	var id = currentMovie.ttNumber;
	
	if(isRated) {
		$.ajax({
			type: "PUT",
			url: "../resources/movie/" + id + "/rate",
			beforeSend: function (request)
	        {
				//rating en access_token worden meegegeven in de headers
	            request.setRequestHeader("access_token", accessToken);
	            request.setRequestHeader("rating", rating);
	        },
			dataType: "json",
		}).fail(function(jgXHR, textStatus){
			alert("Kan de film niet raten..");
		}).done(function(data){
			$.mobile.changePage('#moviespage');
		});
	}
	else
	{
		$.ajax({
			type: "POST",
			url: "../resources/movie/" + id + "/rate",
			beforeSend: function (request)
	        {
				//rating en access_token worden meegegeven in de headers
	            request.setRequestHeader("access_token", accessToken);
	            request.setRequestHeader("rating", rating);
	        },
			dataType: "json",
		}).fail(function(jgXHR, textStatus){
			alert("Kan de film niet raten..");
		}).done(function(data){
			$.mobile.changePage('#moviespage');
		});
	}
};


$(document).on("pageinit", "#loginpage", function(){
	if(localStorage.getItem('accessToken') != null){
		$.mobile.changePage('#moviespage');
	}
	
});

$(document).on("pageinit", "#moviespage", function(){
	var accessToken = localStorage.getItem('accessToken');
	if(accessToken == null){
		alert('Not logged in');
		$.mobile.changePage('#loginpage');
	}
	
	getMovies(accessToken);
	
});

$(document).on("pageinit", "#moviepage", function(){
	var accessToken = localStorage.getItem('accessToken');
	if(accessToken == null){
		alert('Not logged in');
		$.mobile.changePage('#loginpage');
	}
	currentMovie = movies[currentMovieIndex];
	
	
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

$(document).on('change', '#select-rating', function () {
	var rating =$("#select-rating option:selected").text();
	rateMovie(rating);
});