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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DatabaseConnection.DbconnectionMusic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewMusic implements Initializable
{
    //Variables
    @FXML private TextField selectionField;
    @FXML private TableColumn<Album, String> idColumn, nameColumn, dateColumn, priceColumn, labelColumn;
    @FXML private TableView<Album> table;

    DbconnectionMusic dbc = new DbconnectionMusic();
    private ObservableList<Album> data;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            handleLoadButton();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Load the data in the table
    @FXML
    public void handleLoadButton() throws IOException
    {
        Connection connection = dbc.connection;
        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs
        try
        {
            ResultSet rs = connection.createStatement().executeQuery("SELECT  * FROM album");

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
        }
        catch (SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong while trying to load the data into the table", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }

    }


    //Handles clicking on a table row
    @FXML
    public void tableClick(MouseEvent event) throws IOException
    {
        //If double click
        if(event.getClickCount() == 2)
        {
            //Get the album id and save it in the singleton class
            Album row = table.getSelectionModel().getSelectedItem();
            String idAlbum = row.getAlbumId();
            System.out.println(idAlbum);

            int id = Integer.parseInt(idAlbum);
            EmployeeDataStorage.getInstance().setMessage(id);


            //When button is clicked pop up the second stage
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("viewMusicPopup.fxml"));
            stage.setTitle("Album Information");
            ViewMusicPopup popC = new ViewMusicPopup();
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
