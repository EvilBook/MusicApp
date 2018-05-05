package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

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
  public boolean AlbumNameError(String AlbumName)throws IOException{
      if (AlbumName.matches(".*\\d+.*")){
          Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input!AlbumName",ButtonType.OK);
          dialog.show();
          return false;



      }else return true;






    }
    public boolean ReleaseDateError(String ReleaseDateTextfield)throws IOException{
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        format.setLenient(false);

        try {
            format.parse(ReleaseDateTextfield);
            return true;

        }catch (ParseException e){
            Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input!ReleaseDate",ButtonType.OK);
            dialog.show();

            return false;

        }
    }

      public boolean vynlNumberError(String VynlNumberTextfield)throws IOException{
        int foo = Integer.parseInt(VynlNumberTextfield);
        if (foo > 2){
            Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input!VynlNumber",ButtonType.OK);
            dialog.show();
            return false;

        }return true;

        }

        public boolean LabelNameErrorMessage(String LabelTextField)throws IOException{
            if (LabelTextField.matches(".*\\d+.*")){
                Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input!LabelName",ButtonType.OK);
                dialog.show();
                return false;
            }else return true;


        }


        public boolean PriceerrorMessage(String priceTextfield)throws IOException{
       try {
           Integer.parseInt(priceTextfield);

           return true;

       }catch (NumberFormatException e){
           Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input! Price",ButtonType.OK);
           dialog.show();

           return false;
       }

        }
        public boolean GenreErrorMessage(String GenreNameTextfield)throws IOException{
            if (GenreNameTextfield.matches(".*\\d+.*")){
                Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input!GenreName",ButtonType.OK);
                dialog.show();
                return false;
            }else return true;
        }

        public boolean SongNameErrorMessage(String songNameTextfield)throws IOException {
            Pattern namePat  = Pattern.compile("[a-zA-Z\\s]+");
            if (songNameTextfield.matches(String.valueOf(namePat))) {
                Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input!AlbumName",ButtonType.OK);
                dialog.show();
                return false;

            }else return true;

        }
        public boolean ArtistNameerrorMessage(String ArtistNameTextfield)throws IOException{
        if (ArtistNameTextfield.matches(".*\\d+.*")){
            Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input!ArtistName",ButtonType.OK);
            dialog.show();;
            return false;
        }else return true;



        }

     public boolean playTimeErrorMessage(String playTimeTextfield)throws IOException{
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("00:00");
            LocalTime.parse(playTimeTextfield);


            return true;
        }catch (DateTimeParseException e){
            Alert dialog = new Alert(Alert.AlertType.ERROR,"Invalid Input!PlayTime",ButtonType.OK);
            dialog.show();

            return false;
        }

        }





}

