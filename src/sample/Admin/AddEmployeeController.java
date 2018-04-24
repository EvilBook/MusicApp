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
import sample.DatabaseConnection.AddEmployeeToDatabase;
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
    @FXML private TextField birthTextField;
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
    public void handleAddButton(ActionEvent event) throws InterruptedException {

        emailPat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        passPat = Pattern.compile("[a-z0-9_-]{3,15}");
        namePat = Pattern.compile("[a-zA-Z\\s]+");


        //Here it's gonna check for empty fields before all
        if (firstTextField.getText().isEmpty()) {

            firstTextField.setText("First name required");
            wait();

        }

        if (lastTextField.getText().isEmpty()) {
            lastTextField.setText("Last name required");
            wait();
        }

        if (emailTextField.getText().isEmpty()) {
            emailTextField.setText("Email required");
            wait();
        }

        if (passwordTextField.getText().isEmpty()) {
            passwordTextField.setText("Password required");
            wait();

        }

        //Here it's gonna check for the rest of exceptions
        else {

            if (!namePat.matcher(firstTextField.getText()).matches()) {
                firstTextField.setText("First name contains digits");
                wait();
            }

            if (!namePat.matcher(lastTextField.getText()).matches()) {
                lastTextField.setText("Last name contains digits");
                wait();
            }

            if (!emailPat.matcher(emailTextField.getText()).matches()) {
                emailTextField.setText("Incorrect email format");
                wait();
            }

            if (!passPat.matcher(passwordTextField.getText()).matches()) {
                passwordTextField.setText("Incorrect password format");
                wait();
            }

            else {


                AddEmployeeToDatabase empDatabase = new AddEmployeeToDatabase();
                empDatabase.UpdateTableForEmpCreation(emailTextField.getText(), passwordTextField.getText());

                ArrayList<String> empInfo = new ArrayList<String>();
                empInfo.add(firstTextField.getText());
                empInfo.add(lastTextField.getText());
                empInfo.add(emailTextField.getText());
                empInfo.add(passwordTextField.getText());
                empInfo.add(birthTextField.getText());
                empInfo.add(addressTextField.getText());
                empInfo.add(phoneTextField.getText());
                empDatabase.addEmployee(empInfo);
            }
    }
    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        sw.adminBack(event);
    }

}
