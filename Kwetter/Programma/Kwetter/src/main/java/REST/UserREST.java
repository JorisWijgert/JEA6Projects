package rest;

import domain.User;
import jsonobjects.RelationJSON;
import jsonobjects.UserJSON;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Joris on 9-3-2017.
 */
@Stateless
@Path("user")
public class UserREST {

    @Inject
    UserService userService;


    @POST
    @Consumes("application/json")
    @Path("create")
    public void create(final UserJSON input) throws NoSuchAlgorithmException {
        userService.createUser(new User(input.id, input.photo, input.username, input.bio, input.web, input.location, input.password));
    }

    @PUT
    @Consumes("application/json")
    @Path("update")
    public void update(final UserJSON input) throws NoSuchAlgorithmException {
        userService.updateUser(new User(input.id, input.photo, input.username, input.bio, input.web, input.location, input.password));
    }

    @GET
    @Produces("application/json")
    @Path("user")
    public User getUserByName(@QueryParam("name") String username, @QueryParam("id") int id) {
        if (username == null)
            return userService.getUser(id);
        return userService.getUser(username);
    }

    @POST
    @Consumes("application/json")
    @Path("follow")
    public void follow(final RelationJSON input) throws NullPointerException, IllegalArgumentException {
        userService.follow(input.followerId, input.followingId);
    }

    @GET
    @Produces("application/json")
    @Path("getfollowers")
    public List<User> getFollowers(@QueryParam("user") int userId) throws NullPointerException, IllegalArgumentException {
        return userService.getFollowers(userId);
    }

    @GET
    @Produces("application/json")
    @Path("getfollowing")
    public List<User> getFollowing(@QueryParam("user") int userId) throws NullPointerException, IllegalArgumentException {
        return userService.getFollowing(userId);
    }
}
