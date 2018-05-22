package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Employee.EmployeeStorage;
import sample.Employee.ViewMusicPopup;
import sample.MainStorage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminScreen implements Initializable {

    //Variables
    @FXML private Label adminLabel;
    @FXML private Button logOutButton, addmusic, viewmusic, deletemusic, user, deleteemp, addemp;
    @FXML private ImageView userImg, musicImg, empImg;

    //Objects
    AdminStorage access = new AdminStorage();
    MainStorage ms = new MainStorage();
    EmployeeStorage em = new EmployeeStorage();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    //Focus Effects
    @FXML
    private void focusUser(){
        userImg.setEffect(null);
    }
    @FXML
    private void focusMusic(){
        musicImg.setEffect(null);

    }
    @FXML
    private void focusEmp(){
        empImg.setEffect(null);

    }

    //Blur Effects
    @FXML
    private void blurUser(){
        BoxBlur bx = new BoxBlur();
        bx.setHeight(6);
        bx.setWidth(6);
        userImg.setEffect(bx);
        }
    @FXML
    private void blurMusic(){
        BoxBlur bx = new BoxBlur();
        bx.setHeight(6);
        bx.setWidth(6);
        musicImg.setEffect(bx);

    }


    @FXML
    private void blurEmp(){
        BoxBlur bx = new BoxBlur();
        bx.setHeight(6);
        bx.setWidth(6);
        empImg.setEffect(bx);

    }




    //switch scenes User/Emp
    @FXML
    public void handleUserButton(ActionEvent event) throws IOException {
        access.viewUserScene(event);
    }
    @FXML
    public void handleAddEmp(ActionEvent event) throws IOException {
        access.addEmployeeScene(event);
    }
    @FXML
    public void handleRmvEmp(ActionEvent event) throws IOException {
        access.removeEmployeeScene(event);
    }


    //Switch scenes Music
    @FXML
    public void handleview(ActionEvent event) throws IOException {
        access.viewMusic(event);
    }
    @FXML
    public void handleadd(ActionEvent event) throws IOException {
        access.addMusic(event);
    }
    @FXML
    public void handledelete(ActionEvent event) throws IOException {
        access.deleteMusic(event);
    }



    //Switch scenes log out
    @FXML
    public void handleLogOutButton(ActionEvent event) throws IOException {
        ms.logOut(event);
    }
}
