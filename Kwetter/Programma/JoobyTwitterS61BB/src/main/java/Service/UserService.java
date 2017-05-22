package Service;

import DAO.UserDAO;
import Domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joris on 15-5-2017.
 */
public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    private User getUser(String username) {
        return userDAO.getUser(username);
    }

    public List<User> getUsers(){
        return userDAO.getUsers();
    }

    public void addMessage(String message, String username) {
        User user = getUser(username);
        if (user == null)
            throw new NullPointerException("Can't find user: " + username);

        if (message == null || message.length() > 99 || message.length() < 1)
            throw new IllegalArgumentException("Message is wrong.");

        user.addMessage(message);
        userDAO.updateUser(user);
    }

    public List<String> getAllMessages() {
        List<String> messages = new ArrayList<>();

        for (User user : userDAO.getUsers())
            messages.addAll(user.getMessages());

        return messages;
    }

    public List<String> getMessages(String username){
        User user = getUser(username);
        if (user == null)
            throw new NullPointerException("Can't find user: " + username);

        return user.getMessages();
    }

    public List<String> getMessagesFromSubscriptions(String username) {
        User user = getUser(username);
        if (user == null)
            throw new NullPointerException("Can't find user: " + username);

        List<String> messages = new ArrayList<>();

        for (String innerUsername : user.getSubscriptions()) {
            User innerUser = getUser(innerUsername);
            messages.addAll(innerUser.getMessages());
        }
        return messages;
    }

    public User login(String username, String password) {
        for (User user : userDAO.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public User createUser(String username, String password) {
        User user = new User(username, password);
        userDAO.createUser(user);
        return user;
    }

    public void subscribe(String userName, String subscriptionName) {
        User user = userDAO.getUser(userName);
        User subscription = userDAO.getUser(subscriptionName);

        if (user == null || subscription == null)
            throw new NullPointerException("One of the users doesn't exist!");

        user.addSubscription(subscription.getUsername());
        userDAO.updateUser(user);
    }
}
