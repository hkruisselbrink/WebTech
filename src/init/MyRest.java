package init;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;



@ApplicationPath("resources")
public class MyRest extends ResourceConfig {
	
 public MyRest() {
  packages("resources");
 }
 
}