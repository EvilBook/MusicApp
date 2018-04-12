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
    @FXML private TextArea albumTextArea,songTextArea;
    @FXML private Button returnButton, submitAlbumButton;

    @FXML private TextField albumNameField, albumPriceField, albumDateField, albumLabelField, albumVynlField;
    @FXML private TextField albumGenreField1, albumGenreField2, albumGenreField3;
    @FXML private TextField albumArtistField1, albumArtistField2, albumArtistField3;

    @FXML private TextField songNameField1, songNameField2, songNameField3, songNameField4, songNameField5,
            songNameField6, songNameField7, songNameField8;
    @FXML private TextField songArtistField1, songArtistField2, songArtistField3, songArtistField4, songArtistField5,
            songArtistField6, songArtistField7, songArtistField8;
    @FXML private TextField songPlaytimeField1, songPlaytimeField2, songPlaytimeField3, songPlaytimeField4,
            songPlaytimeField5, songPlaytimeField6, songPlaytimeField7, songPlaytimeField8;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

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

    //Set the text from song textFields to the textArea
    @FXML
    void handleAddSong()
    {
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

    //Handle Submit Album Button
    @FXML
    private void handleSubmitAlbum(){
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
    public void saveAlbum()
    {
        AddAlbumToDatabase albumDatabase = new AddAlbumToDatabase();

        //Contains: albumName, date, albumPrice, label, vynlNumber
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
