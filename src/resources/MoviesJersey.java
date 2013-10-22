package resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Model;
import model.Movie;

@Path("/movies")
public class MoviesJersey {

	@Context ServletContext context;
	
	@GET
	@Produces({"application/json", "application/xml"})
	public ArrayList<Movie> getMovies() {
		Model model = (Model) context.getAttribute("model");
		ArrayList<Movie> temp = (ArrayList<Movie>) model.getAllMovies();
		for(Movie m : temp)
		{
			m.setAvgRating(model.getAvgRatingMovie(m));
		}
		return temp;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
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
	
	
}
