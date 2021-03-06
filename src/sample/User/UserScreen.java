package sample.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import sample.MainStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserScreen implements Initializable {

    //Variables
    @FXML Label name;

    //Object
    MainStorage sw = new MainStorage();
    RetrieveInfoFromDatabase newRetrieve = new RetrieveInfoFromDatabase();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void getName(String userEmail) {
        name.setText(newRetrieve.getName1(userEmail));
    }

    @FXML
    public void handleLogOutButton(ActionEvent event) throws IOException {
        sw.logOut(event);
    }

}
