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
import java.util.List;

/**
 * Created by Joris on 2-4-2017.
 */
@Named("mainPageBean")
@SessionScoped
public class MainPageBean implements Serializable{

    @Inject
    UserService userService;
    @Inject
    KweetService kweetService;

    private FacesContext context;
    private ExternalContext externalContext;

    private List<Kweet> timeLine;
    private List<Kweet> searchResults;

    private List<User> followingUsers;
    private List<User> followerUsers;

    private User loggedInUser;

    private String kweetText;
    private String searchQuery;

    public void onLoad() throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        Object loggedInUser = externalContext.getSessionMap().get("loggedInUser");

        if (loggedInUser == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            return;
        }

        this.loggedInUser = (User) loggedInUser;

        timeLine = kweetService.getTimeline(this.loggedInUser.getId());
        followingUsers = userService.getFollowing(this.loggedInUser.getId());
        followerUsers = userService.getFollowers(this.loggedInUser.getId());
    }

    public User getLoggedInUser() throws IOException {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<Kweet> getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(List<Kweet> timeLine) {
        this.timeLine = timeLine;
    }

    public String getKweetText() {
        return kweetText;
    }

    public void setKweetText(String kweetText) {
        this.kweetText = kweetText;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Kweet> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Kweet> searchResults) {
        this.searchResults = searchResults;
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }

    public void setFollowingUsers(List<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public List<User> getFollowerUsers() {
        return followerUsers;
    }

    public void setFollowerUsers(List<User> followerUsers) {
        this.followerUsers = followerUsers;
    }

    public void postKweet() {
        kweetService.createKweet(new Kweet(0, kweetText, loggedInUser));
        kweetText = "";
        timeLine = kweetService.getTimeline(this.loggedInUser.getId());
    }

    public void searchKweets() throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        searchResults = kweetService.searchKweet(searchQuery);
        searchQuery = "";
        externalContext.redirect(externalContext.getApplicationContextPath() + "/searchresults.xhtml");
    }
}
