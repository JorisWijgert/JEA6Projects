package Service;

import Domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joris on 15-5-2017.
 */
public class UserService {
    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    private User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public void addMessage(String message, String username) {
        User user = getUser(username);
        if (user == null)
            throw new NullPointerException("Can't find user: " + username);

        if (message == null || message.length() > 99 || message.length() < 1)
            throw new IllegalArgumentException("Message is wrong.");

        user.addMessage(message);
    }

    public List<String> getAllMessages() {
        List<String> messages = new ArrayList<>();

        for (User user : users)
            messages.addAll(user.getMessages());

        return messages;
    }

    public List<String> getMessages(String username){
        User user = getUser(username);
        if (user != null)
            throw new NullPointerException("Can't find user: " + username);

        return user.getMessages();
    }

    public List<String> getMessagesFromSubscriptions(String username) {
        User user = getUser(username);
        if (user != null)
            throw new NullPointerException("Can't find user: " + username);

        List<String> messages = new ArrayList<>();

        for (User innerUser : user.getSubscriptions())
            messages.addAll(innerUser.getMessages());

        return messages;
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public User createUser(String username, String password) {
        User user = new User(username, password);
        users.add(user);
        return user;
    }
}
