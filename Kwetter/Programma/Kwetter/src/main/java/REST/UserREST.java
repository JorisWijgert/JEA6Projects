package REST;

import Domain.User;
import JSONObjects.RelationJSON;
import JSONObjects.UserJSON;
import Service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
    @Path("login")
    public Response login(final UserJSON input) throws NoSuchAlgorithmException {
        User responseUser = userService.login(input.name, input.password);
        if (responseUser == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(responseUser).build();
    }

    @POST
    @Consumes("application/json")
    @Path("create")
    public Response create(final UserJSON input) throws NoSuchAlgorithmException {
        try {
            userService.createUser(new User(input.id, input.photo, input.name, input.bio, input.web, input.location, input.password));
            return Response.ok(userService.getUser(input.name)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Path("update")
    public Response update(final UserJSON input) throws NoSuchAlgorithmException {
        try {
            userService.updateUser(new User(input.id, input.photo, input.name, input.bio, input.web, input.location, input.password));
            return Response.ok(userService.getUser(input.name)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
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
    public void follow(final RelationJSON input) throws Exception {
        userService.follow(input.followerId, input.followingId);
    }

    @GET
    @Produces("application/json")
    @Path("getfollowers")
    public List<User> getFollowers(@QueryParam("user") int userId) throws Exception {
        return userService.getFollowers(userId);
    }

    @GET
    @Produces("application/json")
    @Path("getfollowing")
    public List<User> getFollowing(@QueryParam("user") int userId) throws Exception {
        return userService.getFollowing(userId);
    }
}
