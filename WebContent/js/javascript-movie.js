var movie = new Object();

var setMovie = function(id){
	var accessToken = localStorage.getItem("accessToken");
	if(accessToken === null){
		accessToken = "";
	}
	alert(accessToken);
	$.ajax({
		url: "resources/movie/" + id,
		dataType: "json",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", accessToken);
        },
	}).fail(function(jgXHR, textStatus){
		alert("faaal");
	}).done(function(data){
		movie = data;
		$('#movie-title-slider').text(data.title);
		$('#movie-description-slider').text(data.description);
		$('#movie-director-slider').text("Director: " + data.director);
		$('#movie-length-slider').text("Length: " + data.length + " min");
		$('#movie-date-slider').text("Date: " + data.date);
		setMoviePoster(data.ttNumber, "jumbotron-poster");
		setStars(data.avgRating, "star");
		alert(movie.ratedByMe);

	});
};

$(document).ready(function(){
	var params = getParams();
	var id = params['id'];
	setMovie(id);
	
});