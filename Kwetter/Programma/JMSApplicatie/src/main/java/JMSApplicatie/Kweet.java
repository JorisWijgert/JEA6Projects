package JMSApplicatie;

import java.util.Calendar;
import java.util.HashSet;

/**
 * Created by Joris on 7-5-2017.
 */
public class Kweet {

    private int id;
    private User user;
    private String message;
    private Calendar date;
    private HashSet<User> likers;

    public static Kweet createKweet(User user, String message){
        if (user == null) {
            return null;
        }

        Kweet kweet = new Kweet();
        kweet.setUser(user);
        kweet.setMessage(message);

        kweet.setLikers(new HashSet<User>());
        kweet.setDate(Calendar.getInstance());

        return kweet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public HashSet<User> getLikers() {
        return likers;
    }

    public void setLikers(HashSet<User> likers) {
        this.likers = likers;
    }
}
