package sample.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DatabaseConnection.DbconnectionMusic;
import sample.DatabaseConnection.RemoveAlbumDatabase;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeViewMusicController implements Initializable
{
    @FXML private TextField selectionField;
    @FXML private TableColumn<Album, String> idColumn;
    @FXML private TableColumn<Album, String> nameColumn;
    @FXML private TableColumn<Album, String > dateColumn;
    @FXML private TableColumn<Album, String> priceColumn;
    @FXML private TableColumn<Album, String> labelColumn;
    @FXML private TableView<Album> table;

    DbconnectionMusic dbc = new DbconnectionMusic();
    private ObservableList<Album> data;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            handleLoadButton();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML
    public void handleLoadButton() throws IOException{

        Connection connection = dbc.connection;
        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT  *FROM album");
            while (rs.next()){
                data.add(new Album(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)
                ));

                // set cell value factory to tableView

                idColumn.setCellValueFactory(new PropertyValueFactory<>("albumId"));

                nameColumn.setCellValueFactory(new PropertyValueFactory<>("albumName"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                labelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));

                table.setItems(null);
                table.setItems(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


    @FXML
    public void tableClick(MouseEvent event) throws IOException {

        if(event.getClickCount() == 2) {

            Album row = table.getSelectionModel().getSelectedItem();
            String idAlbum = row.getAlbumId();
            System.out.println(idAlbum);

            int id = Integer.parseInt(idAlbum);

            EmployeeDataStorage.getInstance().setMessage(id);

            //When button is clicked pop up the second stage
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("EmployeeViewMusicPopUp.fxml"));
            stage.setTitle("Album Information");
            EmployeeViewMusicPopUpController popC = new EmployeeViewMusicPopUpController();
            stage.setScene(new Scene(root, 698, 500));
            stage.show();

        }
    }

    //Switch Scenes
    @FXML
    private void handleSwitchScenes(ActionEvent event) throws IOException
    {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("employeeScreen.fxml"));
        Parent root;
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
