package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DatabaseConnection.ThisIsForConnecting;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Vinyl Store");
        primaryStage.setScene(new Scene(root,1066.62, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        ThisIsForConnecting connect = new ThisIsForConnecting();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

