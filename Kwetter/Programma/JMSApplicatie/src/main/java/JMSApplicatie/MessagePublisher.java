package JMSApplicatie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Joris on 7-5-2017.
 */
public class MessagePublisher {
    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    private static GsonBuilder builder = new GsonBuilder();

    public static boolean sendKweet(Kweet kweet) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
            Connection connection = connectionFactory.createConnection("admin", "admin");
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("KweetQueue");

            MessageProducer producer = session.createProducer(destination);
            //producer.setDeliveryDelay(DeliveryMode.PERSISTENT);

            String body = gson.toJson(kweet);
            TextMessage message = session.createTextMessage(body);

            System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
            producer.send(message);

            session.close();
            connection.close();

            return true;

        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
    }
}
