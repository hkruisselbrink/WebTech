package model;

public class User {
	
	private String lastName, tussenvoegsel, firstName, nickname, password, id;
	private static int lastId = 1000;
	
	
	public User(String lastName,String tussenvoegsel,String firstName,String nickname,String password)
	{
		id = "u" + (lastId + 1);
		this.firstName = firstName;
		this.lastName = lastName;
		this.tussenvoegsel = tussenvoegsel;
		this.nickname = nickname;
		this.password = password;
		lastId++;
	}


	public String getId() {
		return id;
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
	
	public String toString()
	{
		return nickname + password + firstName + tussenvoegsel + lastName;
	}
	

}
