package resources;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.User;

@Path("/user")
public class UserJersey {
	
	@Context ServletContext context;
	
	/**
	 * Methode voor het ophalen van een user aan de hand van de meegegeven id. 
	 * Eerst wordt gecontroleerd of de access token bestaat, dan wordt bekeken of er een user bestaat met de opgegeven id.
	 * Zo ja, dan wordt deze user teruggegeven.
	 * Produceert een JSON array aan de hand van de return result.
	 * @param id id van de te zoeken user
	 * @param accessToken access token van de gebruiker
	 * @return user die bij opgegeven id hoort
	 */
	@Path("{id}")
	@GET
	@Produces({"application/json", "application/xml"})
	public User getUser(@PathParam("id") String id, @HeaderParam("access_token") String accessToken)
	{
		Model model = (Model) context.getAttribute("model");
		User temp = model.getUser(id);
		User me = model.checkAccessToken(accessToken);
		if(me == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid access token").build());

		}
		if(temp == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid access token").build());

		}
		else
		{
			return temp;
		}
	}
	
	/**
	 * Methode voor het ophalen van de user die is ingelogd. Er wordt eerst bekeken of de access token bestaat. Zo ja, dan wordt de huidige gebruiker teruggegeven.
	 * Produceert een JSON array aan de hand van de return result.
	 * @param accessToken de access token van de gebruiker
	 * @return user object van huidige gebruiker
	 */
	@GET
	@Path("me")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserWithAcceptToken(@HeaderParam("access_token") String accessToken)
	{
		Model model = (Model) context.getAttribute("model");
		User me = model.checkAccessToken(accessToken);
		if(me == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("Invalid access token").build());

		}
		return me;
	}
	
	/**
	 * Methode voor het toevoegen van een user aan het model. De opgegeven user moet in een JSONarray beschreven worden.
	 * @param user het toe te voegen user object
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUser(User user)
	{
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getNickname());
		System.out.println(user.getPassword());
		Model model = (Model) context.getAttribute("model");
		try {
			model.addUser(user);
		} catch (IOException e) {
			throw new WebApplicationException(Response.status(Response.Status.CONFLICT).type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build());
		}
		System.out.println("USER GEMAAKT");
		//throw new WebApplicationException(Response.status(Response.Status.CREATED).type(MediaType.TEXT_PLAIN).entity("User created!").build());
		
	}
	
	

}
