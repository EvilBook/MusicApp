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

public class ViewEmployeeController implements Initializable {

    //Objects
    SwitchScene sw = new SwitchScene();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleBackButton(ActionEvent event) throws IOException {
        sw.adminBack(event);
    }

    public void handleDeleteButton(ActionEvent event) {

    }

    public void handleUpdateButton(ActionEvent event) {

    }

    @FXML
    public void handleAddButton(ActionEvent event) throws IOException {
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
