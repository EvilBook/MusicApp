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
    @FXML private TextArea albumTextArea;
    @FXML private TextArea songTextArea;
    @FXML private Button addAlbumArtistButton;
    @FXML private Button addSongArtistButton;
    @FXML private Button addSong;
    @FXML private Button addAlbum;
    @FXML private Button addAlbumGenreButton;
    @FXML private Button returnButton;
    @FXML private Button submitAlbumButton;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        AddAlbumToDatabase albumDatabase = new AddAlbumToDatabase();

        //Contains: albumName, date, albumPrice, label, vynlNumber
        albumDatabase.addAlbum("testname", "testdate", "testprice", "testlabel", "testvynlnum");
        //Contains: albumArtist
        albumDatabase.addAlbumArtist("testalbumartist");
        //Contains: genre
        albumDatabase.addGenre("testGenre");
        //Contains: songName, playTime
        albumDatabase.addSong("testSongName", "testPlaytime");
        //Contains: songArtist
        albumDatabase.addSongArtist("testsongArtist");
    }

    //Set the text from album textFields to the textArea
    @FXML
    void handleAddAlbum()
    {
        if(!(albumNameField.getText().isEmpty()) && !(albumArtistField.getText().isEmpty()) &&
                !(albumGenreField.getText().isEmpty()) && !(albumDateField.getText().isEmpty()))
        {
            //Set text to album textArea
            albumTextArea.setText("Album\n---------------\nName: " + albumNameField.getText() + "\nArtist: " +
                    albumArtistField.getText() + "\nGenre: " + albumGenreField.getText() + "\nRelease Date: " + albumDateField.getText());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }
    }

    //Handle Submit Album Button
    @FXML
    private void handleSubmitAlbum(){
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
    }

    //Save the Album in the database
    public void saveAlbum()
    {
        AddAlbumToDatabase albumDatabase = new AddAlbumToDatabase();

        //Contains: albumName, releaseDate, price, albumArtists, albumGenres,

        //Contains: songName, songArtists, songDuration

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
    }

}
