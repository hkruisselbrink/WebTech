package resources;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.Movie;
import model.Rating;
import model.User;

@Path("/movie")
@Produces({"application/json", "application/xml"})
public class MovieJersey {
	
	@Context ServletContext context;
	
	/**
	 * Methode voor het ophalen van een movie. Er wordt gecontroleerd of er een movie is met de opgegeven id. 
	 * Dan wordt bekeken of de ingelogde gebruiker deze movie al gerate heeft. Als dit zo is, dan wordt de boolean ratedByMe in het movie object op true gezet.
	 * Daarna wordt de film teruggegeven.
	 * Produceert een JSON array aan de hand van de return result.
	 * @param id de id van de movie die opgehaald wordt
	 * @param accessToken de access token van gebruiker
	 * @return de movie die bij het opgegeven id hoort
	 */
	@GET
	@Path("{id}")
	public Movie getMovie(@PathParam("id") String id, @HeaderParam("access_token") String accessToken) {
		Model model = (Model) context.getAttribute("model");
		Movie temp = model.getMovie(id);
		if(temp == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Movie not found").build());
		}
		else
		{
			temp.setAvgRating(model.getAvgRatingMovie(temp));
			if(!accessToken.equals(""))
			{
				User user = model.checkAccessToken(accessToken);
				if(user != null)
				{
					if(model.ratedByUser(temp, user))
					{
						temp.setRatedByMe(true);
					}
					else
					{
						temp.setRatedByMe(false);
					}
				}
				else
				{
					temp.setRatedByMe(false);
				}
			}
			System.out.println(temp.getRatedByMe());
			return temp;
		}
	}
	
	/**
	 * Methode voor het aanpassen van een reeds opgeslagen rating. 
	 * Er wordt gecontroleerd of de rating juist is meegegeven. Dan wordt bekeken of er een movie is met de opgegeven id.
	 * Daarna wordt gecontroleerd of de opgegeven access token bestaat en de gebruiker dus ingelogd is. 
	 * In het model wordt dan bekeken of de gebruiker deze film een rating heeft gegeven. Zo ja, dan wordt deze aangepast.
	 * @param id de id van de movie wiens rating aangepast wordt
	 * @param rating de nieuwe rating die ingesteld wordt
	 * @param accessToken de access token van de gebruiker
	 */
	@PUT
	@Path("{id}/rate")
	public void updateMovie(@PathParam("id") String id, @HeaderParam("rating") double rating, @HeaderParam("access_token") String accessToken)
	{
		if(rating % 0.5 != 0) {
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid Rating").build());
		}
		Model model = (Model) context.getAttribute("model");
		Movie movie = model.getMovie(id);
		
		if(movie == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Movie not found").build());
		}
		
		User user = model.checkAccessToken(accessToken);
		if(user == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("User not found").build());

		}
		
		Rating r = model.getRating(movie, user);
		
		if(r == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Movie not yet rated").build());
		}
		else
		{
			model.changeRating(r, rating, movie);
		}
	}
	
	/**
	 * Methode voor het plaatsen van een rating.
	 * Er wordt gecontroleerd of de rating juist is meegegeven. Dan wordt bekeken of er een movie is met de opgegeven id.
	 * Daarna wordt gecontroleerd of de opgegeven access token bestaat en de gebruiker dus ingelogd is. 
	 * In het model wordt dan bekeken of de gebruiker deze film een rating heeft gegeven. Zo nee, dan wordt de rating geplaatst.
	 * @param id de id van de movie die een rating krijgt
	 * @param rating de rating die ingesteld wordt
	 * @param accessToken de access token van de gebruiker
	 */
	@POST
	@Path("{id}/rate")
	public void rateMovie(@PathParam("id") String id, @HeaderParam("rating") double rating, @HeaderParam("access_token") String accessToken)
	{
		if(rating % 0.5 != 0) {
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid Rating").build());
		}
		Model model = (Model) context.getAttribute("model");
		Movie movie = model.getMovie(id);
		
		if(movie == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Movie not found").build());
		}
		
		User user = model.checkAccessToken(accessToken);
		if(user == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("User not found").build());
		}
		
		Rating r = model.getRating(movie, user);
		
		if(r == null)
		{
			model.addRating(movie, user, rating);
		}
		else
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Movie allready rated").build());
		}		
		
	}
	
	/**
	 * Methode voor het verwijderen van een rating.
	 * Er wordt bekeken of er een movie is met de opgegeven id.
	 * Daarna wordt gecontroleerd of de opgegeven access token bestaat en de gebruiker dus ingelogd is. 
	 * In het model wordt dan bekeken of de gebruiker deze film een rating heeft gegeven. Zo ja, dan wordt deze verwijdert.
	 * @param id de id van de movie wiens rating verwijdert wordt
	 * @param accessToken de access token van de gebruiker
	 */	
	@DELETE
	@Path("{id}/rate")
	public void deleteRating(@PathParam("id") String id, @HeaderParam("access_token") String accessToken) 
	{
		System.out.println("kameel");
		Model model = (Model) context.getAttribute("model");
		Movie movie = model.getMovie(id);
		
		if(movie == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Movie not found").build());
		}
		
		User user = model.checkAccessToken(accessToken);
		if(user == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("User not found").build());

		}
		
		Rating r = model.getRating(movie, user);
		if(r == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Rating not found").build());
		}
		
		model.removeRating(r, movie);
	}
}
