package model;

public class Movie {
	
	private static int lastId = 1000;
	private int id, length;
	private String title, director, description, date, ttNumber;
	
	public Movie(String ttNumber, int length, String title, String director, String description, String date)
	{
		this.id = lastId + 1;
		this.ttNumber = ttNumber;
		this.length = length;
		this.title = title;
		this.director = director;
		this.description = description;
		this.date = date;
	}

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
	
	

}
