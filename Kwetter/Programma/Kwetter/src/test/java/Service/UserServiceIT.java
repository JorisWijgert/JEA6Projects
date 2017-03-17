package Service;

import DAO.RelationDAO;
import DAO.UserDAO;
import Domain.Group;
import Domain.Relation;
import Domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by Joris on 9-3-2017.
 */
public class UserServiceIT {
    User user1;
    User user2;
    Relation relation1;
    UserService userService;

    @Inject
    UserDAO userDAO;

    @Inject
    RelationDAO relationDAO;

    @Before
    public void setUp() throws Exception {
        user1 = new User(1, null, "user1", null, null, null, "password");
        user2 = new User(2, null, "user2", null, null, null, "password");
        userDAO.create(user1);
        userDAO.create(user2);
        relation1 = new Relation(user1, user2);
        relationDAO.follow(relation1);
        userService = new UserService();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createUser() throws Exception {
        User user3 = new User(3, null, "user3", null, null, null, "password");
        userService.createUser(user3);
    }

    @Test(expected = NullPointerException.class)
    public void createUserNoUsernameException() throws Exception {
        User user3 = new User(3, null, null, null, null, null, "password");
        userService.createUser(user3);
    }

    @Test(expected = NullPointerException.class)
    public void createUserEmptyUsernameException() throws Exception {
        User user3 = new User(3, null, "", null, null, null, "password");
        userService.createUser(user3);
    }

    @Test(expected = NullPointerException.class)
    public void createUserNoPasswordException() throws Exception {
        User user3 = new User(3, null, "user3", null, null, null, null);
        userService.createUser(user3);
    }

    @Test(expected = NullPointerException.class)
    public void createUserEmptyPasswordException() throws Exception {
        User user3 = new User(3, null, "user3", null, null, null, " ");
        userService.createUser(user3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUserNameAlreadyExists() throws Exception {
        User user3 = new User(3, null, "user1", null, null, null, "password");
        userService.createUser(user3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUserIdAlreadyExists() throws Exception {
        User user3 = new User(1, null, "user3", null, null, null, "password");
        userService.createUser(user3);
    }

    @Test
    public void updateUser() throws Exception {
        User user4 = new User(1, null, "user1", null, "user1.nl/", null, "password");
        userService.updateUser(user4);
        Assert.assertEquals("Web isn't set for user1.", "user1.nl/", userService.getUser(1).getWeb());
    }

    @Test(expected = NullPointerException.class)
    public void updateUserUnknownUserException() throws Exception {
        User user4 = new User(4, null, "user4", null, null, null, "password");
        userService.updateUser(user4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserExistingUserNameException() throws Exception {
        User updateUser = new User(1, null, "user2", null, null, null, "password");
        userService.updateUser(updateUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserChangeGroupNameException() throws Exception {
        Group group = new Group();
        group.setGroupName("Moderator");
        User updateUser = new User(1, null, "user1", null, null, null, "password");
        ;
        updateUser.setGroup(group);
        userService.updateUser(updateUser);
    }

    @Test
    public void getUser() throws Exception {
        Assert.assertEquals("Unexpected user received instead of user1.", user1, userService.getUser("user1"));
    }

    @Test
    public void getUser1() throws Exception {
        Assert.assertEquals("Unexpected user received instead of user1.", user1, userService.getUser(1));
    }

    @Test
    public void follow() throws Exception {
        userService.follow(2, 1);
    }

    @Test(expected = NullPointerException.class)
    public void followNonExistingFollowerException() throws Exception {
        userService.follow(20, 1);
    }

    @Test(expected = NullPointerException.class)
    public void followNonExistingFollowingException() throws Exception {
        userService.follow(2, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void followAlreadyFollowedException() throws Exception {
        userService.follow(1, 2);
    }

    @Test
    public void getFollowers() throws Exception {
        Assert.assertEquals("Follower not found.", user1, userService.getFollowers(2).get(0));
    }

    @Test (expected = NullPointerException.class)
    public void getFollowersNoFollowingException() throws Exception {
        userService.getFollowers(20);
    }

    @Test
    public void getFollowing() throws Exception {
        Assert.assertEquals("Follower not found.", user2, userService.getFollowing(1).get(0));
    }

    @Test (expected = NullPointerException.class)
    public void getFollowingNoFollowerException() throws Exception {
        userService.getFollowing(20);
    }

}