var newestMovies = new Object();
var movie = new Object();

var loadNewMovies = function(){
	
	$.ajax({
		url: "resources/movies/newest",
		dataType: "json",		
	}).fail(function(jqXHR, textStatus){
		alert("help");
	}).done(function(data){
		newestMovies = data;
		setSliderMovie(0);
	});
};

var setSliderMovie = function(index){
	movie = newestMovies[index];
	$('#movie-title-slider').text(movie.title);
	$('#movie-description-slider').text(movie.description);
	$('#movie-director-slider').text("Director: " + movie.director);
	$('#movie-length-slider').text("Length: " + movie.length + " min");
	$('#movie-date-slider').text("Date: " + movie.date);
	setMoviePoster(movie.ttNumber, "jumbotron-poster");
	setStars(movie.avgRating, "star");
};



$(document).ready(function(){
	loadNewMovies();
});

