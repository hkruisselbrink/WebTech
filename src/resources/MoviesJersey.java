package resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.Movie;
import model.Rating;
import model.User;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MoviesJersey {

	@Context ServletContext context;
	
	/**
	 * Methode voor het ophalen van een lijst met films. Tijdens het maken van deze lijst wordt per movie de gemiddelde rating ingesteld.
	 * Produceert een JSON array aan de hand van de return result.
	 * @return lijst me alle films
	 */
	@GET
	public ArrayList<Movie> getMovies() {
		Model model = (Model) context.getAttribute("model");
		ArrayList<Movie> temp = (ArrayList<Movie>) model.getAllMovies();
		for(Movie m : temp)
		{
			m.setAvgRating(model.getAvgRatingMovie(m));
		}
		return temp;
	}
	
	/**
	 * Methode voor het ophalen een lijst met alle ratings van de ingelogde gebruiker. 
	 * Eerst wordt gecontroleerd of de meegegeven access token bestaat.
	 * Als de access token correct is, wordt er een lijst gemaakt met alle ratings van enkel de ingelogde gebruiker.
	 * Produceert een JSON array aan de hand van de return result.
	 * @param accessToken de access token van de gebruiker
	 * @return een lijst met alle ratings.
	 */
	@GET
	@Path("ratings")
	public ArrayList<Rating> getRatings(@HeaderParam("access_token") String accessToken)
	{
		Model model = (Model) context.getAttribute("model");
		ArrayList<Rating> temp = new ArrayList<Rating>();
		
		User user = model.checkAccessToken(accessToken);
		if(user == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("User not found").build());

		}
		
		for(Rating r : model.getRatings()) {
			if(r.getUser() == user) {
				temp.add(r);
			}
		}
		return temp;
 	}
	
	/**
	 * Methode voor het ophalen van een lijst met alle films die een rating hebben.
	 * Produceert een JSON array aan de hand van de return result.
	 * @return lijst met alle films die een rating hebben
	 */
	@GET
	@Path("avgRatings")
	public ArrayList<Movie> getMoviesWithRating()
	{
		Model model = (Model) context.getAttribute("model");
		ArrayList<Movie> temp = new ArrayList<Movie>();
		for(Movie m : model.getAllMovies())
		{
			if(model.getAllRatingsMovie(m).size() != 0)
			{
				temp.add(m);
			}
		}
		return temp;		
	}

	@GET
	@Path("newest")
	public List<Movie> getNewestMovies(@HeaderParam("access_token") String accessToken)
	{
		Model model = (Model) context.getAttribute("model");
		ArrayList<Movie> temp = new ArrayList<Movie>();
		for(int i = 0; i < model.getAllMovies().size(); i++) {
			model.getAllMovies().get(i).setAvgRating(model.getAvgRatingMovie(model.getAllMovies().get(i)));
			if(!(accessToken == null))
			{
				User user = model.checkAccessToken(accessToken);
				if(user != null)
				{
					if(model.ratedByUser(model.getAllMovies().get(i), user))
					{
						model.getAllMovies().get(i).setRatedByMe(true);
					}
					else
					{
						model.getAllMovies().get(i).setRatedByMe(false);
					}
				}
				else
				{
					model.getAllMovies().get(i).setRatedByMe(false);
				}
			}
			temp.add(model.getAllMovies().get(i));
		}
		return model.newestMovies();

	}
	
	
}
