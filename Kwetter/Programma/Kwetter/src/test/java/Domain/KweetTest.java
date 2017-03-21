package Domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Joris on 3-3-2017.
 */
public class KweetTest {
    User user1;
    User user2;
    User user3;
    Kweet kweet1;
    Kweet kweet2;
    Kweet kweet3;
    Kweet kweet4;
    Calendar date;

    @Before
    public void setUp() throws Exception {
        user1 = new User(1, null, "user1", null, null, null, null);
        user2 = new User(2, null, "user2", "I'm user2.", "http://user2.nl/", "home", null);
        user3 = new User(3, "img.jpg", "NAME", null, null, null, null);
        kweet1 = new Kweet(1, "HELLO EVERYONE", user1);
        kweet2 = new Kweet(2, "HEY EVERYBODY", user2);
        kweet3 = new Kweet(3, "Excuse me for the shouting.", user2);
        kweet4 = new Kweet();
        kweet1.addLiker(user1);
        kweet1.addLiker(user2);
        date = Calendar.getInstance();
    }

    @Test
    public void getId() throws Exception {
        Assert.assertEquals("Kweet1 got the wrong Id.", 1, kweet1.getId());
        Assert.assertEquals("Kweet2 got the wrong Id.", 2, kweet2.getId());
        Assert.assertEquals("Kweet3 got the wrong Id.", 3, kweet3.getId());
    }

    @Test
    public void getMessage() throws Exception {
        Assert.assertEquals("Kweet1 got the wrong message.", "HELLO EVERYONE", kweet1.getMessage());
        Assert.assertEquals("Kweet2 got the wrong message.", "HEY EVERYBODY", kweet2.getMessage());
        Assert.assertEquals("Kweet3 got the wrong message.", "Excuse me for the shouting.", kweet3.getMessage());
    }

    @Test
    public void setMessage() throws Exception {
        kweet4.setMessage("Why does everybody scream here?");
        Assert.assertEquals("Message wasn't properly set to Kweet4", "Why does everybody scream here?", kweet4.getMessage());
    }

    @Test
    public void getUser() throws Exception {
        Assert.assertEquals("Kweet1 got the wrong user.", user1, kweet1.getUser());
        Assert.assertEquals("Kweet2 got the wrong user.", user2, kweet2.getUser());
        Assert.assertEquals("Kweet3 got the wrong user.", user2, kweet3.getUser());
    }

    @Test
    public void getDate() throws Exception {
        Assert.assertEquals("Kweet1 got the wrong date.", date.get(Calendar.DAY_OF_MONTH), kweet1.getDate().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals("Kweet2 got the wrong date.", date.get(Calendar.DAY_OF_MONTH), kweet2.getDate().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals("Kweet3 got the wrong date.", date.get(Calendar.DAY_OF_MONTH), kweet3.getDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void getLiker() throws Exception {
        Assert.assertEquals("Kweet1 got another amount of likers than expected", 2, kweet1.getLikers().size());
    }

    @Test
    public void addLiker() throws Exception {
        kweet1.addLiker(user3);
        Assert.assertEquals("Kweet1 didn't get another liker.", 3, kweet1.getLikers().size());
    }

    @Test(expected = Exception.class)
    public void addExistingLikerException() throws Exception {
        kweet1.addLiker(user1);
    }


}