package Domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joris on 6-3-2017.
 */
public class GroupTest {
    Group group1;
    Group group2;
    Group group3;

    @Before
    public void setUp() {
        group1 = new Group();
        group2 = new Group();
        group3 = new Group();
    }


    @Test
    public void getGroupName() throws Exception {
        Assert.assertEquals("Group1 wasn't User", "User", group1.getGroupName());
        Assert.assertEquals("Group2 wasn't User", "User", group2.getGroupName());
        Assert.assertEquals("Group3 wasn't User", "User", group3.getGroupName());
    }

    @Test
    public void setGroupName() throws Exception {
        group2.setGroupName("Admin");
        Assert.assertEquals("Group2 wasn't changed into Admin", "Admin", group2.getGroupName());
        group3.setGroupName("Moderator");
        Assert.assertEquals("Group2 wasn't changed into Moderator", "Moderator", group3.getGroupName());
    }

}