package sample.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import sample.DatabaseConnection.AddAlbumToDatabase;
import java.net.URL;
import java.util.ResourceBundle;


public class AddMusic implements Initializable {

    //Variables
    @FXML private TextArea albumTextArea,songTextArea;
    @FXML private Button returnButton, submitAlbumButton;
    @FXML private TextField albumNameField, albumPriceField, albumDateField, albumLabelField, albumVynlField,
                            albumGenreField1, albumGenreField2, albumGenreField3, albumArtistField1,
                            albumArtistField2, albumArtistField3, songNameField1, songNameField2,
                            songNameField3, songNameField4, songNameField5, songNameField6, songNameField7,
                            songNameField8, songArtistField1, songArtistField2, songArtistField3, songArtistField4,
                            songArtistField5, songArtistField6, songArtistField7, songArtistField8,
                            songPlaytimeField1, songPlaytimeField2, songPlaytimeField3, songPlaytimeField4,
                            songPlaytimeField5, songPlaytimeField6, songPlaytimeField7, songPlaytimeField8;

    //Objects
    EmployeeStorage access = new EmployeeStorage();
    AddAlbumToDatabase albumDatabase = new AddAlbumToDatabase();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }




    //Set the text from album textFields to the textArea
    @FXML
    void handleAddAlbum()
    {
        if(!(albumNameField.getText().isEmpty()) && !(albumArtistField1.getText().isEmpty()) &&
                !(albumGenreField1.getText().isEmpty()) && !(albumDateField.getText().isEmpty()) &&
                !(albumLabelField.getText().isEmpty())&& !(albumVynlField.getText().isEmpty()))
        {
            //Set text to album textArea
            albumTextArea.setText("Album\n---------------\nName: " + albumNameField.getText() + "\nArtist: " +
                    albumArtistField1.getText() + "\nGenre: " + albumGenreField1.getText() + "\nRelease Date: " +
                    albumDateField.getText() + "\nLabel: " + albumLabelField.getText() + "\nVynl Number: " +
                    albumVynlField.getText());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }
    }

    //Set the text from album textFields to the textArea
    @FXML
    void handleAddSong () {
        if(!(songNameField1.getText().isEmpty()) && !(songArtistField1.getText().isEmpty()) && !(songPlaytimeField1.getText().isEmpty()))
        {
            //Set text to song textArea
            songTextArea.setText("Song\n---------------\nName: " + songNameField1.getText() + "\nArtist: " +
                    songArtistField1.getText() + "\nPlaytime: " + songPlaytimeField1.getText());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }
    }

    //Submit Album Button
    public void handleSubmitAlbum(ActionEvent event) {
        if(!(albumGenreField1.getText().isEmpty()) && !(albumArtistField1.getText().isEmpty()) &&
                !(albumNameField.getText().isEmpty()) && !(albumDateField.getText().isEmpty()))
        {
            saveAlbum();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }
    }

    //Save the Album in the database
    public void saveAlbum() {
        //Contains: albumName, date, albumPrice, label, vinylNumber
        albumDatabase.addAlbum(albumNameField.getText(), albumDateField.getText(), albumPriceField.getText(), albumLabelField.getText(), albumVynlField.getText());
        //Contains: albumArtist
        albumDatabase.addAlbumArtist(albumArtistField1.getText());
        //Contains: genre
        albumDatabase.addGenre(albumGenreField1.getText());
        //Contains: songName, playTime
        albumDatabase.addSong(songNameField1.getText(), songPlaytimeField1.getText());
        //Contains: songArtist
        albumDatabase.addSongArtist(songArtistField1.getText());
    }

    public void handleCloseButton(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
