package JMSApplicatie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

/**
 * Created by Joris on 7-5-2017.
 */
public class RestClient {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    private static GsonBuilder builder = new GsonBuilder();

    public static User login(String username, String password) {
        User user = null;

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(
                    "http://localhost:8080/JEA6Kwetter/api/users/login");

            String jsonString = "{ \"name\": \"" + username + "\", \"password\": \"" + password + "\" }";

            StringEntity input = new StringEntity(jsonString);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " +
                        response.getStatusLine().getStatusCode());

            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;

            StringBuilder json = new StringBuilder();
            while ((output = br.readLine()) != null) {
                json.append(output);

            }

            user = gson.fromJson(json.toString(), User.class);

            httpClient.getConnectionManager().shutdown();


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

        return user;
    }
}
