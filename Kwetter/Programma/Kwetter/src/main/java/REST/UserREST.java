package REST;

import Domain.User;
import JSONObjects.RelationJSON;
import JSONObjects.UserJSON;
import Service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Joris on 9-3-2017.
 */
@Stateless
@Path("users")
public class UserREST {

    @Inject
    UserService userService;

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("login")
    public Response login(final UserJSON input) throws NoSuchAlgorithmException {
        User responseUser = userService.login(input.name, input.password);
        if (responseUser == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(responseUser).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(final UserJSON input) throws NoSuchAlgorithmException {
        try {
            userService.createUser(new User(input.id, input.photo, input.name, input.bio, input.web, input.location, input.password));
            User createdUser = userService.getUser(input.name);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdUser.getId())).build();
            return Response.created(uri).entity(createdUser).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(final UserJSON input) throws NoSuchAlgorithmException {
        try {
            userService.updateUser(new User(input.id, input.photo, input.name, input.bio, input.web, input.location, input.password));
            User updatedUser = userService.getUser(input.name);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(updatedUser.getId())).build();
            return Response.ok(updatedUser).location(uri).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("{user}")
    public User getUserById(@PathParam("user") int userId) {
        return userService.getUser(userId);
    }

    @GET
    @Produces("application/json")
    @Path("username/{username}")
    public User getUserByName(@PathParam("username") String username) {
        return userService.getUser(username);
    }

    @POST
    @Consumes("application/json")
    @Path("follow")
    public Response follow(final RelationJSON input) throws Exception {
        userService.follow(input.followerId, input.followingId);
        return Response.ok(userService.getUser(input.followingId)).build();
    }

    @GET
    @Produces("application/json")
    @Path("followers/{userId}")
    public List<User> getFollowers(@PathParam("userId") int userId) throws Exception {
        return userService.getFollowers(userId);
    }

    @GET
    @Produces("application/json")
    @Path("following/{userId}")
    public List<User> getFollowing(@PathParam("userId") int userId) throws Exception {
        return userService.getFollowing(userId);
    }
}
