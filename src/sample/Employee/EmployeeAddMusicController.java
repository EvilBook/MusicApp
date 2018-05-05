package sample.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DatabaseConnection.AddAlbumToDatabase;
import sample.ExceptionClass;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeAddMusicController implements Initializable {

    //Variables
    @FXML private TextField albumGenreField;
    @FXML private TextField albumArtistField;
    @FXML private TextField albumNameField;
    @FXML private TextField albumPriceField;
    @FXML private TextField songNameField;
    @FXML private TextField songArtistField;
    @FXML private TextField albumDateField;
    @FXML private TextField songPlaytimeField;
    @FXML private TextField albumLabelField;
    @FXML private TextField albumVynlField;
    @FXML private TextArea albumTextArea;
    @FXML private TextArea songTextArea;

    @FXML
    private Label AlbumNameLabelErrorMessage;

    @FXML
    private Label ReleaseDateErrorMessage;

    @FXML
    private Label VynlNumberErrorMessage;

    @FXML
    private Label LabelNameErrorMessage;

    @FXML
    private Label PriceErrorMessage;

    @FXML
    private Label AlbumArtistErrorMessage;

    @FXML
    private Label GenreErrorMessage;

    @FXML
    private Label SongNameErrorMessage;

    @FXML
    private Label ArtistNameErrorMessage;

    @FXML
    private Label PlayTimeErrorMessage;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    //Set the text from album textFields to the textArea
    @FXML
    void handleAddAlbum()
    {
        if(!(albumNameField.getText().isEmpty()) && !(albumArtistField.getText().isEmpty()) &&
                !(albumGenreField.getText().isEmpty()) && !(albumDateField.getText().isEmpty()) &&
                !(albumLabelField.getText().isEmpty())&& !(albumVynlField.getText().isEmpty()))
        {
            //Set text to album textArea
            albumTextArea.setText("Album\n---------------\nName: " + albumNameField.getText() + "\nArtist: " +
                    albumArtistField.getText() + "\nGenre: " + albumGenreField.getText() + "\nRelease Date: " +
                    albumDateField.getText() + "\nLabel: " + albumLabelField.getText() + "\nVynl Number: " +
                    albumVynlField.getText());
        }
        else
        {




           /* Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();*/
        }
    }

    //Set the text from song textFields to the textArea
    @FXML
    void handleAddSong()
    {
        if(!(songNameField.getText().isEmpty()) && !(songArtistField.getText().isEmpty()) && !(songPlaytimeField.getText().isEmpty()))
        {
            //Set text to song textArea
            songTextArea.setText("Song\n---------------\nName: " + songNameField.getText() + "\nArtist: " +
                    songArtistField.getText() + "\nPlaytime: " + songPlaytimeField.getText());
        }
        else
        {
           /* Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();*/



        }
    }

    //Handle Submit Album Button
    @FXML
    private void handleSubmitAlbum() throws IOException {

        ExceptionClass myapp = new ExceptionClass();

        if (myapp.GenreErrorMessage(albumGenreField.getText())==true && myapp.playTimeErrorMessage(songPlaytimeField.getText())==true
                && myapp.ArtistNameerrorMessage(albumArtistField.getText())==true && myapp.vynlNumberError(albumVynlField.getText())&&
                myapp.SongNameErrorMessage(songNameField.getText())==true && myapp.LabelNameErrorMessage(albumLabelField.getText())==true
                && myapp.ReleaseDateError(albumDateField.getText())==true && myapp.PriceerrorMessage(albumPriceField.getText())==true &&
                myapp.ArtistNameerrorMessage(songArtistField.getText())==true && myapp.AlbumNameError(albumNameField.getText())==true
                )





        if(!(albumGenreField.getText().isEmpty()) && !(albumArtistField.getText().isEmpty()) &&
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

        albumArtistField.clear();
        albumGenreField.clear();
        albumNameField.clear();
        albumDateField.clear();
        songNameField.clear();
        songArtistField.clear();
        songPlaytimeField.clear();
        albumPriceField.clear();
        albumDateField.clear();
        albumLabelField.clear();
        albumVynlField.clear();
    }

    //Save the Album in the database
    public void saveAlbum()
    {
        AddAlbumToDatabase albumDatabase = new AddAlbumToDatabase();

        //Contains: albumName, date, albumPrice, label, vynlNumber
        albumDatabase.addAlbum(albumNameField.getText(), albumDateField.getText(), albumPriceField.getText(), albumLabelField.getText(), albumVynlField.getText());
        //Contains: albumArtist
        albumDatabase.addAlbumArtist(albumArtistField.getText());
        //Contains: genre
        albumDatabase.addGenre(albumGenreField.getText());
        //Contains: songName, playTime
        albumDatabase.addSong(songNameField.getText(), songPlaytimeField.getText());
        //Contains: songArtist
        albumDatabase.addSongArtist(songArtistField.getText());
        
    }

    //Switch Scenes
    @FXML
    private void handleSwitchScenes(ActionEvent event) throws IOException
    {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../login.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //
    }

}
