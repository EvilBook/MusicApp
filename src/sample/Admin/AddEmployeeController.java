package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseConnection.UpdateDatabase;
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddEmployeeController implements Initializable {

    //Variables
    @FXML private TextField firstTextField;
    @FXML private TextField lastTextField;
    @FXML private TextField dateTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField passwordTextField;
    public String userEmail;

    private static Pattern emailPat;
    private static Pattern passPat;
    private static Pattern namePat;

    //Objects
    SwitchScene sw = new SwitchScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        sw.empBack(event);
    }

    @FXML
    public void handleAddButton(ActionEvent event) {
        emailPat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        passPat = Pattern.compile("[a-z0-9_-]{3,15}");
        namePat = Pattern.compile("[a-zA-Z\\s]+");


        //Here it's gonna check for empty fields before all
        if (firstTextField.getText().isEmpty()) {

        }

        if (lastTextField.getText().isEmpty()) {

        }

        if (emailTextField.getText().isEmpty()) {

        }

        if (passwordTextField.getText().isEmpty()) {

        }

        //Here it's gonna check for the rest of exceptions
        else {

            if (!namePat.matcher(firstTextField.getText()).matches()) {

            }

            if (!namePat.matcher(lastTextField.getText()).matches()) {

            }

            if (!emailPat.matcher(emailTextField.getText()).matches()) {

            }

            if (!passPat.matcher(passwordTextField.getText()).matches()) {

            }
            else {


                UpdateDatabase database = new UpdateDatabase();
                database.UpdateTableForUserCreation(emailTextField.getText(), passwordTextField.getText());

                ArrayList<String> userInfo = new ArrayList<String>();

                userInfo.add(firstTextField.getText());
                userInfo.add(lastTextField.getText());
                userInfo.add(dateTextField.getText());
                userInfo.add(emailTextField.getText());
                userInfo.add(phoneTextField.getText());
                userInfo.add(addressTextField.getText());
                userInfo.add(passwordTextField.getText());
                database.AddUserCreationData(userInfo);

                //Clear the fields after the signing up
                /*firstNameTextField.clear();
                lastNameTextField.clear();
                emailTextField.clear();
                confirmEmailTextField.clear();
                passwordPasswordField.clear();
                confirmPasswordField.clear();*/

            }
        }
    }

}
