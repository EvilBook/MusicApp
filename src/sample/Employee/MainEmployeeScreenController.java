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
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainEmployeeScreenController implements Initializable {

    //Variables
    @FXML
    Label nameLabel;

    //Objects
    SwitchScene sw = new SwitchScene();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleLogOutButton(ActionEvent event) throws IOException {
        sw.logOut(event);
    }

    public void getName(String userEmail) {
        RetrieveInfoFromDatabase newRetrieve = new RetrieveInfoFromDatabase();
        nameLabel.setText(newRetrieve.getName(userEmail));
    }


    @FXML
    public void handleAddAlbumButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeAddMusic.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void handledeleteAlbumButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteAlbum.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void handleViewAlbum(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAlbum.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}
