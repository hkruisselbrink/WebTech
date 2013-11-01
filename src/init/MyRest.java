package init;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;



@ApplicationPath("resources")
public class MyRest extends ResourceConfig {

/**
 * Registeert de resources
 */
public MyRest() {
  packages("resources");
}
 
}