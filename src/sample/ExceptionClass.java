package sample;

import javafx.scene.control.Label;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
  public boolean AlbumNameError(String AlbumName,Label label)throws IOException{
      if (AlbumName.matches(".*\\d+.*")){
          label.setText("Invalid Input");
          return false;



      }else return true;






    }
    public boolean ReleaseDateError(String ReleaseDateTextfield,Label errorMessage)throws IOException{
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        format.setLenient(false);

        try {
            format.parse(ReleaseDateTextfield);
            return true;

        }catch (ParseException e){
            errorMessage.setText("invalid Input");
            return false;

        }
    }

      public boolean vynlNumberError(String VynlNumberTextfield,Label errorMessage)throws IOException{
        if (!VynlNumberTextfield.equals(1) && !VynlNumberTextfield.equals(2)){
            errorMessage.setText("Invalid Input");
            return false;

        }return true;

        }

        public boolean LabelNameErrorMessage(String LabelTextField,Label errorMessage)throws IOException{
            if (LabelTextField.matches(".*\\d+.*")){
                errorMessage.setText("Invalid Input");
                return false;
            }else return true;


        }


        public boolean PriceerrorMessage(String priceTextfield,Label errorMessage)throws IOException{
       try {
           Integer.parseInt(priceTextfield);
           return true;

       }catch (NumberFormatException e){
           errorMessage.setText("Invalid input");

           return false;
       }

        }
        public boolean GenreErrorMessage(String GenreNameTextfield,Label errorMessage)throws IOException{
            if (GenreNameTextfield.matches(".*\\d+.*")){
                errorMessage.setText("Invalid Input");
                return false;
            }else return true;
        }

        public boolean SongNameErrorMessage(String songNameTextfield,Label errorMessage)throws IOException {
            if (songNameTextfield.contains(",.!?@")) {
                errorMessage.setText("invalid Input");
                return false;

            }else return true;

        }
        public boolean ArtistNameerrorMessage(String ArtistNameTextfield,Label errorMessage)throws IOException{
        if (ArtistNameTextfield.matches(".*\\d+.*")){
            errorMessage.setText("Invalid Input");
            return false;
        }else return true;



        }

     public boolean playTimeErrorMessage(String playTimeTextfield,Label errorMessage)throws IOException{
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("00:00");
            LocalTime.parse(playTimeTextfield);

            return true;
        }catch (DateTimeParseException e){
            errorMessage.setText("Invalid input");

            return true;
        }

        }





}

