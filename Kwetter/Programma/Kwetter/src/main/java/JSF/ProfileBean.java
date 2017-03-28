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

    private User chosenUser;
    private User loggedInUser;

    public ProfileBean() {
    }

    public String getChosenUserProfilePhoto() {
        if (chosenUser.getPhoto() == null)
            return "https://sst-software.nl/wp-content/uploads/2016/02/100_100_empty.gif";
        return chosenUser.getPhoto();
    }

    public void onLoad(User chosenUser) throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        Object object = externalContext.getSessionMap().get("user");

        if (object == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            return;
        }

        loggedInUser = (User) object;

        this.chosenUser = chosenUser;

        latestKweets = kweetService.latestKweets(chosenUser.getId(), 10);
    }

    public List<Kweet> getLatestKweets() {
        return latestKweets;
    }

    public User getChosenUser() {
        return chosenUser;
    }

    public void setChosenUser(User chosenUser) {
        this.chosenUser = chosenUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getEditButtonText(){
        if(loggedInUser.getId() == chosenUser.getId())
            return "<a href='editUser.xhtml'>Edit Profile</a>";
        return null;
    }

//    public void updateUser() throws NoSuchAlgorithmException, IOException {
//        userService.updateUser(new User(userId, null, username, null, null, null, password));
//    }
}