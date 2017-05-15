package Domain;

/**
 * Created by Joris on 15-5-2017.
 */
public class MessageJson {
    private String username;
    private String message;

    public MessageJson() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
