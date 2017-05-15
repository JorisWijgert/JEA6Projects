package nl.kabisa.battle.jooby;

import Domain.User;
import Domain.UserJson;
import Service.UserService;
import com.google.gson.Gson;
import org.jooby.Jooby;

public class Application extends Jooby {

    Gson gson = new Gson();
    UserService userService = new UserService();

    {
        get("/", () -> "Hello world!");
        post("/user", request -> {
            UserJson userJson =  gson.fromJson(request.body().value(), UserJson.class);
            User createdUser = userService.createUser(userJson.getUsername(), userJson.getPassword());
            return gson.toJson(createdUser);
        });
    }

    public static void main(String[] args) {
        run(Application::new, args);
    }
}