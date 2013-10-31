package model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class Movie implements Comparable<Movie>{
	
	private static int lastId = 1000;
	private int id, length;
	private String title, director, description, ttNumber;
	private GregorianCalendar date;
	private double avgRating = 0;
	private boolean ratedByMe = false;
	
	public Movie() {
		
	}
	
	public Movie(String ttNumber, int length, String title, String director, String description, GregorianCalendar date)
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
	@JsonIgnore
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

	public double getAvgRating() {
		return avgRating;
	}
	
	public boolean getRatedByMe(){
		return ratedByMe;
	}

	public String getDate() {
		SimpleDateFormat jsonDateFormat =  new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
		return jsonDateFormat.format(date.getTime());
	}
	
	public void setAvgRating(double avgRating) 
	{
		System.out.println("setAvgRating" + avgRating);
		this.avgRating = avgRating;
	}
	
	public void setRatedByMe(boolean status)
	{
		ratedByMe = status;
	}
	
	public void setLength(int length) { }
	public void setTitle(String title) { }
	public void setDirector(String director) { }
	public void setDescription(String description) { }
	public void setDate(String date) { }
	public void setTtNumber(String ttNumber) { }

	@Override
	public int compareTo(Movie arg0) {
		if(date.after(arg0.date))
		{
			return -1;
		}
		else if(date.before(arg0.date))
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}	
}
