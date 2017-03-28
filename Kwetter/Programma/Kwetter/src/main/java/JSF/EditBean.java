package JSF;

import Domain.User;
import Service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Joris on 28-3-2017.
 */
@Named("editBean")
@SessionScoped
public class EditBean implements Serializable {

    @Inject
    UserService userService;

    private FacesContext context;
    private ExternalContext externalContext;

    private int id;
    private String username;
    private String password;
    private String photo;
    private String bio;
    private String location;
    private String web;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void onLoad() throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        Object object = externalContext.getSessionMap().get("user");

        if (object == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            return;
        }

        User loggedInUser = (User) object;
        this.id = loggedInUser.getId();
        this.username = loggedInUser.getName();
        this.photo = loggedInUser.getPhoto();
        this.bio = loggedInUser.getBio();
        this.web = loggedInUser.getWeb();
        this.location = loggedInUser.getLocation();
    }

    public void updateUser() throws NoSuchAlgorithmException, IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        userService.updateUser(new User(id, photo, username, bio, web, location, password));
        externalContext.redirect(externalContext.getRequestContextPath() + "/profile.xhtml");
    }
}
