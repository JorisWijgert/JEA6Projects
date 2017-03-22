package JSF;

import Domain.User;
import Service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Joris on 21-3-2017.
 */
@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {
    @Inject
    private UserService userService;

    private int userId;
    private String username;
    private String password;

    public UserBean() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void createUser() throws NoSuchAlgorithmException {
        userService.createUser(new User(0, null, username, null, null, null, password));
    }

    public void updateUser() throws NoSuchAlgorithmException {
        userService.updateUser(new User(userId, null, username, null, null, null, password));
    }
}
