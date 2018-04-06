package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;

public class ExceptionClass {





    public void firstNameException(String firstNameTextfield, Label label1) throws IOException {


            label1.setText("Invalid input");
            label1.setOpacity(1);


    }

    public void lastNameException(String lastNameTextfield, Label label1) throws IOException {


            label1.setText("Invalid input");
            label1.setOpacity(1);


    }

    public void EmailException(String EmailTextfield, Label label) {

            label.setText("Please enter a valid email");
            label.setOpacity(1);



    }

    public void EmailComfirmationException(String emailConfirmationTextfield, Label label, String EmailTextfield) {

            label.setText("email doesnt match");
            label.setOpacity(1);


    }

    public void PassException(String passwordTextfield, Label label) {

            label.setText("Password too long");
            label.setOpacity(1);




    }


    public void PasswordComfirmation(String passwordComfirmationField, String passwordTextfield, Label label) {


            label.setText("Password dont match try again");
            label.setOpacity(1);




    }
}