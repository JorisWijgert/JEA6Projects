package Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joris on 15-5-2017.
 */
public class User {
    private String username;
    private String password;
    private List<User> subscriptions;
    private List<String> messages;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.subscriptions = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void addSubscription(User subscription) {
        this.subscriptions.add(subscription);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
