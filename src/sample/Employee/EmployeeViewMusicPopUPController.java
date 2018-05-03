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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class EmployeeViewMusicPopUPController implements Initializable
{
    @FXML private TableColumn<Songs, String> songidColumn;
    @FXML private TableColumn<Songs, String> songnameColumn;
    @FXML private TableColumn<Songs, String> songtimeColumn;
    @FXML private TableColumn<Songs, String> songartistColumn;
    @FXML private Label albumLabel;
    @FXML private Label artistLabel;
    @FXML private Label genreLabel;
    @FXML private Label vynlLabel;
    @FXML private Label labelLabel;

    private int idAlbum;

    @FXML private TableView<Songs> table;

    DbconnectionMusic dbc = new DbconnectionMusic();
    private ObservableList<Songs> data;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
      //  idAlbum = EmployeeDataStorage.getInstance().getMessage();
        loadData(idAlbum);
    }

    public void loadData(int idAlbum){
        Connection connection = dbc.connect();
        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery(
                    "SELECT idSong, songName, songArtist, playtime FROM song, album,songartist WHERE (idSong = song_idSong) && " +
                            "(album_idAlbum =" + idAlbum +"&& idAlbum = "+idAlbum+");");

            while (rs.next()){
                data.add(new Songs(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

                // set cell value factory to tableView
                songidColumn.setCellValueFactory(new PropertyValueFactory<>("idSong"));
                songnameColumn.setCellValueFactory(new PropertyValueFactory<>("songName"));
                songartistColumn.setCellValueFactory(new PropertyValueFactory<>("songArtist"));
                songtimeColumn.setCellValueFactory(new PropertyValueFactory<>("playtime"));

                table.setItems(null);
                table.setItems(data);
            }


            ResultSet rs2 = connection.createStatement().executeQuery(
                    "select distinct idalbum, albumname, date, price, label, vynlNumber, albumartist from album,albumartist where " +
                            "idAlbum = "+idAlbum+" && album_idAlbum = "+idAlbum+" ;");
            ResultSet rs3 = connection.createStatement().executeQuery(
                    "select distinct genre from album,genre where idAlbum = "+idAlbum+" && album_idAlbum = "+idAlbum+" ;");


            while(rs2.next()){
                albumLabel.setText(rs2.getString(2));
                artistLabel.setText(rs2.getString(7));
                vynlLabel.setText(rs2.getString(6));
                labelLabel.setText(rs2.getString(5));
            }
            while (rs3.next()){
                genreLabel.setText(rs3.getString(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}



