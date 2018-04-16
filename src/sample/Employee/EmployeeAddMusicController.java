package sample.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.SwitchScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeAddMusicController implements Initializable {

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
    SwitchScene sw = new SwitchScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleSubmitAlbum(ActionEvent event) {

    }

    public void handleReturnButton(ActionEvent event) throws IOException {
        sw.backToEmp(event);
    }

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



}
