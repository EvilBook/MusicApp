package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseConnection.ThisIsForConnecting;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleLoginButton(ActionEvent event){

    }

    @FXML
    public void handleCreateButton(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

}
