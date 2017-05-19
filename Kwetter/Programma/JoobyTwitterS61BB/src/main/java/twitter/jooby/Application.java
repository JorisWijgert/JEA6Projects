package twitter.jooby;

import Domain.MessageJson;
import Domain.SubscribeJson;
import Domain.User;
import Domain.UserJson;
import Service.UserService;
import com.google.gson.Gson;
import org.jooby.Jooby;

public class Application extends Jooby {

    Gson gson = new Gson();
    UserService userService = new UserService();

    {
        get("/messages", () -> {
            return gson.toJson(userService.getAllMessages());
        });

        get("/messages/:username", req -> {
            return gson.toJson(userService.getMessages(req.param("username").value()));
        });

        get("/messages/subscribed/:username", req -> {
            return gson.toJson(userService.getMessagesFromSubscriptions(req.param("username").value()));
        });

        get("/users", () -> {
           return gson.toJson(userService.getUsers());
        });
    }

    {
        post("/users", request -> {
            UserJson userJson = gson.fromJson(request.body().value(), UserJson.class);
            User createdUser = userService.createUser(userJson.getUsername(), userJson.getPassword());
            return gson.toJson(createdUser);
        });

        post("/users/login", request -> {
            UserJson userJson = gson.fromJson(request.body().value(), UserJson.class);
            User loggedInUser = userService.login(userJson.getUsername(), userJson.getPassword());
            return gson.toJson(loggedInUser);
        });

        post("/messages", request -> {
            MessageJson messageJson = gson.fromJson(request.body().value(), MessageJson.class);
            userService.addMessage(messageJson.getMessage(), messageJson.getUsername());
            return "Done";
        });

        post("/subscribe", request -> {
            SubscribeJson subscribeJson = gson.fromJson(request.body().value(), SubscribeJson.class);
            userService.subscribe(subscribeJson.getUsername(), subscribeJson.getSubscription());
            return "Done";
        });
    }

    public static void main(String[] args) {
        run(Application::new, args);
    }
}