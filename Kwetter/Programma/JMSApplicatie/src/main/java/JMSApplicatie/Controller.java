package JMSApplicatie;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    Button btnLogin;
    @FXML
    Button btnKweet;
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;
    @FXML
    TextField tfMessage;
    @FXML
    Label lblInfo;

    private User currentUser = null;
    private MessagePublisher publisher = new MessagePublisher();

    public void loginHandler() {
        if(tfUsername.getText().length() >= 0 && tfPassword.getText().length() >= 0){
            currentUser = RestClient.login(tfUsername.getText(), tfPassword.getText());
        }

        if (currentUser == null){
            lblInfo.setText("Inloggen niet gelukt.");
        } else {
            lblInfo.setText("Ingelogd als: " + currentUser.getName());
            tfMessage.setDisable(false);
        }
    }

    public void kweetHandler() {
        if (currentUser != null) {
            String message = tfMessage.getText();
            Kweet kweet = Kweet.createKweet(currentUser, message);

            MessagePublisher.sendKweet(kweet);
        }
    }
}
