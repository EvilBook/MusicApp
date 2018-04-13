package sample.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DatabaseConnection.DbconnectionMusic;
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DeleteAlbumController implements Initializable {



    @FXML
    private TableView<Album> AlbumTable;



    @FXML
    private TableColumn<Album, String> AlbumNameColumn;

    @FXML
    private TableColumn<Album, String> ReleaseDateColumn;

    @FXML
    private TableColumn<Album, String> PriceColumn;

    @FXML
    private TableColumn<Album, String> RecordLabelColumn;



    private ObservableList<Album> data ;
    private DbconnectionMusic dc;

















    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dc = new DbconnectionMusic();






        }


    @FXML
    public void handleLogoutButton(ActionEvent event) throws IOException {
        SwitchScene myapp = new SwitchScene();
        myapp.logOut(event);



    }

    @FXML
    public void handleLoadButton(ActionEvent event) throws IOException{
        Connection connection = dc.connect();

        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT  *FROM album");
            while (rs.next()){
                data.add(new Album(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)

                       ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        // set cell value factory to tableView

       AlbumNameColumn.setCellValueFactory(new PropertyValueFactory<>("albumName"));
        ReleaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        RecordLabelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));


        AlbumTable.setItems(null);
        AlbumTable.setItems(data);


    }






}
