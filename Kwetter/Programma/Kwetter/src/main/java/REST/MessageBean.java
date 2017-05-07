package REST;

import Domain.Kweet;
import Domain.User;
import Service.KweetService;
import Service.UserService;
import com.google.gson.Gson;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;

/**
 * Created by Joris on 3-5-2017.
 */
@MessageDriven(name = "kwetterMdb", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/KwetterAdminResource"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "KweetQueue"),
        @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.12.0")
})
public class MessageBean implements MessageListener{
    private Gson gson = new Gson();

    @Inject
    KweetService kweetService;

    @Inject
    UserService userService;


    @Override
    public void onMessage(Message message) {
        try {
            Kweet kweet = gson.fromJson(((ActiveMQTextMessage) message).getText(), Kweet.class);
            List<User> followers = userService.getFollowers(kweet.getUser().getId());
            kweetService.createKweet(kweet, followers);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
