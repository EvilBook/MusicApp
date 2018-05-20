package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Employee.EmployeeScreen;
import sample.User.UserScreen;

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

        EmployeeScreen emp = loader.getController();
        emp.getName(userEmail);

        Scene scene = new Scene(root,1066.62, 600);
        stage.setScene(scene);
        stage.show();
    }

    //User Screen
    public void mainUserScreen(ActionEvent event, String userEmail) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUserScreen.fxml"));
        Parent root;
        root = loader.load();

        MainUserScreenController one = loader.getController();
        one.setUserEmail(userEmail);

        Scene scene = new Scene(root,1066.62, 600);
        stage.setScene(scene);
        stage.show();
    }

    //LoginController screen
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
