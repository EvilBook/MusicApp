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

public class EmployeeScreen implements Initializable {

    //Variables
    @FXML Label nameLabel;

    //Objects
    MainStorage sw = new MainStorage();


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

    @FXML
    private void handleSwitchScenesAdd(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addMusic.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSwitchScenesRemove(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("removeMusic.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSwitchScenesView(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewMusic.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
