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
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import sample.MainStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeScreen implements Initializable {

    //Variables
    @FXML Label nameLabel;
    @FXML Button addButton;
    @FXML Button removeButton;

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
        access.employeeAddMusic(event, addButton);
    }

    public void handleDeleteButton(ActionEvent event) throws IOException {
        access.employeeRemoveMusic(event, removeButton);
    }

    @FXML
    public void handleLogOutButton (ActionEvent event) throws IOException {
        proceed.logOut(event);
    }
}
