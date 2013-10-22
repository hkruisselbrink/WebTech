package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Rating {
	
	private User user;
	private Movie movie;
	private double rating;
	
	public Rating(User user, Movie movie, double rating)
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

	public double getRating() {
		return rating;
	}

	public void setUser(User user) {this.user = user;}
	public void setRating(double rating) {this.rating = rating;}
	public void setMovie(Movie movie) {this.movie = movie;}
	

}
