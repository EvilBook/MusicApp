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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DatabaseConnection.DbconnectionMusic;
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DeleteAlbumController implements Initializable {


    @FXML
    private TableView<Album> AlbumTable;

    @FXML
    private TableColumn<Album, String> AlbumIdColumn;

    @FXML
    private TableColumn<Album, String> AlbumNameColumn;

    @FXML
    private TableColumn<Album, String> ReleaseDateColumn;

    @FXML
    private TableColumn<Album, String> PriceColumn;

    @FXML
    private TableColumn<Album, String> RecordLabelColumn;

    @FXML
    private TextField DeleteTextField;




    private ObservableList<Album> data;
    private DbconnectionMusic dc;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dc = new DbconnectionMusic();


    }


    @FXML
    private void handleDeleteAlbumButton(ActionEvent event) throws IOException {
        int index2delete = Integer.parseInt(DeleteTextField.getText());
        delete(index2delete);
        DeleteTextField.clear();


    }


    @FXML
    public void handleLogoutButton(ActionEvent event) throws IOException {
        SwitchScene myapp = new SwitchScene();
        myapp.logOut(event);


    }

    @FXML
    public void handleLoadButton(ActionEvent event) throws IOException {
        Connection connection = dc.connect();

        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT  *FROM album");
            while (rs.next()) {
                data.add(new Album(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        // set cell value factory to tableView

        AlbumIdColumn.setCellValueFactory(new PropertyValueFactory<>("albumId"));

        AlbumNameColumn.setCellValueFactory(new PropertyValueFactory<>("albumName"));
        ReleaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        RecordLabelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));


        AlbumTable.setItems(null);
        AlbumTable.setItems(data);


    }


    public void delete(int selectedInDEX) {
        Connection connection = dc.connect();

        String sql = " delete from album where idalbum = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, selectedInDEX);

            preparedStatement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    @FXML

    public void ReturnButton(ActionEvent event) throws IOException {
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








