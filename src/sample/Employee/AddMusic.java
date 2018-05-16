package sample.Employee;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
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

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class AddMusic implements Initializable
{
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

    //Handle Submit Album Button
    @FXML
    private void handleSubmitAlbum()
    {
        checkFormat();
    }

    public String generateRandom()
    {
        Random r = new Random();

        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        String str = "";


        for(int i=0; i < 16; i++)
        {
            Character ch = alphabet.charAt(r.nextInt(N));
            str = str + ch;
        }

        str = str + ".pdf";
        return str;
    }


    public void savePDF(String albumName, String date, String albumPrice, String label, String vynl)
    {
        Document doc = new Document();

        try
        {
            String file = generateRandom();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/sample/Pdf/" + file));
            doc.open();
            doc.addTitle(albumName);
            doc.add(new Paragraph("------ALBUM ADDED TO DATABASE------\n\n" +
                    "Album Name: " + albumName + "\nPrice: " + albumPrice + "\nLabel: " + label + "\nVinyl Number: " + vynl));
            doc.close();
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }

    }


    //Check if text fields are filled in correctly
    public void checkFormat()
    {
        //Check if album text fields are filled in
        if(!(albumNameField.getText().isEmpty())&& !(albumArtistField1.getText().isEmpty()) &&
                !(albumGenreField1.getText().isEmpty()) &&
                !(albumLabelField.getText().isEmpty()) &&!(albumVynlField.getText().isEmpty()) &&
                !(songNameField1.getText().isEmpty())  && !(songArtistField1.getText().isEmpty()) &&
                !(songPlaytimeField1.getText().isEmpty()))
        {

            //Check if format is correctly filled in
            if((albumDateField.getText().matches("\\d{2}-\\d{2}-\\d{4}")) &&
                    (songPlaytimeField1.getText().matches("\\d{2}:\\d{2}:\\d{2}") ||
                            songPlaytimeField1.getText().matches("\\d{2}:\\d{2}")) &&
                    (albumVynlField.getText().matches("\\d{2}")) &&
                    (albumPriceField.getText().matches("\\d")||
                    albumPriceField.getText().matches("\\d{2}") ||
                    albumPriceField.getText().matches("\\d{3}")))
            {

                //Ask for confirmation
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to submit the album?", ButtonType.APPLY, ButtonType.CANCEL);
                alert.setTitle("CONFIRM");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.APPLY)
                {
                    //Save the data
                    saveAlbum();
                }

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields with the correct format.", ButtonType.OK);
                alert.setHeaderText("ERROR");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields.", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }
    }


    //Save the Album in the database
    public void saveAlbum()
    {
        AddAlbumToDatabase albumDatabase = new AddAlbumToDatabase();

        //Contains: albumName, date, albumPrice, label, VynlNumber
        albumDatabase.addAlbum(albumNameField.getText(), albumDateField.getText(), albumPriceField.getText(),
                albumLabelField.getText(), albumVynlField.getText());

        //Save Data in PDF file
        savePDF(albumNameField.getText(), albumDateField.getText(), albumPriceField.getText(),
                albumLabelField.getText(), albumVynlField.getText());


        //Contains: albumArtist
        albumDatabase.addAlbumArtist(albumArtistField1.getText());


        if(!albumArtistField2.getText().isEmpty()){
            albumDatabase.addAlbumArtist(albumArtistField2.getText());
        }
        if(!albumArtistField3.getText().isEmpty()){
            albumDatabase.addAlbumArtist(albumArtistField3.getText());
        }


        //Contains: genre
        albumDatabase.addGenre(albumGenreField1.getText());

        if(!albumGenreField2.getText().isEmpty())
        {
            albumDatabase.addGenre(albumGenreField2.getText());
        }
        if(!albumGenreField3.getText().isEmpty())
        {
            albumDatabase.addGenre(albumGenreField3.getText());
        }


        //Contains: songName, playTime, songArtist
        albumDatabase.addSong(songNameField1.getText(), songPlaytimeField1.getText());
        albumDatabase.addSongArtist(songArtistField1.getText());

        if(!(songNameField2.getText().isEmpty()) && !(songArtistField2.getText().isEmpty()) && !(songArtistField2.getText().isEmpty()) &&
                !(songPlaytimeField2.getText().isEmpty()) && (songPlaytimeField2.getText().matches("\\d{2}:\\d{2}")))
        {
            albumDatabase.addSong(songNameField2.getText(), songPlaytimeField2.getText());
            albumDatabase.addSongArtist(songArtistField2.getText());
        }

        if(!songNameField3.getText().isEmpty() && !songArtistField3.getText().isEmpty() && !songArtistField3.getText().isEmpty() &&
                !songPlaytimeField3.getText().isEmpty() && songPlaytimeField3.getText().matches("\\d{2}:\\d{2}"))
        {
            albumDatabase.addSong(songNameField3.getText(), songPlaytimeField3.getText());
            albumDatabase.addSongArtist(songArtistField3.getText());
        }

        if(!songNameField4.getText().isEmpty() && !songArtistField4.getText().isEmpty() && !songArtistField4.getText().isEmpty() &&
                !songPlaytimeField4.getText().isEmpty() && songPlaytimeField4.getText().matches("\\d{2}:\\d{2}"))
        {
            albumDatabase.addSong(songNameField4.getText(), songPlaytimeField4.getText());
            albumDatabase.addSongArtist(songArtistField4.getText());
        }

        if(!songNameField5.getText().isEmpty() && !songArtistField5.getText().isEmpty() && !songArtistField5.getText().isEmpty() &&
                !songPlaytimeField5.getText().isEmpty() && songPlaytimeField5.getText().matches("\\d{2}:\\d{2}"))
        {
            albumDatabase.addSong(songNameField5.getText(), songPlaytimeField5.getText());
            albumDatabase.addSongArtist(songArtistField5.getText());
        }

        if(!songNameField6.getText().isEmpty() && !songArtistField6.getText().isEmpty() && !songArtistField6.getText().isEmpty() &&
                !songPlaytimeField6.getText().isEmpty() && songPlaytimeField6.getText().matches("\\d{2}:\\d{2}"))
        {
            albumDatabase.addSong(songNameField6.getText(), songPlaytimeField6.getText());
            albumDatabase.addSongArtist(songArtistField6.getText());
        }

        if(!songNameField7.getText().isEmpty() && !songArtistField7.getText().isEmpty() && !songArtistField7.getText().isEmpty() &&
                !songPlaytimeField7.getText().isEmpty() && songPlaytimeField7.getText().matches("\\d{2}:\\d{2}"))
        {
            albumDatabase.addSong(songNameField7.getText(), songPlaytimeField7.getText());
            albumDatabase.addSongArtist(songArtistField7.getText());
        }

        if(!songNameField8.getText().isEmpty() && !songArtistField8.getText().isEmpty() && !songArtistField8.getText().isEmpty() &&
                !songPlaytimeField8.getText().isEmpty() && songPlaytimeField8.getText().matches("\\d{2}:\\d{2}"))
        {
            albumDatabase.addSong(songNameField8.getText(), songPlaytimeField8.getText());
            albumDatabase.addSongArtist(songArtistField8.getText());
        }


        //Reset the text fields and show message
        resetTextFields();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Album added to the database! " +
                "A PDF File is created with the album information", ButtonType.OK);
        alert.setHeaderText("COMPLETE");
        alert.showAndWait();
    }


    //Handle button press Clear
    @FXML
    public void handleClear()
    {
        resetTextFields();
    }


    //Reset all the text fields
    public void resetTextFields()
    {
        albumNameField.setText(""); albumLabelField.setText(""); albumDateField.setText(""); albumVynlField.setText("");albumPriceField.setText("");
        albumArtistField1.setText(""); albumArtistField2.setText(""); albumArtistField3.setText("");
        albumGenreField1.setText(""); albumGenreField2.setText(""); albumGenreField3.setText("");
        songNameField1.setText(""); songNameField2.setText(""); songNameField3.setText(""); songNameField4.setText("");
        songNameField5.setText(""); songNameField6.setText(""); songNameField7.setText(""); songNameField8.setText("");
        songArtistField1.setText(""); songArtistField2.setText(""); songArtistField3.setText(""); songArtistField4.setText("");
        songArtistField5.setText(""); songArtistField6.setText(""); songArtistField7.setText(""); songArtistField8.setText("");
        songPlaytimeField1.setText(""); songPlaytimeField2.setText(""); songPlaytimeField3.setText(""); songPlaytimeField4.setText("");
        songPlaytimeField5.setText(""); songPlaytimeField6.setText(""); songPlaytimeField7.setText(""); songPlaytimeField8.setText("");
    }

    //Close Scenes
    @FXML
    private void handleSwitchScenes(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }
}
