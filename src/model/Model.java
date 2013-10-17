package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
		
		movies.add(new Movie("0120889", 113, "What dreams may come", "Vincent Ward", "Mooie film enzo", "2 Oct 1998"));
		movies.add(new Movie("0373883", 109, "Halloween", "Rob Zombie", "Enge film enzoo", "31 Aug 2007"));
		movies.add(new Movie("0076759", 121, "Star Wars Episode 4 - A New Hope", "George Lucas", "Coole film enzoo", "25 May 1977"));
				
		users.add(new User("Janus", "de", "Henk", "Henkiedejanus9919912", "ikheetjanusvandeachternaam"));
		users.add(new User("Piet", "van", "Diederiksen", "Diederikje1", "ikhouvanpuppies"));

		clients = new HashMap<User, String>();
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
	
	public void addUser(User user) throws IOException
	{
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

