package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUserScreenController implements Initializable {

    //Variables
    @FXML Label name;

    //Object
    SwitchScene sw = new SwitchScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void getName(String userEmail) {
        RetrieveInfoFromDatabase newRetrieve = new RetrieveInfoFromDatabase();
        name.setText(newRetrieve.getName(userEmail));
    }

    @FXML
    public void handleLogOutButton(ActionEvent event) throws IOException {
        sw.logOut(event);
    }

}
