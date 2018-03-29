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

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{


    @FXML private TextField userNameTextField;
    @FXML private PasswordField PasswordTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    @FXML
    public void handleLoginButton(ActionEvent event){

        Exceptions myapp = new Exceptions();
        try {
            myapp.UsernameException(userNameTextField.getText());
            myapp.MaximumPasswordLength(PasswordTextField.getText());
            PasswordTextField.clear();
            userNameTextField.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @FXML
    public void handleCreateButton(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
