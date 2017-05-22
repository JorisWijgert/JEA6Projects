package Domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joris on 15-5-2017.
 */
@Entity
@Table(name = "twitteruser")
@NamedQueries({@NamedQuery(name = "user.all", query = "SELECT u FROM User u"),
        @NamedQuery(name = "user.getByName", query = "SELECT u FROM User u WHERE u.username = :username")})
public class User {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @ElementCollection
    @CollectionTable(name ="subscription")
    private List<String> subscriptions;
    @ElementCollection
    @CollectionTable(name ="message")
    private List<String> messages;

    public User() {
    }

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

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<String> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void addSubscription(String subscription) {
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
