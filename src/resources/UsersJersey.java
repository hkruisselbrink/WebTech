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
	@Produces({"application/json", "application/xml"})
	public ArrayList<User> getUsers()
	{
		Model model = (Model) context.getAttribute("model");
		return (ArrayList<User>) model.getAllUsers();
	}	

}
