package JSF;

import Domain.User;
import Service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

/**
 * Created by Joris on 21-3-2017.
 */
@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {
    @Inject
    UserService userService;

    private User user;

    private int userId;
    private String username;
    private String password;

    public UserBean() {
    }

    public User getUser() {
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userService.getUser(principal.getName()); // Find User by j_username.
            }
        }
        return user;
    }

    public void login(String username, String password) throws NoSuchAlgorithmException, IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        User userToLogin = new User(0, null, username, null, null, null, password);
        User foundUser = userService.getUser(username);

        if(foundUser == null)
            externalContext.redirect("/error.xhtml");

        if(!userToLogin.getPassword().equals(foundUser.getPassword()))
            externalContext.redirect(externalContext.getRequestContextPath() + "/error.xhtml");

        user = foundUser;
        externalContext.redirect(externalContext.getRequestContextPath() + "/profile.xhtml");
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

    public void setUser(String username){
        this.username = username;
    }

    public void createUser() throws NoSuchAlgorithmException {
        userService.createUser(new User(0, null, username, null, null, null, password));
    }

    public void updateUser() throws NoSuchAlgorithmException {
        userService.updateUser(new User(userId, null, username, null, null, null, password));
    }
}
