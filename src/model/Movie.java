package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Movie {
	
	private static int lastId = 1000;
	private int id, length;
	private String title, director, description, date, ttNumber;
	
	public Movie() {
		
	}
	
	public Movie(String ttNumber, int length, String title, String director, String description, String date)
	{
		this.id = lastId;
		this.ttNumber = ttNumber;
		this.length = length;
		this.title = title;
		this.director = director;
		this.description = description;
		this.date = date;
		lastId++;
	}

	@XmlTransient
	public int getId() {
		return id;
	}
	
	public String getTtNumber() {
		return ttNumber;
	}

	public int getLength() {
		return length;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}
	
	public void setLength(int length) { }
	public void setTitle(String title) { }
	public void setDirector(String director) { }
	public void setDescription(String description) { }
	public void setDate(String date) { }
	public void setTtNumber(String ttNumber) { }	
}
