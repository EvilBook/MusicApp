package sample.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DatabaseConnection.DbconnectionMusic;
import sample.SwitchScene;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewSongsController {


    @FXML
    private TableView<Album> songTable;
    @FXML
    private TableColumn<Songs, String> songIdColumn;

    @FXML
    private TableColumn<Songs, String> songNameColumn;

    @FXML
    private TableColumn<Songs, String> PlayTimecolumn;

    @FXML
    private TableColumn<Songs, String> AlbumIdColumn;


    private ObservableList data ;
    public DbconnectionMusic dc;




    @FXML
    public void handleLoadSong(ActionEvent event) throws IOException {



        String sql = "SELECT *FROM song";




        data = FXCollections.observableArrayList();


        try { 
           DbconnectionMusic dk = new DbconnectionMusic();
            Connection connection = dk.connect();

            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()){
                data.add(new Songs(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)

                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }





        AlbumIdColumn.setCellValueFactory(new PropertyValueFactory<>("album_idalbum"));
        songIdColumn.setCellValueFactory(new PropertyValueFactory<>("idsong"));
        songNameColumn.setCellValueFactory(new PropertyValueFactory<>("songName"));
        PlayTimecolumn.setCellValueFactory(new PropertyValueFactory<>("playtime"));




        songTable.setItems(null);
        songTable.setItems(data);


    }

    @FXML
    private void handlelogoutButton(ActionEvent event) throws IOException{

        SwitchScene myapp = new SwitchScene();
        myapp.logOut(event);


    }
}


