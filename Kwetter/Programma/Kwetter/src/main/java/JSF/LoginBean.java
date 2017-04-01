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
 * Created by Joris on 26-3-2017.
 */
@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    UserService userService;

    private String username;
    private String password;

    private User chosenUser;

    private FacesContext context;
    private ExternalContext externalContext;

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

    public String onLoad() throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        return null;
    }

    public void login() throws NoSuchAlgorithmException, IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        User userToLogin = new User(0, null, username, null, null, null, password);
        User foundUser = userService.getUser(username);

        if (userToLogin.getPassword() == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/error.xhtml");
            return;
        }

        if (foundUser == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/error.xhtml");
            return;
        }

        if (foundUser.getPassword() == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/error.xhtml");
            return;
        }

        if (!userToLogin.getPassword().equals(foundUser.getPassword())) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/error.xhtml");
            return;
        }
        setChosenUser(foundUser);

        externalContext.getSessionMap().put("loggedInUser", foundUser);
        externalContext.getSessionMap().put("chosenUser", foundUser);
        externalContext.redirect(externalContext.getRequestContextPath() + "/profile.xhtml");
    }

    public void logout() throws IOException {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();

        externalContext.getSessionMap().remove("loggedInUser");
        externalContext.getSessionMap().remove("chosenUser");

        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }
}
