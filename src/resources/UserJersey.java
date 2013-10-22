package resources;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	
	@Path("{id}")
	@GET
	@Produces({"application/json", "application/xml"})

	public User getUser(@PathParam("id") String id)
	{
		Model model = (Model) context.getAttribute("model");
		User temp = model.getUser(id);
		if(temp == null)
		{
			throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity("User not found").build());

		}
		else
		{
			return temp;
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUser(User user)
	{
		Model model = (Model) context.getAttribute("model");
		try {
			model.addUser(user);
		} catch (IOException e) {
			throw new WebApplicationException(Response.status(Response.Status.CONFLICT).type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build());
		}
		
		throw new WebApplicationException(Response.status(Response.Status.CREATED).type(MediaType.TEXT_PLAIN).entity("User created!").build());
		
	}
	
	

}
