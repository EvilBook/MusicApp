package sample.Admin;



import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;



public class AdminStorage {





    public void viewEmployeeScene(ActionEvent event) throws IOException {

        System.out.println(event);

        Node node = (Node)event.getSource();

        Stage stage = (Stage)node.getScene().getWindow();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("employeeManagement.fxml"));

        Parent root;

        root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

        System.out.println("it works");

    }



    /*public void mainAdminScene(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();

        Stage stage = (Stage)node.getScene().getWindow();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminScreen.fxml"));

        Parent root;

        root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }*/



    public void manageStore(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();

        Stage stage = (Stage)node.getScene().getWindow();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("musicManagement.fxml"));

        Parent root;

        root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }



    public void viewUserScene(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();

        Stage stage = (Stage)node.getScene().getWindow();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("userManagement.fxml"));

        Parent root;

        root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }



    public void removeEmployeeScene(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();

        Stage stage = (Stage)node.getScene().getWindow();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("removeEmployee.fxml"));

        Parent root;

        root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }



    public void addEmployeeScene(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();

        Stage stage = (Stage)node.getScene().getWindow();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployee.fxml"));

        Parent root;

        root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }







}
