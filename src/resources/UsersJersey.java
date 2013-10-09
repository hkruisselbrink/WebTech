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

@Path("/users")
public class UsersJersey {
	
	@Context ServletContext context;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getUsers()
	{
		Model model = (Model) context.getAttribute("model");
		String result = "";
		for(User u : model.getAllUsers())
		{
			result += u.toString() + "<br/>";
		}
		
		return result;
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getUser(@PathParam("id") String id)
	{
		Model model = (Model) context.getAttribute("model");
		return model.getUser(id).toString();
	}

}
