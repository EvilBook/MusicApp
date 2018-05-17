package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Employee.EmployeeScreen;

import java.io.IOException;

public class SwitchScene {

    //Method to go back to the login screen
    public void logOut(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root,1066.22, 600);
        stage.setScene(scene);
        stage.show();
    }

    //Method to go back to the User Main Screen
    public void userBack(ActionEvent event) {

    }

    //Method to go back to the Employee Main Screen
    public void empScreen(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Employee/employeeScreen.fxml"));
        Parent root;
        root = loader.load();

        EmployeeScreen emp = loader.getController();


        Scene scene = new Scene(root,1066.62, 600);
        stage.setScene(scene);
        stage.show();


    }

    //Method to go back to the Admin Main Screen
    public void adminBack(ActionEvent event) {

    }

}
