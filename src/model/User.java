package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class User {
	
	private String lastName, tussenvoegsel, firstName, nickname, password, id;
	private static int lastId = 1000;

	public User() {	}
	
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

	@XmlTransient
	@JsonIgnore
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

	@XmlTransient
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	public String toString()
	{
		return nickname + password + firstName + tussenvoegsel + lastName;
	}

	public void setLastName(String lastName) { }
	public void setTussenvoegsel(String tussenvoegsel) { }
	public void setFirstName(String firstName) { }
	public void setNickname(String nickname) { }
	public void setPassword(String password) { }
	public void setId(String id) { }
	
	
}
