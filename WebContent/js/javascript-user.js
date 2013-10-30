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

var setUser = function(id){
	$.ajax({
		url: "resources/user/" + id,
		dataType: "json",
		beforeSend: function (request)
        {
            request.setRequestHeader("access_token", 1);
        },
	}).fail(function(jgXHR, textStatus){
		alert(jgXHR.status);
	}).done(function(data){
		$('#user-nickname').text(data.nickname);
		var name = data.firstName;
		if(!(data.tussenvoegsel === "undefined")){
			name = name + " " + data.tussenvoegsel;
		}
		name = name + " " + data.lastName;
		$('#user-realname').text("Real name: " + name);
	});
};


$(document).ready(function(){
	var params = getParams();
	var id = params['id'];
	setUser(id);
	
});