package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	private ArrayList<Movie> movies;
	private ArrayList<Rating> ratings;
	private ArrayList<User> users;
	
	public Model()
	{
		movies = new ArrayList<Movie>();
		ratings = new ArrayList<Rating>();
		users = new ArrayList<User>();
		
		movies.add(new Movie("0120889", 113, "What dreams may come", "Vincent Ward", "Mooie film enzo", "2 Oct 1998"));
		movies.add(new Movie("0373883", 109, "Halloween", "Rob Zombie", "Enge film enzoo", "31 Aug 2007"));
		movies.add(new Movie("0076759", 121, "Star Wars Episode 4 - A New Hope", "George Lucas", "Coole film enzoo", "25 May 1977"));
				
		users.add(new User("Janus", "de", "Henk", "Henkiedejanus9919912", "ikheetjanusvandeachternaam"));
		users.add(new User("Piet", "van", "Diederiksen", "Diederikje1", "ikhouvanpuppies"));

	}
	
	public List<Movie> getAllMovies()
	{
		return movies;
	}
	
	public Movie getMovie(int id)
	{
		for(Movie m : movies)
		{
			if(m.getId() == id)
			{
				return m;
			}
		}
		return null;
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



}

