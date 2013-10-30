package resources;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.User;

@Path("/login")
@Consumes({"application/json", "application/xml"})
public class Login {
	
	@Context ServletContext context;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String loginUser(User user) {
		Model model = (Model) context.getAttribute("model");
		System.out.println("Pjotr is cool");
		List<User> userList = model.getAllUsers();
		User temp = null;
		if(user.getNickname() == null || user.getPassword() == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("No password or username").build());

		}
		for(User u : userList) {
			if(user.getPassword().equals(u.getPassword()) && user.getNickname().equals(u.getNickname())) {
				temp = u;
			}
		}
		if(temp == null) {
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid username or password").build());
		}

		String v =  model.addClient(temp);
		return "{\"access_token\":\"" + model.addClient(temp) + "\"}";
		
	}
}
