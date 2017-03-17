package Domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joris on 3-3-2017.
 */
public class RelationTest {
    User user1;
    User user2;
    Relation relation1;
    Relation relation2;

    @Before
    public void setUp() throws Exception {
        new Relation();
        user1 = new User(1, null, "user1", null, null, null, null);
        user2 = new User(2, null, "user2", null, null, null, null);
        relation1 = new Relation(user1, user2);
        relation2 = new Relation(user2, user1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addRelationToMyselfFail() throws Exception {
        Relation relation = new Relation(user1, user1);
    }

    @Test
    public void getFollower() throws Exception {
        Assert.assertEquals("User1 wasn't the follower in relation1", user1, relation1.getFollower());
        Assert.assertEquals("User2 wasn't the follower in relation2", user2, relation2.getFollower());
    }

    @Test
    public void getFollowing() throws Exception {
        Assert.assertEquals("User2 wasn't the following in relation1", user2, relation1.getFollowing());
        Assert.assertEquals("User1 wasn't the following in relation2", user1, relation2.getFollowing());
    }

}