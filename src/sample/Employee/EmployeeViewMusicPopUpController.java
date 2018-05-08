package sample.Employee;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DatabaseConnection.DbconnectionMusic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EmployeeViewMusicPopUpController implements Initializable
{
    //Variables
    @FXML private TableColumn<Song, String> songidColumn, songnameColumn, songtimeColumn, songartistColumn;
    @FXML private Label albumLabel, artistLabel, genreLabel, vynlLabel, labelLabel, priceLabel, dateLabel;
    @FXML private TableView<Song> table;
    private int idAlbum;
    DbconnectionMusic dbc = new DbconnectionMusic();
    private ObservableList<Song> data;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Get the id of the album through the EmployeeDataStorage singleton class
        idAlbum = EmployeeDataStorage.getInstance().getMessage();
        loadData(idAlbum);
    }


    //Load the data in the table
    public void loadData(int idAlbum)
    {
        Connection connection = dbc.connection;
        data = FXCollections.observableArrayList();

        try
        {
            //Execute query to display song information in the table
            ResultSet rs = connection.createStatement().executeQuery(
                    "SELECT idSong, songName, songArtist, playtime FROM song, album,songartist WHERE (idSong = song_idSong) && " +
                            "(album_idAlbum =" + idAlbum +"&& idAlbum = "+idAlbum+");");

            //Add the songs to the table
            while (rs.next())
            {
                data.add(new Song(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

                // set cell value factory to tableView
                songidColumn.setCellValueFactory(new PropertyValueFactory<>("idSong"));
                songnameColumn.setCellValueFactory(new PropertyValueFactory<>("songName"));
                songartistColumn.setCellValueFactory(new PropertyValueFactory<>("songArtist"));
                songtimeColumn.setCellValueFactory(new PropertyValueFactory<>("playtime"));

                table.setItems(null);
                table.setItems(data);
            }


            //Execute query to display album information in the labels
            ResultSet rs2 = connection.createStatement().executeQuery(
                    "select distinct idalbum, albumname, date, price, label, vynlNumber, albumartist from album,albumartist where " +
                            "idAlbum = "+idAlbum+" && album_idAlbum = "+idAlbum+" ;");
            ResultSet rs3 = connection.createStatement().executeQuery(
                    "select distinct genre from album,genre where idAlbum = "+idAlbum+" && album_idAlbum = "+idAlbum+" ;");

            //Set the data to the correct labels
            //Reset str, used for displaying multiple artists
            String str = "";
            while(rs2.next())
            {
                albumLabel.setText(rs2.getString(2));
                dateLabel.setText(rs2.getString(3));
                priceLabel.setText(rs2.getString(4) + " euros");
                labelLabel.setText(rs2.getString(5));
                vynlLabel.setText(rs2.getString(6));
                artistLabel.setText(rs2.getString(7) + " " + str);
                str = artistLabel.getText();


            }
            //Reset str, used for displaying multiple genres
            str = "";
            while (rs3.next())
            {
                genreLabel.setText(rs3.getString(1) + " " + str);
                str = genreLabel.getText();
            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong while trying to load the data to the table", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }


    }


}
