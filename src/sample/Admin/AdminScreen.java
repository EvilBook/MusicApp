package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.MainStorage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminScreen implements Initializable {


    //Variables
    @FXML private Label adminLabel;
    @FXML private Button logOutButton;

    //Objects
    AdminStorage access = new AdminStorage();
    MainStorage ms = new MainStorage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleEmployeeButton(ActionEvent event) throws IOException {
        access.viewEmployeeScene(event);
    }

    @FXML
    public void handleStoreButton(ActionEvent event) throws IOException {
        access.manageStore(event);
    }

    @FXML
    public void handleUserButton(ActionEvent event) throws IOException {
        access.viewUserScene(event);
    }

    @FXML
    public void handleLogOutButton(ActionEvent event) throws IOException {
        ms.logOut(event);
    }

}
