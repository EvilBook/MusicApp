package sample.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DatabaseConnection.DbconnectionMusic;
import sample.DatabaseConnection.RemoveAlbumDatabase;
import sample.DatabaseConnection.ThisIsForConnecting;
import sample.DatabaseConnection.UpdateDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeRemoveMusicController implements Initializable
{
    //Variables
    @FXML private TextField selectionField;
    @FXML private TableColumn<Album, String> idColumn,nameColumn,dateColumn,priceColumn,labelColumn;
    @FXML private TableView<Album> table;

    DbconnectionMusic dbc = new DbconnectionMusic();
    private ObservableList<Album> data;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Run a method to load the data in the table
        try {
            handleLoadButton();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Refer to another class that removes the album from the database
    public void removeData(int id){
        System.out.println("removing");
        RemoveAlbumDatabase rmvDatabase = new RemoveAlbumDatabase();
        rmvDatabase.removeAlbum(id);
    }

    //Button press to remove
    @FXML
    private void handleRemove(){

        //Check if the number is given correctly
        if(!selectionField.getText().isEmpty())
        {
            int id = Integer.parseInt(selectionField.getText());
            removeData(id);
            selectionField.setText("");
        }


    }

    //Loads the data into the table
    @FXML
    public void handleLoadButton() throws IOException{

        Connection connection = dbc.connection;
        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs
        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT  *FROM album");

            while (rs.next())
            {
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
