package Domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;

/**
 * Created by Joris on 3-3-2017.
 */

@Entity
@Table(name = "kweet")
@NamedQueries({@NamedQuery(name = "kweet.timeline", query = "SELECT k FROM Kweet k WHERE k.user = :user OR k.user IN (SELECT r.following FROM Relation r WHERE r.follower = :user) ORDER BY k.date DESC"),
        @NamedQuery(name = "kweet.latest", query = "SELECT k FROM Kweet k WHERE k.user = :user ORDER BY k.date DESC"),
        @NamedQuery(name = "kweet.search", query = "SELECT k FROM Kweet k WHERE k.message LIKE CONCAT('%',:keyword,'%')"),
        @NamedQuery(name = "kweet.all", query = "SELECT k  FROM Kweet k ORDER BY k.date"),
        @NamedQuery(name = "kweet.getById", query = "SELECT k FROM Kweet k WHERE k.id = :kweetId")})
public class Kweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "message")
    private String message;
    @ManyToOne
    private User user;
    @Column(name = "date")
    private Calendar date;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "likers", joinColumns = @JoinColumn(name = "kweet_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private HashSet<User> likers;

    public Kweet() {
        date = Calendar.getInstance();
        likers = new HashSet<>();
    }

    public Kweet(int id, String message, User user) {
        this.id = id;
        this.message = message;
        this.user = user;
        date = Calendar.getInstance();
        likers = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public Calendar getDate() {
        return date;
    }

    public HashSet<User> getLikers() {
        return likers;
    }

    public void addLiker(User user) throws Exception {
        if (!likers.add(user))
            throw new Exception("The user already liked this kweet.");
    }
}
