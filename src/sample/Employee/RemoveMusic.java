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
import sample.DatabaseConnection.RemoveAlbumDatabase;
import sample.DatabaseConnection.ThisIsForConnecting;
import sample.DatabaseConnection.UpdateDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RemoveMusic implements Initializable
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

    //Loads the data into the table
    @FXML
    public void handleLoadButton() throws IOException
    {
        Connection connection = dbc.connection;
        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs
        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM album");

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
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong while trying to load the data", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }
    }


    //Detect double click on the table row
    @FXML
    public void tableClick(MouseEvent event) throws IOException
    {
        //If double click
        if(event.getClickCount() == 2) {

            //Get the album id and put it in the text field
            Album row = table.getSelectionModel().getSelectedItem();
            String idAlbum = row.getAlbumId();
            System.out.println(idAlbum);
            selectionField.setText(idAlbum);
        }
    }

    //Handle button press to remove
    @FXML
    private void handleRemove() throws IOException
    {
        //Check if the number is given correctly
        if(!selectionField.getText().isEmpty() && (selectionField.getText().matches("\\d{1}") ||
                selectionField.getText().matches("\\d{2}")))
        {
            int id = Integer.parseInt(selectionField.getText());
            removeData(id);
            selectionField.setText("");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in an album id to remove.", ButtonType.OK);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }
    }


    //Refer to another class that removes the album from the database
    public void removeData(int id) throws IOException
    {
        System.out.println("removing");
        RemoveAlbumDatabase rmvDatabase = new RemoveAlbumDatabase();
        rmvDatabase.removeAlbum(id);

        //Clear textField and refresh
        handleLoadButton();
        selectionField.setText("");

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
