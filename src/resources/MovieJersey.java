package resources;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
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
	
	@GET
	@Path("{id}")
	public Movie getMovie(@PathParam("id") String id) {
		Model model = (Model) context.getAttribute("model");
		Movie temp = model.getMovie(id);
		if(temp == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Movie not found").build());
		}
		else
		{
			temp.setAvgRating(model.getAvgRatingMovie(temp));
			return temp;
		}
	}
	
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
			System.out.println("fredje");
			model.addRating(movie, user, rating);
		}
		else
		{
			model.changeRating(r, rating, movie);
		}		
		
	}
	
	@DELETE
	@Path("{id}/rate")
	public void deleteRating(@PathParam("id") String id, @HeaderParam("access_token") String accessToken) 
	{
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
