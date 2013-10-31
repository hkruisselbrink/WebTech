var movies = new Object();

var getMovies = function(){
	$.ajax({
		url: "resources/movies",
		dataType: "json",
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.status);
	}).done(function(data){
		$.each(data, function(key, movieObj){
			$('<div class="object-line">')
			.append('<img src="img/noImage.png" alt="poster" id="img' + key + '"</img>')
			.append('<a href="movie.html?id=' + movieObj.ttNumber + '"<p>' + movieObj.title + '</p></a>')
			.append('<div>')
						.append('<img id="star' + key + '1" src="img/unratedStar.png" alt="star1"></img>')
						.append('<img id="star' + key + '2" src="img/unratedStar.png" alt="star1"></img>')
						.append('<img id="star' + key + '3" src="img/unratedStar.png" alt="star1"></img>')
						.append('<img id="star' + key + '4" src="img/unratedStar.png" alt="star1"></img>')
						.append('<img id="star' + key + '5" src="img/unratedStar.png" alt="star1"></img>')
			.append('</div>')
			.append('<div class="clearfix></div></div>')
			.appendTo('#movies');
			
			setMoviePoster(movieObj.ttNumber, "img" + key);
			setStars(movieObj.avgRating, "star" + key);
			
		});
	});
};

var getRatedMovies = function(){
	$.ajax({
		url: "resources/movies/avgRatings",
		dataType: "json",
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.status);
	}).done(function(data){
		$.each(data, function(key, movieObj){
			$('<div class="object-line">')
			.append('<img src="img/noImage.png" alt="poster" id="img' + key + '"</img>')
			.append('<a href="movie.html?id=' + movieObj.ttNumber + '"<p>' + movieObj.title + '</p></a>')
			.append('<div>')
						.append('<img id="star' + key + '1" src="img/unratedStar.png" alt="star1"></img>')
						.append('<img id="star' + key + '2" src="img/unratedStar.png" alt="star1"></img>')
						.append('<img id="star' + key + '3" src="img/unratedStar.png" alt="star1"></img>')
						.append('<img id="star' + key + '4" src="img/unratedStar.png" alt="star1"></img>')
						.append('<img id="star' + key + '5" src="img/unratedStar.png" alt="star1"></img>')
			.append('</div>')
			.append('<div class="clearfix></div></div>')
			.appendTo('#movies');
			
			setMoviePoster(movieObj.ttNumber, "img" + key);
			setStars(movieObj.avgRating, "star" + key);
			
		});
	});
};



$(document).ready(function(){
	var params = getParams();
	var type = params['type'];
	
	if(type == undefined){
		getMovies();
	}
	else if(type == "rated"){
		getRatedMovies();
	}
	
});