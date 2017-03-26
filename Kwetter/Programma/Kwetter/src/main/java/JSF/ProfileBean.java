package JSF;

import Domain.Kweet;
import Domain.User;
import Service.KweetService;
import Service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;

/**
 * Created by Joris on 21-3-2017.
 */
@Named("profileBean")
@SessionScoped
public class ProfileBean implements Serializable {
    @Inject
    UserService userService;

    @Inject
    KweetService kweetService;

    private int userId;

    private List<Kweet> latestKweets;

    private FacesContext context;
    private ExternalContext externalContext;
    private String username;
    private String password;

    private User chosenUser;

    public ProfileBean() {
    }

    public String getChosenUserProfilePhoto() {
        if (chosenUser.getPhoto() == null)
            return "https://sst-software.nl/wp-content/uploads/2016/02/100_100_empty.gif";
        return chosenUser.getPhoto();
    }

    public String onLoad(User chosenUser) throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        this.chosenUser = chosenUser;

        latestKweets = kweetService.latestKweets(chosenUser.getId(), 10);
        return null;
    }

    public List<Kweet> getLatestKweets() {
        return latestKweets;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUser(String username) {
        this.username = username;
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

    public User getChosenUser() {
        return chosenUser;
    }

    public void setChosenUser(User chosenUser) {
        this.chosenUser = chosenUser;
    }

    public void createUser() throws NoSuchAlgorithmException {
        userService.createUser(new User(0, null, username, null, null, null, password));
    }

    public void updateUser() throws NoSuchAlgorithmException {
        userService.updateUser(new User(userId, null, username, null, null, null, password));
    }
}