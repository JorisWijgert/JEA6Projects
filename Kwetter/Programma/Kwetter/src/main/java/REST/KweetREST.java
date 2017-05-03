package REST;

import Domain.Kweet;
import Domain.User;
import JSONObjects.KweetJSON;
import JSONObjects.LikeJSON;
import Service.KweetService;
import Service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
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
@Path("kweets")
public class KweetREST {

    @Inject
    KweetService kweetService;
    @Inject
    UserService userService;

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes("application/json")
    @Interceptors(Interceptor.class)
    public Response create(final KweetJSON input) {
        try {
            User kweetUser = userService.getUser(input.userId);
            if (kweetUser == null)
                throw new NullPointerException("User not found.");
            Kweet kweet = new Kweet(input.id, input.message, kweetUser);
            kweetService.createKweet(kweet);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(kweet.getId())).build();
            return Response.created(uri).entity(kweet).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("like")
    public Response likeKweet(final LikeJSON input) throws Exception {
        kweetService.likeKweet(input.kweetId, input.userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces("application/json")
    @Path("timeline/{userId}")
    public List<Kweet> getTimeline(@PathParam("userId") int userId) {
        return kweetService.getTimeline(userId);
    }

    @GET
    @Produces("application/json")
    @Path("latest/{userId}/{amount}")
    public List<Kweet> getLatest(@PathParam("userId") int userId, @PathParam("amount") int amount){
        return kweetService.latestKweets(userId, amount);
    }

    @GET
    @Produces("application/json")
    @Path("search/{keyword}")
    public List<Kweet> search(@PathParam("keyword") String keyword){
        return kweetService.searchKweet(keyword);
    }
}
