package resources;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Model;
import model.User;

@Path("/user")
public class UserJersey {
	
	@Context ServletContext context;
	
	@Path("{id}")
	@GET
	@Produces({"application/json", "application/xml"})
	public User getUser(@PathParam("id") String id)
	{
		Model model = (Model) context.getAttribute("model");
		return model.getUser(id);
	}
	
	

}
