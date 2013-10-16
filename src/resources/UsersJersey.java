package resources;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Model;
import model.User;

@Path("/users")
public class UsersJersey {
	
	@Context ServletContext context;
	
	@GET
<<<<<<< HEAD
	@Produces(MediaType.APPLICATION_JSON)
=======
	@Produces({"application/json", "application/xml"})
>>>>>>> ce8694ca3862ef1ae80dab523fbdf7692b3c3ac3
	public ArrayList<User> getUsers()
	{
		Model model = (Model) context.getAttribute("model");
		return (ArrayList<User>) model.getAllUsers();
	}	

}
