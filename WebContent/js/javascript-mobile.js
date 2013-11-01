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
			$('#movieList').append('<li><a href="#moviepage?id=' + key + '">' + movieObj.title + '</a></li>');
			
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
};

var rateMovie = function(index, rating){
	alert("1");
	var accessToken = localStorage.getItem("accessToken");
	if(accessToken === null){
		alert("Session expired");
		$.mobile.changePage('#loginpage');
	}
	alert("2");

	var isRated = movies[index].ratedByMe;
	var id = movies[index].ttNumber;
	
	alert(isRated);
	
	if(isRated) {
		alert("put");
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
		alert("post");
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
			$.mobile.refresh();
		});
	}
};

var getParams = function(){
    var params = {},
        pairs = document.URL.split('?')
               .pop()
               .split('&');

    for (var i = 0, p; i < pairs.length; i++) {
           p = pairs[i].split('=');
           alert(p[0]);
           params[ p[0] ] =  p[1];
    }     

    alert(params['id']);
    return params;
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
	alert("hier");
	var accessToken = localStorage.getItem('accessToken');
	if(accessToken == null){
		alert('Not logged in');
		$.mobile.changePage('#loginpage');
	}
	
	var chosenMovieIndex = getParams()['id'];
	
	alert(chosenMovieIndex);
	
	var movie = movies[chosenMovieIndex];
	
	
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

$(document).on('change', '#select-rating', function () {
	alert("sccie");
});