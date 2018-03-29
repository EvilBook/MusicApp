package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.DatabaseConnection.ThisIsForConnecting;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    Button LoginButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("One");

        LoginButton.setOnMouseClicked((event -> {
            System.out.println("www");
        }));

    }
}
