package sample.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeScreen implements Initializable {

    //Variables
    @FXML Label nameLabel;
    @FXML Button addButton, viewButton, deleteButton;

    //Objects
    SwitchScene sw = new SwitchScene();
    EmployeeStorage goTo = new EmployeeStorage();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        DropShadow ds = new DropShadow();
//        addButton.setEffect(ds);
    }

    @FXML
    public void handleLogOutButton (ActionEvent event) throws IOException {
        sw.logOut(event);
    }

    public void getName(String userEmail) {
        RetrieveInfoFromDatabase newRetrieve = new RetrieveInfoFromDatabase();
        nameLabel.setText(newRetrieve.getName1(userEmail));
    }


    @FXML
    private void handleSwitchScenesAdd(ActionEvent event) throws IOException {
        goTo.employeeAddMusic(event, addButton);

    }

    @FXML
    private void handleSwitchScenesRemove(ActionEvent event) throws IOException {
        goTo.employeeRemoveMusic(event, deleteButton);
    }

    @FXML
    private void handleSwitchScenesView(ActionEvent event) throws IOException {
        goTo.employeeViewMusic(event, viewButton);
    }


}
