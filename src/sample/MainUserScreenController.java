package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import sample.DatabaseConnection.UpdateDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainUserScreenController implements Initializable {



    @FXML
    Label name;
    @FXML
    HBox horizontalMenu;

    @FXML
    Button moveLeft;

    @FXML
    Button moveRight;

    Stage stage;
    public void SetStage(Stage stage){
        this.stage=stage;

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        horizontalMenu.setSpacing(8);
        horizontalMenu.setMinWidth(400*horizontalMenu.getSpacing());
        horizontalMenu.setAlignment(Pos.CENTER_LEFT);
        for(int i=0;i<14;i++) {
            ImageView imageView=new ImageView();
            Image image=new Image(getClass().getResource("andrei.png").toString());

            imageView.setImage(image);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setOnMouseEntered(event -> {
                imageView.setFitWidth(400);
            });
            imageView.setOnMouseExited(event -> {
                imageView.setFitWidth(300);
            });
            horizontalMenu.getChildren().add(imageView);
        }

        moveLeft.setOnMouseClicked(event -> {
            horizontalMenu.setTranslateX(horizontalMenu.getTranslateX()+80);
        });
        moveRight.setOnMouseClicked(event -> {
            horizontalMenu.setTranslateX(horizontalMenu.getTranslateX()-80);
        });








    }

    public void getName(String userEmail) {
        RetrieveInfoFromDatabase newRetrieve=new RetrieveInfoFromDatabase();
        name.setText(newRetrieve.getName(userEmail));
    }
    public AnchorPane addAnchorPane(AnchorPane grid) {


        AnchorPane anchorpane = new AnchorPane();
        Button buttonSave = new Button("Save");
        Button buttonCancel = new Button("Cancel");

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(buttonSave, buttonCancel);

        anchorpane.getChildren().addAll(grid,hb);   // Add grid from Example 1-5
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(grid, 10.0);

        return anchorpane;
    }
}
