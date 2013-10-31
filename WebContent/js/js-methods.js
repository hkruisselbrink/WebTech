var setStars = function(rating, tag){
	
	if(rating >= 0.75){
		$('#' + tag + '1').attr("src", "img/rated-star.png");
	}
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



var setMoviePoster = function(id, tag){
	
	$.ajax({
		url: "http://www.omdbapi.com/?i="+ id,
		dataType: "json",
	}).fail(function(jqXHR, textStatus){
		alert(textStatus);
	}).done(function(data){
		$('#' + tag).attr("src", data.Poster);
		
	});
};

$(document).ready(function(){
	
});

$('#rate-select').change(function(){
	var rating = $("#rate-select option:selected").text();
	rateMovie(rating);
});

var rateMovie = function(rating){
	alert("ha");
	$.ajax({
		type: "POST",
		url: "resources/movie/" + movie.ttNumber + "/rate",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", 1);
            request.setRequestHeader("rating", rating);
        },
		dataType: "json",
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.status);
	}).done(function(data){
		alert("yay");
	});
	alert("yayyayaya");
};

var getParams = function(){
	alert("im here");

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
