package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.DatabaseConnection.AddEmployeeToDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddEmployee implements Initializable {

    //Variables
    @FXML private TextField firstTextField;
    @FXML private TextField lastTextField;
    @FXML private TextField birthTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField passwordTextField;
    @FXML private Label firstLabel, lastLabel, emailLabel, passLabel, addressLabel, dateLabel, phoneLabel;

    private static Pattern emailPat;
    private static Pattern passPat;
    private static Pattern namePat;
    private static Pattern datePat;
    private static Pattern phonePat;

    //Objects
    AdminStorage access = new AdminStorage();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        firstLabel.setOpacity(0f);
        lastLabel.setOpacity(0f);
        emailLabel.setOpacity(0f);
        passLabel.setOpacity(0f);
        emailLabel.setOpacity(0f);
        addressLabel.setOpacity(0f);
        dateLabel.setOpacity(0f);
        phoneLabel.setOpacity(0f);

    }

    @FXML
    public void handleAddButton(ActionEvent event) throws InterruptedException
    {
        firstLabel.setOpacity(0f);
        lastLabel.setOpacity(0f);
        emailLabel.setOpacity(0f);
        passLabel.setOpacity(0f);
        emailLabel.setOpacity(0f);
        addressLabel.setOpacity(0f);
        dateLabel.setOpacity(0f);
        phoneLabel.setOpacity(0f);

        emailPat = Pattern.compile("^[A-Z0-9._%+-]+@mass.com", Pattern.CASE_INSENSITIVE);
        passPat = Pattern.compile("[a-z0-9_-]{3,15}");
        namePat = Pattern.compile("[a-zA-Z\\s]+");
        datePat = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        phonePat = Pattern.compile("[0-9]+");

        Boolean a=false,b=false,c=false,d=false,e=false,f=false,g=false;

        //Check for correct formats
        if(namePat.matcher(firstTextField.getText()).matches())
        {
            a = true;
        } else
        {
            firstLabel.setText("Name contains digit");
            firstLabel.setOpacity(1);
        }

        if(namePat.matcher(lastTextField.getText()).matches())
        {
            b = true;
        }else{
            lastLabel.setText("Name contains digit");
            lastLabel.setOpacity(1);
        }

        if(emailPat.matcher(emailTextField.getText()).matches()) {

            c = true;
        }else{
            emailLabel.setText("Wrong email format");
            emailLabel.setOpacity(1);
        }

        if(passPat.matcher(passwordTextField.getText()).matches())
        {
            d = true;
        }else{
            passLabel.setText("Wrong password format");
            passLabel.setOpacity(1);

        }
        if(datePat.matcher(birthTextField.getText()).matches())
        {
            e = true;
        }else{
            dateLabel.setText("Wrong date format");
            dateLabel.setOpacity(1);

        }
        if(phonePat.matcher(phoneTextField.getText()).matches())
        {
            f = true;
        }else{
            phoneLabel.setText("Wrong phone format");
            phoneLabel.setOpacity(1);

        }


        //Check if fields are filled in
        if(!firstTextField.getText().isEmpty() && !lastTextField.getText().isEmpty() &&
                !emailTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() &&
                !birthTextField.getText().isEmpty() && !phoneTextField.getText().isEmpty() &&
                !addressTextField.getText().isEmpty())
        {
            g = true;
        }
        else
        {
            if(firstTextField.getText().isEmpty() )
            {
                firstLabel.setOpacity(1);
                firstLabel.setText("Please fill in name");
            }
            if(lastTextField.getText().isEmpty() )
            {
                lastLabel.setOpacity(1);
                lastLabel.setText("Please fill in name");
            }
            if(emailTextField.getText().isEmpty())
            {
                emailLabel.setOpacity(1);
                emailLabel.setText("Please fill in email");
            }
            if(passwordTextField.getText().isEmpty())
            {
                passLabel.setOpacity(1);
                passLabel.setText("Please fill in password");
            }
            if(birthTextField.getText().isEmpty())
            {
                dateLabel.setOpacity(1);
                dateLabel.setText("Please fill in date");
            }
            if(phoneTextField.getText().isEmpty())
            {
                phoneLabel.setOpacity(1);
                phoneLabel.setText("Please fill in phone");
            }
            if(addressTextField.getText().isEmpty())
            {
                addressLabel.setOpacity(1);
                addressLabel.setText("Please fill in address");

            }
        }




        //If all fields are correctly filled in, add to db
        if(a==true && b==true && c==true && d==true && e==true && f==true && g==true)
        {
            System.out.println("else works");
            AddEmployeeToDatabase empDatabase = new AddEmployeeToDatabase();

            ArrayList<String> empInfo = new ArrayList<String>();
            empInfo.add(firstTextField.getText());      //first name
            empInfo.add(lastTextField.getText());       //last name
            empInfo.add(birthTextField.getText());      //birth date
            empInfo.add(phoneTextField.getText());      //phone number
            empInfo.add(emailTextField.getText());      //email address
            empInfo.add(addressTextField.getText());    //home address
            empInfo.add(passwordTextField.getText());   //password
            empDatabase.addEmployee(empInfo);

            a=false; b=false; c=false; d=false;
            System.out.println("Employee Added!");
        }
    }


    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        access.viewAdminScreen(event);
    }

}