package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Model {
	
	private ArrayList<Movie> movies;
	private ArrayList<Rating> ratings;
	private ArrayList<User> users;
	private HashMap<User, String> clients;
	
	/**
	 * Constructor voor het model. Hier worden de lijsten met data geinitialiseerd en de initiële data wordt hieraan toegevoegd. 
	 */
	public Model()
	{
		movies = new ArrayList<Movie>();
		ratings = new ArrayList<Rating>();
		users = new ArrayList<User>();
		
		Movie m1 = new Movie("tt0120889", 113, "What dreams may come", "Vincent Ward", "Mooie film enzo", new GregorianCalendar(1998, 9, 2));
		Movie m2 = new Movie("tt0373883", 109, "Halloween", "Rob Zombie", "After being committed for 17 years, Michael Myers, now a grown man and still very dangerous, escapes from the mental institution (where he was committed as a 10 year old) and he immediately returns to Haddonfield, where he wants to find his baby sister, Laurie. Anyone who crosses his path is in mortal danger.",new GregorianCalendar(2007, 7, 31));

		movies.add(m1);
		movies.add(m2);
		movies.add(new Movie("tt1981115", 120, "Thor: The Dark World (2013)", "Alan Taylor", "Faced with an enemy that even Odin and Asgard cannot withstand, Thor must embark on his most perilous and personal journey yet, one that will reunite him with Jane Foster and force him to sacrifice everything to save us all.", new GregorianCalendar(2013, 10, 8)));
		movies.add(new Movie("tt1133985", 114, "Green Lantern", "Martin Campbell", "A test pilot is granted an alien ring that bestows him with otherworldly powers, as well as membership into an intergalactic squadron tasked with keeping peace within the universe.", new GregorianCalendar(2011, 6, 17)));
		movies.add(new Movie("tt0111161", 142, "The Shawshank Redemption", "Frank Darabont", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", new GregorianCalendar(1994, 9, 14)));
		movies.add(new Movie("tt0458339", 124, "Captain America: The First Avenger", "Joe Johnston", "After being deemed unfit for military service, Steve Rogers volunteers for a top secret research project that turns him into Captain America, a superhero dedicated to defending USA ideals.", new GregorianCalendar(2011, 5, 22)));
		movies.add(new Movie("tt0120689", 189, "The Green Mile", "Frank Darabont", "The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.", new GregorianCalendar(1999, 11, 10)));
		movies.add(new Movie("tt1201607", 130, "Harry Potter and the Deathly Hallows: Part 2", "David Yates", "Harry, Ron and Hermione search for Voldemort's remaining Horcruxes in their effort to destroy the Dark Lord.", new GregorianCalendar(2011, 6, 15)));
		movies.add(new Movie("tt0241527", 152, "Harry Potter and the Sorcerer's Stone", "Chris Columbus", "Rescued from the outrageous neglect of his aunt and uncle, a young boy with a great destiny proves his worth while attending Hogwarts School of Witchcraft and Wizardry.", new GregorianCalendar(2001, 9, 16)));
		movies.add(new Movie("tt0076759", 121, "Star Wars Episode 4 - A New Hope", "George Lucas", "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a wookiee and two droids to save the universe from the Empire's world-destroying battle-station, while also attempting to rescue Princess Leia from the evil Darth Vader.", new GregorianCalendar(1977, 4, 25)));

		
		
		
		users.add(new User("Janus", "de", "Henk", "Henkiedejanus9919912", "ikheetjanusvandeachternaam"));
		users.add(new User("Piet", "van", "Diederiksen", "test", "test"));
		User temp = new User("Pjotr", "de", "Bruin", "pjoow", "oke");
		users.add(temp);
		addRating(m2, temp, 4.5);

		clients = new HashMap<User, String>();
		clients.put(temp, "1");
		
		Collections.sort(movies);
		
	}
	
	/**
	 * Bekijkt of de meegegeven access token in het systeem aanwezig is. Als deze aanwezig is, wordt de bijbehorende gebruiker terug gegeven.
	 * @param accessToken the access token die gecontroleerd wordt.
	 * @return de user als de access token aanwezig is, anders null.
	 */
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
	
	/**
	 * Bekijkt of een user al aanwezig is in het systeem. Zo niet, dan wordt deze toegevoegd met een nieuwe access token
	 * @param user de user die toegevoegd wil worden
	 * @return de access token die bij de opgegeven user hoort.
	 */
	public String addClient(User user) {
		if(clients.containsKey(user)) {
			return clients.get(user);
		} else {
			String token = createToken();
			clients.put(user, token);
			return token;
		}
	}
	
	/**
	 * Geeft lijst met alle ratings
	 * @return lijst met alle ratings
	 */
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	/**
	 * Controleerd of de opgegeven movie al gerate is door de opgegeven user.
	 * @param movie de movie die gecontroleerd moet worden
	 * @param user de user die gecontroleerd moet worden
	 * @return true als movie door user gerate is, anders false
	 */
	public boolean ratedByUser(Movie movie, User user)
	{
		for(Rating rating : ratings)
		{
			if(rating.getMovie() == movie)
			{
				if(rating.getUser() == user)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Maakt aan de hand van movie, user en rating een rating object. Dit object wordt toegevoegd aan de lijst met ratings. 
	 * Daarna wordt voor de movie nog de gemiddelde rating bepaald en opgeslagen.
	 * @param movie de movie waarvoor een rating wordt toegevoegd
	 * @param user de user die de rating toevoegd
	 * @param rating de rating voor de rating die wordt toegevoegd.
	 */
	public void addRating(Movie movie, User user, double rating)
	{
		Rating ratingObj = new Rating(user, movie, rating);
		ratings.add(ratingObj);
		movie.setAvgRating(getAvgRatingMovie(movie));
	}
	
	/**
	 * Veranderd de rating-variabele van het meegeven rating-object in de meegegeven ratingNumber. 
	 * Daarna wordt voor de meegegeven film de gemiddelde rating bepaald en opgeslagen.
	 * @param rating de rating die aangepast moet worden
	 * @param ratingNumber de nieuwe rating die opgeslagen moet worden
	 * @param movie de film wiend rating geupdate moet worden.
	 */
	public void changeRating(Rating rating, double ratingNumber, Movie movie)
	{
		rating.setRating(ratingNumber);
		movie.setAvgRating(getAvgRatingMovie(movie));
	}
	
	/**
	 * Verwijdert de opgegeven rating uit het model. Past daarna de gemiddelde rating van de opgegeven film aan.
	 * @param rating de rating die verwijdert moet worden.
	 * @param movie de films wiens rating geupdate moet worden.
	 */
	public void removeRating(Rating rating, Movie movie) {
		ratings.remove(rating);
		movie.setAvgRating(getAvgRatingMovie(movie));
	}
	
	/**
	 * Controleert of er een rating is met de opgegeven movie en user. Indien aanwezig, wordt deze teruggegeven.
	 * @param movie de movie wiens rating opgehaald wordt.
	 * @param user de user wiens rating opgehaald wordt.
	 * @return als rating aanwezig is wordt deze teruggegeven, anders null
	 */
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
	
	/**
	 * Controleert of er een rating is met de opgegeven movie en user. Indien aanwezig, wordt deze verwijdert.
	 * @param movie de movie wiens rating verwijdert wordt
	 * @param user de user wiens rating verwijdert wordt
	 */
	public void deleteRating(Movie movie, User user)
	{
		for(Rating r: ratings) {
			if(r.getMovie() == movie && r.getUser() == user) {
				ratings.remove(r);
			}
		}
		
	}
	
	/**
	 * Creëert een access token en bekijkt of deze al bestaat, zo niet dan wordt deze terug gegeven. 
	 * Als de rating al wel bestaat roept de methode zichzelf weer aan.
	 * @return de gecreëerde rating
	 */
	public String createToken() {
		int i = 0;
		i = (int) (Math.random()*1000000000);
		String token = Integer.toString(i);
		if (clients.containsValue(token)) {
			return createToken();
		}
		return token;
	}
	
	/**
	 * Geeft lijst met alle films
	 * @return lijst met alle films
	 */
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
	
	/**
	 * Haalt alle ratings op van de opgegeven movie en maakt hier een gemiddelde van. Dit gemiddelde wordt teruggegeven
	 * @param movie de movie wiens rating opgehaald wordt.
	 * @return de gemiddelde rating
	 */
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
	
	/**
	 * Geeft een lijst met alle ratings voor de opgegeven movie.
	 * @param movie de movie wiens ratings opgehaald worden.
	 * @return lijst met alle ratings van opgegeven movie.
	 */
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
	
	/**
	 * Geeft een lijst met alle gebruikers
	 * @return lijst met alle gebruikers
	 */
	public List<User> getAllUsers()
	{
		return users;
	}
	
	/**
	 * Controleert of er een user is met de opgegeven id. Zo ja, dan wordt deze user teruggegeven.
	 * @param id de id die gecontroleert moet worden
	 * @return de user die bij de id hoort, anders null.
	 */
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
	
	/**
	 * Controleert of het opgegeven user object volledig is ingevuld en of de nickname van deze user niet al bestaat. 
	 * Zo niet, dan wordt de opgegeven user toegevoegd aan het model.
	 * @param user de user die gecontroleert en toegevoegd moet worden.
	 * @throws IOException als user niet compleet is "user not complete", als user al bestaat "user already exists".
	 */
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

