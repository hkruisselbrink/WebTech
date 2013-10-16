package resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import model.Model;
import model.User;

@Path("/login")
@Consumes({"application/json", "application/xml"})
@Produces({"application/json", "application/xml"})
public class Login {
	
	@Context ServletContext context;

	@POST
	public void loginUser(User user) {
		System.out.println(user.toString());
		Model model = (Model) context.getAttribute("model");
		List<User> userList = model.getAllUsers();
		System.out.println(userList.toString());
		for(User u : userList) {
			System.out.println(u.toString());
			if(user.getPassword().equals(u.getPassword()) && user.getNickname().equals(u.getNickname())) {
				//TODO INGELOGD
			}
		}
	}
}
