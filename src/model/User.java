package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class User {
	
	private String lastName, tussenvoegsel, firstName, nickname, password, id;
	private static int lastId = 1000;

	public User()
	{
		id = "u" + (lastId + 1);
		lastId++;
	}
	
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

	
	@SuppressWarnings("unused")
	private void setLastName(String lastName) {this.lastName = lastName; }
	@SuppressWarnings("unused")
	private void setTussenvoegsel(String tussenvoegsel) {this.tussenvoegsel = tussenvoegsel; }
	@SuppressWarnings("unused")
	private void setFirstName(String firstName) {this.firstName = firstName; }
	@SuppressWarnings("unused")
	private void setNickname(String nickname) {this.nickname = nickname; }
	@SuppressWarnings("unused")
	private void setPassword(String password) {this.password = password; }
	
	
	
	
}
