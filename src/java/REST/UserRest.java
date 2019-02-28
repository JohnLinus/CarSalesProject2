package REST;

import Controller.UserController;
import Entities.Bidder;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/user")
public class UserRest {

    @Inject
    UserController uc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(Bidder user) {
        uc.add(user);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bidder getUser(@PathParam("id") Long id) {
        return uc.get(id);
    }

    @GET
    @Path("/all/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bidder> getUsers(@PathParam("name") String name) {
        return uc.get(name);
    }

}
