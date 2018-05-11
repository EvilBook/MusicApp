package sample.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DatabaseConnection.MusicDBConnection;
import sample.DatabaseConnection.RemoveAlbumDatabase;
import sample.MainStorage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveMusic implements Initializable {

    //Variables
    @FXML private TextField selectionField;
    @FXML private TableColumn <Album, String> idColumn;
    @FXML private TableColumn <Album, String> nameColumn;
    @FXML private TableColumn <Album, String > dateColumn;
    @FXML private TableColumn <Album, String> priceColumn;
    @FXML private TableColumn <Album, String> labelColumn;
    @FXML private TableView <Album> table;
    private ObservableList <Album> data;

    //Objects
    EmployeeStorage access = new EmployeeStorage();
    MusicDBConnection dbc = new MusicDBConnection();
    Connection connection = dbc.connection;
    RemoveAlbumDatabase rmvDatabase = new RemoveAlbumDatabase();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleRemove(ActionEvent event) {
        if(!selectionField.getText().isEmpty())
        {
            int id = Integer.parseInt(selectionField.getText());
            removeData(id);
        }
    }

    public void removeData(int id){
        System.out.println("removing");
        rmvDatabase.removeAlbum(id);
    }



    public void handleLoadButton(ActionEvent event) {
        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT  * FROM album");
            while (rs.next()){
                data.add(new Album(rs.getString(1), rs.getString(2),
                                   rs.getString(3), rs.getString(4),
                                   rs.getString(5)));

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

    public void handleCloseButton(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
