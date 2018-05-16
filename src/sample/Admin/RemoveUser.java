package sample.Admin;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.DatabaseConnection.PersonDBConnection;
import sample.DatabaseConnection.RemoveEmployeeDatabase;
import sample.Employee.Employee;
import sample.MainStorage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveUser implements Initializable {

    //Variables
    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn <Employee, String> firstColumn;
    @FXML private TableColumn <Employee, String> lastColumn;
    @FXML private TableColumn <Employee, String> birthColumn;
    @FXML private TableColumn <Employee, String> phoneColumn;
    @FXML private TableColumn <Employee, String> addressColumn;
    @FXML private TableColumn <Employee, String> emailColumn;
    @FXML private TextField removeTextField;
    private ObservableList<Employee> employeeList;


    //Objects
    MainStorage access = new MainStorage();
    PersonDBConnection dbc = new PersonDBConnection();
    Connection connection = dbc.connection;
    RemoveEmployeeDatabase red = new RemoveEmployeeDatabase();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            handleLoadButton();
        });
    }

    @FXML
    public void tableClick(MouseEvent event) throws IOException
    {
        //If double click
        if(event.getClickCount() == 2) {

            //Get the album id and put it in the text field
            Employee row = employeeTable.getSelectionModel().getSelectedItem();
            String idAlbum = row.getEmail();
            System.out.println(idAlbum);
            removeTextField.setText(idAlbum);
        }
    }

    public void handleLoadButton() {
        employeeList = FXCollections.observableArrayList();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM person WHERE login_email NOT LIKE '%@mass.com'");
            while (rs.next()){
                employeeList.add(new Employee(rs.getString(1), rs.getString(2),
                                              rs.getString(3), rs.getString(4),
                                              rs.getString(5), rs.getString(6)));

                // set cell value factory to tableView
                firstColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
                lastColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                employeeTable.setItems(null);
                employeeTable.setItems(employeeList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleRemoveButton(ActionEvent event) {
        if(!removeTextField.getText().isEmpty())
        {
            String email = removeTextField.getText();
            removeData(email);
            handleLoadButton();

        }
    }

    public void removeData(String email){
        System.out.println("removing");
        red.removeEmployee(email);
    }

    public void handleReturnButton(ActionEvent event) throws IOException {
        access.mainAdminScreen(event);
    }

}
