package sample;

import javafx.scene.control.Label;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
  public void AlbumNameError(String AlbumName,Label label){
        if (AlbumName.matches(".*\\d+.*")){
            label.setText("Invalid Input");
            }




    }
    public void ReleaseDateError(String ReleaseDateTextfield,Label errorMessage){
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        format.setLenient(false);

        try {
            format.parse(ReleaseDateTextfield);

        }catch (ParseException e){
            errorMessage.setText("invalid Input");

        }
    }

      public void vynlNumberError(String VynlNumberTextfield,Label errorMessage){
        if (!VynlNumberTextfield.equals(1) && !VynlNumberTextfield.equals(2)){
            errorMessage.setText("Invalid Input");

        }

        }

        public void LabelNameErrorMessage(String LabelTextField,Label errorMessage){
            if (LabelTextField.matches(".*\\d+.*")){
                errorMessage.setText("Invalid Input");
            }


        }


        public void PriceerrorMessage(String priceTextfield,Label errorMessage){
        if (priceTextfield.equals(300)){
            errorMessage.setText("Invalid input");

        }

        }
        public void GenreErrorMessage(String GenreNameTextfield,Label errorMessage){
            if (GenreNameTextfield.matches(".*\\d+.*")){
                errorMessage.setText("Invalid Input");
            }
        }

        public void SongNameErrorMessage(String songNameTextfield,Label errorMessage) {
            if (songNameTextfield.contains(",.!?@")) {
                errorMessage.setText("invalid Input");
            }

        }
        public void ArtistNameerrorMessage(String ArtistNameTextfield,Label errorMessage){
        if (ArtistNameTextfield.matches(".*\\d+.*")){
            errorMessage.setText("Invalid Input");
        }



        }

        public void PlayTimeErrorMessage(String PlayTimeTextField,Label errorMessage){
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            format.setLenient(false);

            try {
                format.parse(PlayTimeTextField);

            }catch (ParseException e){
                errorMessage.setText("invalid Input");

            }

        }





}

