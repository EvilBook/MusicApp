package sample.Admin;



import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.stage.Modality;

import javafx.stage.Stage;

import sample.Employee.EmployeeStorage;

import sample.MainStorage;



import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;



public class MusicManagement implements Initializable {



    //Variables

    @FXML private Button addMusicButton;

    @FXML private Button removeMusicButton;



    //Objects

    MainStorage access = new MainStorage();

    EmployeeStorage goTo = new EmployeeStorage();







    @Override

    public void initialize(URL location, ResourceBundle resources) {



    }



    public void addMusic(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeAddMusic.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    public void removeMusic(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteAlbum.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    public void handleReturnButton(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminScreen.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}
