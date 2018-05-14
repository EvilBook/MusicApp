package sample.User;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import sample.LoginController;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {

    private String userEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() ->{
            LoginController log = new LoginController();
            userEmail = log.getUserEmail();
            System.out.println("the user email is : " + userEmail);

        });
    }


}
