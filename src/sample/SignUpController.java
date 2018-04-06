package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DatabaseConnection.UpdateDatabase;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {

    //Variables
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField confirmEmailTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField confirmPasswordField;
    private static Pattern emailPat;
    private static Pattern passPat;
    private static Pattern namePat;

    //Objects
    SwitchScene sw = new SwitchScene();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML public void handleSubmitButton(ActionEvent event) {

        emailPat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        passPat  = Pattern.compile("^[a-z0-9_-]{3,15}");
        namePat  = Pattern.compile("^[a-zA-Z\\\\s]+");


        if     (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() ||
                emailTextField.getText().isEmpty() || confirmEmailTextField.getText().isEmpty() ||
                passwordPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()   ){
                Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
                dialog2.setTitle("Error!");                                                    //Title
                dialog2.setHeaderText("Empty Field!");                                         //Header
                dialog2.setContentText("Make sure to fill all the fields before submitting");  //Content
                dialog2.show();
        }

        if (!emailPat.matcher(emailTextField.getText()).matches()) {
            Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
            dialog2.setTitle("Error!");                                                    //Title
            dialog2.setHeaderText("Incorrect Format");                                     //Header
            dialog2.setContentText("Make sure email format respects xxx@xxx.xxx form");    //Content
            dialog2.show();
        }

        if (!emailTextField.getText().equals(confirmEmailTextField.getText())) {
            Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
            dialog2.setTitle("Error!");                                                    //Title
            dialog2.setHeaderText("Email Mismatch");                                       //Header
            dialog2.setContentText("Make sure that emails are matching");                  //Content
            dialog2.show();
        }

        if (!passPat.matcher(passwordPasswordField.getText()).matches()) {
            Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
            dialog2.setTitle("Error!");                                                    //Title
            dialog2.setHeaderText("Incorrect Format");                                     //Header
            dialog2.setContentText("Make sure your password is made of digits and characters only!");    //Content
            dialog2.show();
        }

        if (!passwordPasswordField.getText().equals(confirmPasswordField.getText())) {
            Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
            dialog2.setTitle("Error!");                                                    //Title
            dialog2.setHeaderText("Password Mismatch");                                       //Header
            dialog2.setContentText("Make sure that passwords are matching");                  //Content
            dialog2.show();
        }

        if     (!namePat.matcher(firstNameTextField.getText()).matches() ||
                !namePat.matcher(lastNameTextField.getText()).matches()) {
            Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
            dialog2.setTitle("Error!");                                                    //Title
            dialog2.setHeaderText("Incorrect Format");                                     //Header
            dialog2.setContentText("Make sure the first and last name are made of characters");    //Content
            dialog2.show();
        }

           else {

               UpdateDatabase database = new UpdateDatabase();
               database.UpdateTableForUserCreation(emailTextField.getText(), passwordPasswordField.getText());

               ArrayList<String> userInfo = new ArrayList<String>();
               userInfo.add(firstNameTextField.getText());
               userInfo.add(lastNameTextField.getText());
               userInfo.add(emailTextField.getText());
               database.AddUserCreationData(userInfo);
           }

    }

    @FXML public void handleBackButton(ActionEvent event) throws IOException {
        sw.logOut(event);
    }

}
