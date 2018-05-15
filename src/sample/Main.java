package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DatabaseConnection.ThisIsForConnecting;

import javax.swing.text.html.CSS;

public class Main extends Application {

    @FXML private Button loginButton;



    @Override
    public void start(Stage primaryStage) throws Exception{


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Vinyl Store");
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setResizable(false);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.show();
        ThisIsForConnecting connect =new ThisIsForConnecting();


    }


    public static void main(String[] args) {

        launch(args);
    }



}

