package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.User.MainUserScreenController;

import java.io.IOException;

public class MainStorage {

    //Admin Screen
    public void mainAdminScreen(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin/mainAdminScreen.fxml"));
        Parent root;
        root = loader.load();

        Scene scene = new Scene(root, 1066.62, 600);
        stage.setScene(scene);
        stage.show();
    }

    //Employee Screen
    public void employeeScreen(ActionEvent event) throws IOException {

    }

    //User Screen
    public void mainUserScreen(ActionEvent event) throws IOException {

    }

    //Login screen
    public void logOut(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root,1066.62, 600);
        stage.setScene(scene);
        stage.show();
    }

}
