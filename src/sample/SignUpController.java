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
import sample.DatabaseConnection.UpdateDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {



    @FXML
    private TextField firstNameTextField;
    

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField confirmEmailTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button submitButton;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setOnMouseClicked((event -> {
            UpdateDatabase database=new UpdateDatabase();
            database.UpdateTableForUserCreation(emailTextField.getText(),passwordPasswordField.getText());

            ArrayList<String> userInfo=new ArrayList<String>();
            userInfo.add(firstNameTextField.getText());
            userInfo.add(lastNameTextField.getText());
            userInfo.add(emailTextField.getText());
            database.AddUserCreationData(userInfo);
        }));




    }

    @FXML public void handleBackButton(ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/login.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
