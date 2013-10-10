package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Rating {
	
	private User user;
	private Movie movie;
	private int rating;
	
	public Rating(User user, Movie movie, int rating)
	{
		this.user = user;
		this.movie = movie;
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public Movie getMovie() {
		return movie;
	}

	public int getRating() {
		return rating;
	}

	public void setUser(User user) { }
	public void setRating(int rating) { }
	public void setMovie(Movie movie) { }
	

}
