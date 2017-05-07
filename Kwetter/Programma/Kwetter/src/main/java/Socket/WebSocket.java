package Socket;


import Domain.Kweet;
import Domain.User;
import Service.KweetService;
import Service.UserService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

/**
 * Created by Joris on 7-5-2017.
 */

@ServerEndpoint(value = "/websocket/kweet", configurator = HttpSessionConfigurator.class)
public class WebSocket {

    private static Map<String, Session> sockets = new HashMap<>();
    private static Map<String, Session> temp = new HashMap<>();

    private static JsonParser parser = new JsonParser();
    private static GsonBuilder builder = new GsonBuilder();

    @Inject
    UserService userService;
    @Inject
    KweetService kweetService;

    @OnClose
    public void onCloseConnection(Session session) {
        sockets.remove(session.getUserPrincipal().getName());
    }

    public static void sendKweet(Kweet kweet, List<User> followers) {

        for (User u : followers) {
            sendWebSocket(kweet, u.getName());
        }

        sendWebSocket(kweet, kweet.getUser().getName());
    }

    private static void sendWebSocket(Kweet kweet, String username) {
        Session s = sockets.get(username);
        builder.registerTypeAdapter(Calendar.class, new CalendarSerializer());
        builder.registerTypeAdapter(GregorianCalendar.class, new CalendarSerializer());
        if (s != null) {
            s.getAsyncRemote().sendObject(builder.create().toJson(kweet));
        }
    }

    @OnMessage
    public void OnMessage(Session session, String message) {
        if (message == null || message == "" || session == null)
            return;

        sockets.put(message, session);
    }

    public static void sendMessage(String message, String user) {
        Session s = sockets.get(user);
        if (s != null) {
            s.getAsyncRemote().sendText(message);
        }
    }

    public static void sendMessage(String message, List<String> users) {
        for (String user : users) {
            sendMessage(message, user);
        }
    }
}
