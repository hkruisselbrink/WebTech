package resources;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.User;

@Path("/users")
public class UsersJersey {
	
	@Context ServletContext context;
	
	/**
	 * Methode voor het ophalen van een lijst met alle users. 
	 * Eerst wordt bekeken of de opgegeven accessToken bestaat.
	 * Produceert een JSON array aan de hand van de return result.
	 * @param accessToken de access token van de gebruiker
	 * @return lijst met alle geregisteerde gebruikers.
	 */
	@GET
	@Produces({"application/json", "application/xml"})
	public ArrayList<User> getUsers(@HeaderParam("access_token") String accessToken)
	{
		Model model = (Model) context.getAttribute("model");
		User me = model.checkAccessToken(accessToken);
		if(me == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid access token").build());

		}
		return (ArrayList<User>) model.getAllUsers();
	}	

}
