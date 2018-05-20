package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.MainStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserManagement implements Initializable {

    //Objects
    AdminStorage access = new AdminStorage();
    MainStorage backTo = new MainStorage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        //access.mainAdminScene(event);
        backTo.mainAdminScreen(event);
    }

    @FXML
    public void handleDeleteButton(ActionEvent event)throws IOException
    {
            Node node = (Node)event.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("removeUser.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}

