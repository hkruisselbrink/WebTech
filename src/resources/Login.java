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
@Produces(MediaType.APPLICATION_JSON)
public class Login {
	
	@Context ServletContext context;

	/**
	 * Methode voor het inloggen van een gebruiker. Eerst wordt gekeken of het user object voldoende ingevuld is. 
	 * Daarna of de user in het systeem staat. Zo ja, dan wordt de client in het systeem gezet en wordt de access token teruggegeven.
	 * De opgegeven user moet in een JSONarray beschreven worden.
	 * @param user de user die ingelogd wordt
	 * @return de access token
	 */
	@POST
	public String loginUser(User user) {
		Model model = (Model) context.getAttribute("model");
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
