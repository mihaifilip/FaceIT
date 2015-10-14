package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by mihfilip on 14/10/2015.
 */

@Path("/user")
public class Resource {

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Object someFunction() {
        System.out.println("lalalal");

        return true;
    }

}
