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
	
	@GET
	public ArrayList<Movie> getMovies(@HeaderParam("access_token") String accessToken) {
		Model model = (Model) context.getAttribute("model");
		ArrayList<Movie> temp = new ArrayList<Movie>();
		System.out.println(accessToken);
		for(int i = 0; i < model.getAllMovies().size(); i++) {
			model.getAllMovies().get(i).setAvgRating(model.getAvgRatingMovie(model.getAllMovies().get(i)));
			if(accessToken != null)
			{
				if(!accessToken.equals("null"))
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
						throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid access token").build());
					}
				}
			}
			temp.add(model.getAllMovies().get(i));
			
		}
		return (ArrayList<Movie>) model.getAllMovies();
	}
	
	@GET
	@Path("ratings")
	public ArrayList<Rating> getRatings(@HeaderParam("access_token") String accessToken)
	{
		Model model = (Model) context.getAttribute("model");
		ArrayList<Rating> temp = new ArrayList<Rating>();
		
		User user = model.checkAccessToken(accessToken);
		if(user == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid access token").build());

		}
		
		for(Rating r : model.getRatings()) {
			if(r.getUser() == user) {
				temp.add(r);
			}
		}
		return temp;
 	}
	
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
		System.out.println(accessToken);
		for(int i = 0; i < model.getAllMovies().size(); i++) {
			model.getAllMovies().get(i).setAvgRating(model.getAvgRatingMovie(model.getAllMovies().get(i)));
			if(!accessToken.equals("null"))
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
					throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid access token").build());
				}
			}
			temp.add(model.getAllMovies().get(i));
		}
		return model.newestMovies();

	}
	
	
}
