package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Employee.Employee;
import sample.User.MainUserScreenController;

import java.io.IOException;

public class MainStorage {

    //Admin Screen
    public void mainAdminScreen(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin/adminScreen.fxml"));
        Parent root;
        root = loader.load();

        Scene scene = new Scene(root, 1066.62, 600);
        stage.setScene(scene);
        stage.show();
    }

    //Employee Screen
    public void employeeScreen(ActionEvent event, String userEmail) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Employee/employeeScreen.fxml"));
        Parent root;
        root = loader.load();

        Employee emp = loader.getController();
        emp.getEmail(userEmail);

        Scene scene = new Scene(root,1066.62, 600);
        stage.setScene(scene);
        stage.show();
    }

    //User Screen
    public void mainUserScreen(ActionEvent event, String userEmail) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("User/MainUserScreen.fxml"));
        Parent root;
        root = loader.load();

       /* UserScreen one = loader.getController();
        one.getName(userEmail);*/

        Scene scene = new Scene(root,1066.62, 600);
        stage.setScene(scene);
        stage.show();
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
