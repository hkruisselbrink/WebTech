package resources;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
<<<<<<< HEAD
	@Produces(MediaType.APPLICATION_JSON)
=======
	@Produces({"application/json", "application/xml"})
>>>>>>> ce8694ca3862ef1ae80dab523fbdf7692b3c3ac3
	public User getUser(@PathParam("id") String id)
	{
		Model model = (Model) context.getAttribute("model");
		
		return model.getUser(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUser(User user)
	{
		System.out.println(user.getFirstName());
		
		
		
	}
	
	

}
