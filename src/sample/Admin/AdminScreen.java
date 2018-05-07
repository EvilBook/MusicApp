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
import sample.Employee.EmployeeStorage;

import sample.MainStorage;
import sample.SwitchScene;

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

    EmployeeStorage goTo = new EmployeeStorage();



    @Override

    public void initialize(URL location, ResourceBundle resources) {



    }



    @FXML

    public void handleEmployeeButton(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewEmployee.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }



    @FXML

    public void handleStoreButton(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("employeeManagement.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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

        SwitchScene myapp = new SwitchScene();
        myapp.logOut(event);

    }



}
