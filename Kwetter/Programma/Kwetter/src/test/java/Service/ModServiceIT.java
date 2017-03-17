package Service;

import DAO.KweetDAO;
import DAO.UserDAO;
import Domain.Kweet;
import Domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by Joris on 9-3-2017.
 */
public class ModServiceIT {
    User user1;
    User user2;
    Kweet kweet1;
    ModService modService;

    @Inject
    KweetDAO kweetDAO;

    @Inject
    UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        user1 = new User(1, null, "user1", null, null, null, "hoi");
        user2 = new User(2, null, "user2", null, null, null, "hoi");
        kweet1 = new Kweet(1, "Hey people!", user1);
        userDAO.create(user1);
        userDAO.create(user2);
        kweetDAO.create(kweet1);
        modService = new ModService();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void changeRole() throws Exception {
        modService.changeRole(1, "Moderator");
    }

    @Test(expected = NullPointerException.class)
    public void changeRoleUserNotFoundException() throws Exception {
        modService.changeRole(20, "Moderator");
    }

    @Test
    public void getAllUsers() throws Exception {
        Assert.assertEquals("Another amount of users received.", 2, modService.getAllUsers().size());
    }

    @Test
    public void getAllKweets() throws Exception {
        Assert.assertEquals("Another kweets of users received.", 1, modService.getAllKweets().size());
    }

    @Test
    public void deleteKweet() throws Exception {
        modService.deleteKweet(1);
        Assert.assertEquals("Kweet wasn't deleted.", 0, modService.getAllKweets().size());
    }

    @Test(expected = NullPointerException.class)
    public void deleteKweetNotFoundException() throws Exception {
        modService.deleteKweet(20);
    }
}