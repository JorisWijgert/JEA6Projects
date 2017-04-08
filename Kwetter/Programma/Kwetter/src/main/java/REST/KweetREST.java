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
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Joris on 9-3-2017.
 */
@Stateless
@Path("kweet")
public class KweetREST {

    @Inject
    KweetService kweetService;
    @Inject
    UserService userService;

    @POST
    @Consumes("application/json")
    @Path("create")
    @Interceptors(Interceptor.class)
    public Response create(final KweetJSON input) {
        try {
            User kweetUser = userService.getUser(input.userId);
            if (kweetUser == null)
                throw new NullPointerException("User not found.");
            Kweet kweet = new Kweet(input.id, input.message, kweetUser);
            kweetService.createKweet(kweet);
            return Response.ok(kweet).build();
        } catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("like")
    public void likeKweet(final LikeJSON input) throws Exception {
        kweetService.likeKweet(input.kweetId, input.userId);
    }

    @GET
    @Produces("application/json")
    @Path("timeline")
    public List<Kweet> getTimeline(@QueryParam("user") int userId) {
        return kweetService.getTimeline(userId);
    }

    @GET
    @Produces("application/json")
    @Path("latest")
    public List<Kweet> getLatest(@QueryParam("user") int userId, @QueryParam("amount") int amount){
        return kweetService.latestKweets(userId, amount);
    }

    @GET
    @Produces("application/json")
    @Path("search")
    public List<Kweet> search(@QueryParam("keyword") String keyword){
        return kweetService.searchKweet(keyword);
    }
}
