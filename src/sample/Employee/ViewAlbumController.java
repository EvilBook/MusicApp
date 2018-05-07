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
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class ViewAlbumController implements Initializable {


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
    private TextField searchTextfield;
    @FXML
    private TableColumn<AlbumNameViewClass,String> AlbumColumnView;
    @FXML
    private TableView<AlbumNameViewClass>AlbumTable1;




    private ObservableList<Album> data;
    private ObservableList<AlbumNameViewClass> data1;

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


    @FXML
    public void tableClick(MouseEvent event) throws IOException {

        if (event.getClickCount() == 2) {

            Album row = AlbumTable.getSelectionModel().getSelectedItem();
            String idAlbum = row.getAlbumId();
            System.out.println(idAlbum);

            int id = Integer.parseInt(idAlbum);

            EmployeeDataStorage.getInstance().setMessage(id);

            //When button is clicked pop up the second stage
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("EmployeeViewMusicPopUp.fxml"));
            stage.setTitle("Album Information");
            EmployeeViewMusicPopUPController popC = new EmployeeViewMusicPopUPController();
            stage.setScene(new Scene(root, 698, 500));
            stage.show();

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
    @FXML
    public void SearchAlbumButton(ActionEvent event)throws IOException{
        Connection connection = dc.connect();
        data1 = FXCollections.observableArrayList();


        try{

            String sql = "select *from album where albumName like '"+'%'+searchTextfield.getText()+'%'+"' ";
           PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            while (result.next()) {
               data1.add(new AlbumNameViewClass(result.getString(2)));


                System.out.println(result.getString(2));


            }

        }
        catch(Exception e){
           e.printStackTrace();
            }


        AlbumColumnView.setCellValueFactory(new PropertyValueFactory<>("albumName"));
        AlbumTable1.setItems(null);
        AlbumTable1.setItems(data1);


    }
    @FXML
    public void tableClick1(MouseEvent event) throws IOException {

        if (event.getClickCount() == 2) {

            AlbumNameViewClass row = AlbumTable1.getSelectionModel().getSelectedItem();
            String AlbumName = row.getAlbumName();
            System.out.println(AlbumName);



            EmployeeDataStorage.getInstance().setMessage1(AlbumName);


            //When button is clicked pop up the second stage
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("EmployeeViewMusicPopUp.fxml"));
            stage.setTitle("Album Information");
            EmployeeViewMusicPopUPController popC = new EmployeeViewMusicPopUPController();
            stage.setScene(new Scene(root, 698, 500));
            stage.show();

        }


    }


}
