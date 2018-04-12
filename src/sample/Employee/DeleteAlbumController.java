package sample.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import sample.DatabaseConnection.ThisIsForConnecting;
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DeleteAlbumController implements Initializable {
    @FXML

    private javafx.scene.control.TextArea TextArea;












    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RetrieveInfoFromDatabase myapp = new RetrieveInfoFromDatabase();
        myapp.getAlbumTable();








    }

































    @FXML
    public void handleLogoutButton(ActionEvent event) throws IOException {
        SwitchScene myapp = new SwitchScene();
        myapp.logOut(event);



    }


}
