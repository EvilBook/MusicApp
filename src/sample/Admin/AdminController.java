package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    //Variables
    @FXML private Label adminLabel;
    @FXML private Button logOutButton;

    //Objects
    SwitchScene sw = new SwitchScene();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleEmployeeButton(ActionEvent event) throws IOException {
        sw.empBack(event);
        System.out.println(event);
    }

    @FXML
    public void handleStoreButton(ActionEvent event) {

    }

    @FXML
    public void handleUserButton(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewUser.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleLogOutButton(ActionEvent event) throws IOException {
        sw.logOut(event);
    }

}
