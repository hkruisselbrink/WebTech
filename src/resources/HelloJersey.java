package resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/pjoow")
public class HelloJersey {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHoi()
	{
		return "<p>Hello cool guy</p>";
		
	}

}
