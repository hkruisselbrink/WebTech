// In dit javascript bestand staan alle functies die in meerdere html files worden gebruikt. De reden dat 
// We hebben alle functies die specifiek voor één html file zijn in aparte javascript files geschreven.


// Geef een rating (bijvoorbeeld 2,5) en de tag van de ratingsterren en de sterren worden aangepast
// aan de rating.
var setStars = function(rating, tag){
	
	// Als de rating groter of gelijk aan 0.75 is wordt de hele ster gevuld en gaat de methode verder.
	if(rating >= 0.75){
		$('#' + tag + '1').attr("src", "img/rated-star.png");
	}
	//als de rating groter of gelijk aan 0.25 is wordt de ster voor de helf gevuld en stopt de methode.
	else if(rating >= 0.25){
		$('#' + tag + '1').attr("src", "img/half-rated.png");
		return;
	}
	
	if(rating >= 1.75){
		$('#' + tag + '2').attr("src", "img/rated-star.png");
	}
	else if(rating >= 1.25){
		$('#' + tag + '2').attr("src", "img/half-rated.png");
		return;
	}
	
	if(rating >= 2.75){
		$('#' + tag + '3').attr("src", "img/rated-star.png");
	}
	else if(rating >= 2.25){
		$('#' + tag + '3').attr("src", "img/half-rated.png");
		return;
	}
	
	if(rating >= 3.75){
		$('#' + tag + '4').attr("src", "img/rated-star.png");
	}
	else if(rating >= 3.25){
		$('#' + tag + '4').attr("src", "img/half-rated.png");
		return;
	}
	
	if(rating >= 4.75){
		$('#' + tag + '5').attr("src", "img/rated-star.png");
	}
	else if(rating >= 4.25){
		$('#' + tag + '5').attr("src", "img/half-rated.png");
		return;
	}
};


// Geef het movie id en de tag van het plaatje dat je wilt veranderen en het plaatje wordt opgehaald
// en laten zien
var setMoviePoster = function(id, tag){
	$.ajax({
		url: "http://www.omdbapi.com/?i="+ id,
		dataType: "json",
	}).fail(function(jqXHR, textStatus){
		alert("Film poster kan niet worden opgehaald..");
	}).done(function(data){
		$('#' + tag).attr("src", data.Poster);
		
	});
};

// Zodra er een rating wordt gegeven wordt die rating in een variabele gezet en wordt de functie 
// rateMovie(rating) aangeroepen
$('#rate-select').change(function(){
	var rating = $("#rate-select option:selected").text();
	rateMovie(rating);
});

//Geef een rating mee en deze methode zal de rating plaatsen.
var rateMovie = function(rating){
	
	var accessToken = localStorage.getItem("accessToken");
	//Als er geen aangemelde gebruiker is stopt de methode
	if(accessToken == null){
		alert("Not logged in");
		return;
	}
	if(movie.ratedByMe) {
		$.ajax({
			type: "PUT",
			url: "resources/movie/" + movie.ttNumber + "/rate",
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
			location.reload();

		});
	}
	else
	{
		$.ajax({
			type: "POST",
			url: "resources/movie/" + movie.ttNumber + "/rate",
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
			location.reload();
		});
	}
};


// Een methode die de query parameters uit de url haalt en deze in een lijst returned.
var getParams = function(){
    var params = {},
        pairs = document.URL.split('?')
               .pop()
               .split('&');

    for (var i = 0, p; i < pairs.length; i++) {
           p = pairs[i].split('=');
           params[ p[0] ] =  p[1];
    }     

    return params;
};

var handleError = function(jgXHR){
	if(jgXHR.responseText === "Invalid access token"){
		alert("Session expired (oftewel accesstoken geldt niet meer ;) )");
		logout();
		window.location.href = "http://localhost:8080/Webtech3/";
	}
	else if(jgXHR.status == 404){
		alert("Gevraagde resource is niet gevonden.");
	}
	else if(jgXHR.responseText === "user already exists"){
		alert("User already exists");
	}
};
