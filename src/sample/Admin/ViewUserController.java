package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.MainStorage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewUserController implements Initializable {

    //Objects
    AdminStorage access = new AdminStorage();
    MainStorage backTo = new MainStorage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        //access.mainAdminScene(event);
        backTo.mainAdminScreen(event);
    }

    @FXML
    public void handleDeleteButton(ActionEvent event) {

    }

}
