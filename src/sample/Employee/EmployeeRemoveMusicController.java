package sample.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import sample.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeRemoveMusicController implements Initializable {

    //Variables

    //Objects
    SwitchScene sw = new SwitchScene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleRemove(ActionEvent event) {

    }

    public void handleLoadButton(ActionEvent event) {

    }

    public void handleReturnButton(ActionEvent event) throws IOException {
        sw.backToEmp(event);
    }

}
