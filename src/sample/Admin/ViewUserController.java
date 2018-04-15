package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewUserController implements Initializable {

    //Objects
    SwitchScene sw = new SwitchScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        sw.adminBack(event);
    }

    @FXML
    public void handleDeleteButton(ActionEvent event) {

    }

}
