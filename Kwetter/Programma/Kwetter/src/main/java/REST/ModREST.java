package rest;

import domain.Kweet;
import domain.User;
import jsonobjects.RoleJSON;
import service.ModService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Joris on 9-3-2017.
 */
@Stateless
@Path("mod")
public class ModREST {
    @Inject
    ModService modService;

    @POST
    @Consumes("application/json")
    @Path("changerole")
    public void create(final RoleJSON input) {
        modService.changeRole(input.userId, input.role);
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
    public void deleteKweet(@QueryParam("kweetId") int kweetId){
        modService.deleteKweet(kweetId);
    }
}
