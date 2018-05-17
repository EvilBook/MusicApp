package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Employee.EmployeeStorage;
import sample.MainStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MusicManagement implements Initializable {

    //Variables
    @FXML private Button addMusicButton;
    @FXML private Button removeMusicButton;
    @FXML private Button viewMusicButton;

    //Objects
    MainStorage access = new MainStorage();
    EmployeeStorage goTo = new EmployeeStorage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addMusic(ActionEvent event) throws IOException {
        goTo.employeeAddMusic(event, addMusicButton);
    }

    public void removeMusic(ActionEvent event) throws IOException {
        goTo.employeeRemoveMusic(event, removeMusicButton);
    }

    public void viewMusic(ActionEvent event) throws IOException {
        goTo.employeeAddMusic(event, viewMusicButton);
    }

    public void handleReturnButton(ActionEvent event) throws IOException {
        access.mainAdminScreen(event);
    }
}

