package sample.Employee;

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
import sample.MainStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainEmployeeScreenController implements Initializable {

    //Variables
    @FXML Label nameLabel;

    //Objects
    EmployeeStorage access = new EmployeeStorage();
    MainStorage proceed = new MainStorage();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void getName(String userEmail) {
        RetrieveInfoFromDatabase newRetrieve = new RetrieveInfoFromDatabase();
        nameLabel.setText(newRetrieve.getName(userEmail));
    }

    public void handleAddButton(ActionEvent event) throws IOException {
        access.employeeAddMusic(event);
    }

    public void handleDeleteButton(ActionEvent event) throws IOException {
        access.employeeRemoveMusic(event);
    }

    @FXML
    public void handleLogOutButton (ActionEvent event) throws IOException {
        proceed.logOut(event);
    }
}
