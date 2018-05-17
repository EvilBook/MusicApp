package sample.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class EmployeeStorage {


    public void employeeAddMusic(ActionEvent event, Button addButton) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("addMusic.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addButton.getScene().getWindow());
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void employeeRemoveMusic(ActionEvent event, Button removeButton) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("removeMusic.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(removeButton.getScene().getWindow());
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void employeeViewMusic(ActionEvent event, Button removeButton) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("viewMusic.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(removeButton.getScene().getWindow());
        stage.setResizable(false);
        stage.showAndWait();
    }
}
