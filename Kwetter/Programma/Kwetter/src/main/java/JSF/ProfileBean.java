package JSF;

import Domain.Kweet;
import Domain.User;
import Service.KweetService;
import Service.ModService;
import Service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
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
    private List<User> following;

    private FacesContext context;
    private ExternalContext externalContext;

    private User chosenUser;
    private User loggedInUser;

    private int followingAmount;
    private int followerAmount;
    private int kweetAmount;

    public ProfileBean() {
    }

    public String getChosenUserProfilePhoto() {
        if (chosenUser.getPhoto() == null || chosenUser.getPhoto().trim().length() <= 0)
            return "https://sst-software.nl/wp-content/uploads/2016/02/100_100_empty.gif";
        return chosenUser.getPhoto();
    }

    public void onLoad() throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        Object loggedInUser = externalContext.getSessionMap().get("loggedInUser");

        if (loggedInUser == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            return;
        }

        Object chosenUser = externalContext.getSessionMap().get("chosenUser");

        if (loggedInUser == null) {
            externalContext.getSessionMap().put("chosenUser", loggedInUser);
            externalContext.redirect(externalContext.getRequestContextPath() + "/profile.xhtml");
            return;
        }

        this.loggedInUser = (User) loggedInUser;
        this.chosenUser = (User) chosenUser;

        latestKweets = kweetService.latestKweets(this.chosenUser.getId(), 10);
        following = userService.getFollowing(this.chosenUser.getId());

        followerAmount = userService.getFollowers(this.chosenUser.getId()).size();
        followingAmount = following.size();
        kweetAmount = kweetService.latestKweets(this.chosenUser.getId(), 0).size();
    }

    public List<Kweet> getLatestKweets() {
        return latestKweets;
    }

    public List<User> getFollowing() {
        return following;
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

    public int getFollowingAmount() {
        return followingAmount;
    }

    public void setFollowingAmount(int followingAmount) {
        this.followingAmount = followingAmount;
    }

    public int getFollowerAmount() {
        return followerAmount;
    }

    public void setFollowerAmount(int followerAmount) {
        this.followerAmount = followerAmount;
    }

    public int getKweetAmount() {
        return kweetAmount;
    }

    public void setKweetAmount(int kweetAmount) {
        this.kweetAmount = kweetAmount;
    }

    public String getEditButtonText() {
        if (loggedInUser.getId() == chosenUser.getId())
            return "<a href='editUser.xhtml'>Edit Profile</a>";
        return null;
    }

    public void ownProfile() throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        externalContext.getSessionMap().put("chosenUser", loggedInUser);

        externalContext.redirect(externalContext.getRequestContextPath() + "/profile.xhtml");
    }

    public void openProfile(User userForProfile) throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        externalContext.getSessionMap().put("chosenUser", userForProfile);
        externalContext.redirect(externalContext.getRequestContextPath() + "/profile.xhtml");
    }
}