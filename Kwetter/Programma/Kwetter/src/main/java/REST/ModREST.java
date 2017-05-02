package REST;

import Domain.Kweet;
import Domain.User;
import JSONObjects.RoleJSON;
import Service.ModService;
import Service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by Joris on 9-3-2017.
 */
@Stateless
@Path("mod")
public class ModREST {
    @Inject
    ModService modService;

    @Inject
    UserService userService;

    @Context
    UriInfo uriInfo;

    @PUT
    @Consumes("application/json")
    @Path("changerole")
    public Response create(final RoleJSON input) {
        modService.changeRole(input.userId, input.role);
        User updatedUser = userService.getUser(input.userId);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(updatedUser.getId())).build();
        return Response.ok(updatedUser).location(uri).build();
    }

    @GET
    @Produces("application/json")
    @Path("users")
    public List<User> getAllUsers(){
        return modService.getAllUsers();
    }

    @GET
    @Produces("application/json")
    @Path("kweets")
    public List<Kweet> getAllKweets(){
        return modService.getAllKweets();
    }

    @DELETE
    @Path("deletekweet")
    public Response deleteKweet(@QueryParam("kweetId") int kweetId){
        modService.deleteKweet(kweetId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
