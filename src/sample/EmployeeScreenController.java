package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeScreenController implements Initializable {

    //Variables
    @FXML Label nameLabel;

    //Objects
    SwitchScene sw = new SwitchScene();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleLogOutButton (ActionEvent event) throws IOException {
        sw.logOut(event);
    }

    public void getName(String userEmail) {
        RetrieveInfoFromDatabase newRetrieve = new RetrieveInfoFromDatabase();
        nameLabel.setText(newRetrieve.getName(userEmail));
    }


}
