package model;

public class User {
	
	private String lastName, tussenvoegsel, firstName, nickname, password;
	
	
	public User(String lastName,String tussenvoegsel,String firstName,String nickname,String password)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.tussenvoegsel = tussenvoegsel;
		this.nickname = nickname;
		this.password = password;
	}


	public String getLastName() {
		return lastName;
	}


	public String getTussenvoegsel() {
		return tussenvoegsel;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getNickname() {
		return nickname;
	}


	public String getPassword() {
		return password;
	}
	

}
