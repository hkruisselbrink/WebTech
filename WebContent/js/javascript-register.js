$(document).ready(function(){
	logout();
	
});

$('#submit-register').click(function(){
	var firstname = $('#inputFirstName').val();
	var insertion = $('#inputInsertion').val();
	var lastname = $('#inputLastName').val();
	var nickname = $('#inputNickname').val();
	var password = $('#inputPassword').val();
	
	if(firstname === "" || lastname === "" || nickname === "" || password === "") {
		alert("Foutieve registratiewaarden");
	} else {
		register(firstname, insertion, lastname, nickname, password);
	}
	
});

var register = function(firstname, insertion, lastname, nickname, password){
	var jsonObject = new Object();
	jsonObject['firstName'] = firstname;
	jsonObject['tussenvoegsel'] = insertion;
	jsonObject['lastName'] = lastname;
	jsonObject['nickname'] = nickname;
	jsonObject['password'] = password;

	
	$.ajax({
		type: "POST",
		url: "resources/user",
		contentType: "application/json; charset=UTF-8",
		data: JSON.stringify(jsonObject),
		dataType:"json",
		async: false
	}).fail(function(jgXHR, textStatus){
		
	}).done(function(data){
		window.location.href = "http://localhost:8080/Webtech3/";
	});
};