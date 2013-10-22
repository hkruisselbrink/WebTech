package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Multiset.Entry;

public class Model {
	
	private ArrayList<Movie> movies;
	private ArrayList<Rating> ratings;
	private ArrayList<User> users;
	private HashMap<User, String> clients;
	
	public Model()
	{
		movies = new ArrayList<Movie>();
		ratings = new ArrayList<Rating>();
		users = new ArrayList<User>();
		
		movies.add(new Movie("tt0120889", 113, "What dreams may come", "Vincent Ward", "Mooie film enzo", new GregorianCalendar(1998, 9, 2)));
		movies.add(new Movie("tt0373883", 109, "Halloween", "Rob Zombie", "Enge film enzoo",new GregorianCalendar(2007, 7, 31)));
		movies.add(new Movie("tt0076759", 121, "Star Wars Episode 4 - A New Hope", "George Lucas", "Coole film enzoo", new GregorianCalendar(1977, 4, 25)));
				
		users.add(new User("Janus", "de", "Henk", "Henkiedejanus9919912", "ikheetjanusvandeachternaam"));
		users.add(new User("Piet", "van", "Diederiksen", "Diederikje1", "ikhouvanpuppies"));

		clients = new HashMap<User, String>();
	}
	
	public User checkAccessToken(String accessToken)
	{
		if(clients.containsValue(accessToken))
		{
			for(java.util.Map.Entry<User, String> entry : clients.entrySet())
			{
				if(accessToken.equals(entry.getValue()))
				{
					return entry.getKey();
				}
			}
		}
		
		return null;
	}
	
	public String addClient(User user) {
		if(clients.containsKey(user)) {
			return clients.get(user);
		} else {
			String token = createToken();
			clients.put(user, token);
			return token;
		}
	}
	
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	public void addRating(Movie movie, User user, double rating)
	{
		Rating ratingObj = new Rating(user, movie, rating);
		ratings.add(ratingObj);
		movie.setAvgRating(getAvgRatingMovie(movie));
		System.out.println(ratings.toString());
	}
	
	public void changeRating(Rating rating, double ratingNumber, Movie movie)
	{
		rating.setRating(ratingNumber);
		movie.setAvgRating(getAvgRatingMovie(movie));
		System.out.println(ratings.toString());
	}
	
	public void removeRating(Rating rating, Movie movie) {
		ratings.remove(rating);
		movie.setAvgRating(getAvgRatingMovie(movie));
	}
	
	public Rating getRating(Movie movie, User user)
	{
		List<Rating> tempRatings = getAllRatingsMovie(movie);
		for(Rating r : tempRatings)
		{
			if(r.getUser().equals(user))
			{
				return r;
			}
		}
		return null;
	}
	
	public void deleteRating(Movie movie, User user)
	{
		for(Rating r: ratings) {
			if(r.getMovie() == movie && r.getUser() == user) {
				ratings.remove(r);
			}
		}
		
	}
	
	public String createToken() {
		int i = 0;
		i = (int) (Math.random()*1000000000);
		String token = Integer.toString(i);
		if (clients.containsValue(token)) {
			return createToken();
		}
		return token;
	}
	
	public List<Movie> getAllMovies()
	{
		return movies;
	}
	
	public Movie getMovie(String id)
	{
		for(Movie m : movies)
		{
			if(m.getTtNumber().equals(id))
			{
				return m;
			}
		}
		return null;
	}
	
	public double getAvgRatingMovie(Movie movie)
	{
		List<Rating> temp = getAllRatingsMovie(movie);
		double total = 0;
		for(Rating r : temp)
		{
			total += r.getRating();
		}
		if(total == 0)
		{
			return 0;
		}
		return total/temp.size();
	}
	
	public List<Rating> getAllRatingsMovie(Movie movie)
	{
		List<Rating> temp = new ArrayList<Rating>();
		for(Rating r : ratings)
		{
			if(r.getMovie().equals(movie))
			{
				temp.add(r);
			}
		}
		
		return temp;
	}
	
	public List<User> getAllUsers()
	{
		return users;
	}
	
	
	public User getUser(String id)
	{
		for(User u : users)
		{
			if(u.getId().equals(id))
			{
				return u;
			}
		}
		
		return null;
	}
	
	public void addUser(User user) throws IOException
	{
		if(!user.userComplete())
		{
			throw new IOException("User not complete");
		}
		
		for(User u : users)
		{
			if(u.getNickname().equals(user.getNickname()))
			{
				throw new IOException("user already exists");
			}
		}
		
		//check of het niet leeg is
		
		users.add(user);
	}



}

