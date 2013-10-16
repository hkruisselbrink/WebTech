package resources;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Model;
import model.Movie;

@Path("/movie")
public class MovieJersey {
	
	@Context ServletContext context;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMovie(@PathParam("id") int id) {
		Model model = (Model) context.getAttribute("model");
		return model.getMovie(id);
	}
}
