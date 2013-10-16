package resources;

import java.util.ArrayList;

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
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Movie> getMovies() {
		Model model = (Model) context.getAttribute("model");
		return (ArrayList<Movie>) model.getAllMovies();
	}
	
}
