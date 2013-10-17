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
		Model model = (Model) context.getAttribute("model");
		List<User> userList = model.getAllUsers();
		User temp = null;
		for(User u : userList) {
			if(user.getPassword().equals(u.getPassword()) && user.getNickname().equals(u.getNickname())) {
				temp = u;
			}
		}
		if(temp == null) {
			System.out.println("NIET INGELOGD");
		} else if (temp != null) {
			System.out.println(model.addClient(temp));
		}
	}
}
