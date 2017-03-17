package Domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Joris on 3-3-2017.
 */
public class UserTest {
    User user1;
    User user2;
    User user3;

    @Before
    public void setUp() throws Exception {
        user1 = new User(1, "http://www.img.nl/1.jpg", "nr1", "I'm number one", "http://www.nr1.nl/", "home", "ww1");
        user2 = new User(2, "http://www.img.nl/2.jpg", "nr2", "I'm number two", "http://www.nr2.nl/", "work", "ww2");
        user3 = new User();
    }

    @Test
    public void getId() throws Exception {
        Assert.assertEquals( "User1 didn't have the id 1.", 1, user1.getId());
        Assert.assertEquals( "User2 didn't have the id 2.", 2, user2.getId());
     }

    @Test
    public void getPhoto() throws Exception {
        Assert.assertEquals("User1 didn't have the photo http://www.img.nl/1.jpg.", "http://www.img.nl/1.jpg", user1.getPhoto());
        Assert.assertEquals("User2 didn't have the photo http://www.img.nl/2.jpg.", "http://www.img.nl/2.jpg", user2.getPhoto());
        Assert.assertNull("User3 had a photo when there shouldn't be one.", user3.getPhoto());
    }

    @Test
    public void setPhoto() throws Exception {
        user3.setPhoto("http://www.img.nl/3.jpg");
        Assert.assertEquals("Photo isn't correctly set to the user.", "http://www.img.nl/3.jpg", user3.getPhoto());
    }

    @Test
    public void getName() throws Exception {
        Assert.assertEquals("User1 didn't have the name nr1.", "nr1", user1.getName());
        Assert.assertEquals("User2 didn't have the name nr2.", "nr2", user2.getName());
        Assert.assertNull("User3 had a name when there shouldn't be one.", user3.getName());
    }

    @Test
    public void setName() throws Exception {
        user3.setName("nr3");
        Assert.assertEquals("Name isn't correctly set to the user.", "nr3", user3.getName());
    }

    @Test
    public void getBio() throws Exception {
        Assert.assertEquals("User1 didn't have the bio I'm number one.", "I'm number one", user1.getBio());
        Assert.assertEquals("User2 didn't have the bio I'm number two.", "I'm number two", user2.getBio());
        Assert.assertNull("User3 had a bio when there shouldn't be one.", user3.getBio());
    }

    @Test
    public void setBio() throws Exception {
        user3.setBio("I'm number three");
        Assert.assertEquals("Bio isn't correctly set to the user.", "I'm number three", user3.getBio());
    }

    @Test
    public void getWeb() throws Exception {
        Assert.assertEquals("User1 didn't have the web http://www.nr1.nl/.", "http://www.nr1.nl/", user1.getWeb());
        Assert.assertEquals("User2 didn't have the web http://www.nr2.nl/.", "http://www.nr2.nl/", user2.getWeb());
        Assert.assertNull("User3 had a web when there shouldn't be one.", user3.getWeb());
    }

    @Test
    public void setWeb() throws Exception {
        user3.setWeb("http://www.nr3.nl/");
        Assert.assertEquals("Web isn't correctly set to the user.", "http://www.nr3.nl/", user3.getWeb());
    }

    @Test
    public void getLocation() throws Exception {
        Assert.assertEquals("User1 didn't have the location home.", "home", user1.getLocation());
        Assert.assertEquals("User2 didn't have the location work.", "work", user2.getLocation());
        Assert.assertNull("User3 had a location when there shouldn't be one.", user3.getLocation());
    }

    @Test
    public void setLocation() throws Exception {
        user3.setLocation("school");
        Assert.assertEquals("Location isn't correctly set to the user.", "school", user3.getLocation());
    }

    @Test
    public void getRole() throws Exception {
        Assert.assertEquals("User1 didn't have the role User.", "User", user1.getGroup().getGroupName());
        Assert.assertEquals("User2 didn't have the role User.", "User", user2.getGroup().getGroupName());
        Assert.assertEquals("User3 didn't have the role User.", "User", user3.getGroup().getGroupName());
    }

    @Test
    public void setRole() throws Exception {
        Group user1Group = new Group();
        user1Group.setGroupName("Moderator");
        user1.setGroup(user1Group);
        Assert.assertEquals("Role Moderator hasn't been set correctly to User1.", "Moderator", user1.getGroup().getGroupName());

        Group user2Group = new Group();
        user2Group.setGroupName("Admin");
        user2.setGroup(user2Group);
        Assert.assertEquals("Role Admin hasn't been set correctly to User2.", "Admin", user2.getGroup().getGroupName());
    }

    @Test
    public void getPassword() throws Exception {
        Assert.assertEquals("User1 didn't have the password ww1", "a265286d677b4b77689c84d85ae7fd88a77a2a31b054ac5541bca1ebabc4040d", user1.getPassword());
        Assert.assertEquals("User2 didn't have the password ww2.", "151bacff996cb5510986f9d2e13a7c2412618456da6acee7693f64d8e62092aa", user2.getPassword());
        Assert.assertNull("User3 had a password when there shouldn't be one.", user3.getPassword());
    }

    @Test
    public void setPassword() throws Exception {
        user3.setPassword("ww3");
        Assert.assertEquals("Password isn't correctly set to the user.", "8fe5178ece480a254916d473550f6ebda5c35e0d701fd0f792721dedd9956f3a", user3.getPassword());
    }
}