package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import sample.DatabaseConnection.UpdateDatabase;

import java.io.File;
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

    @FXML
    TextField search;

    Stage stage;
    public void SetStage(Stage stage){
        this.stage=stage;

    }


    ArrayList<String> names=new ArrayList<>();

    String[] s=new String[]{"Moderat","by: ","Release Date: 12-12-1999","Genre: HARD","Price: 5$"};






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReadNames();

        horizontalMenu.setSpacing(14);
        horizontalMenu.setMinWidth(400*names.size());
        horizontalMenu.setAlignment(Pos.CENTER_LEFT);
        for(int i=0;i<names.size();i++) {
            StackPane stackPane=new StackPane();
            ImageView imageView=new ImageView();
            AnchorPane pane=new AnchorPane();
            Image image=new Image(getClass().getResource("Graphics/Records/"+names.get(i)).toString());

            imageView.setImage(image);
            imageView.setId("cover");
            imageView.setFitWidth(250);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            DropShadow dropShadow=new DropShadow();
            dropShadow.radiusProperty().setValue(15);
            imageView.setEffect(dropShadow);

            ImageView imageView2=new ImageView();
            Image image1=new Image(getClass().getResource("Graphics/tinyBlackSquare.png").toString());
            imageView2.setImage(image1);
            imageView2.setFitWidth(250);
            imageView2.setPreserveRatio(true);
            imageView2.setVisible(false);
            imageView2.setId("informationPane");
            VBox albumInfo=new VBox();
            pane.setStyle("-fx-background-color: #ffffff");
            stackPane.getChildren().addAll(imageView,imageView2,CreateLabels(albumInfo,imageView2));
            stackPane.setStyle("-fx-background-color: #ffffff");
            stackPane.setMaxWidth(250);
            stackPane.setMaxHeight(250);
            horizontalMenu.getChildren().add(stackPane);

            stackPane.setOnMouseEntered(event -> {
                AnimationOne(stackPane);

            });
            stackPane.setOnMouseExited(event -> {
                AnimationTwo(stackPane);

            });
        }

        moveLeft.setOnMouseClicked(event -> {
            //horizontalMenu.setTranslateX(horizontalMenu.getTranslateX()+80);
            Timeline time=new Timeline();
            KeyValue kv = new KeyValue(horizontalMenu.layoutXProperty(),horizontalMenu.getLayoutX()+400, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.4), kv);
            time.getKeyFrames().add(kf);
            time.setOnFinished(t->{
                // remove pane and restore scene 1
                //root1.getChildren().setAll(rectangle1);
                // set scene 2
                //primaryStage.setScene(scene2);
            });
            time.play();
        });
        moveRight.setOnMouseClicked(event -> {
            Timeline time=new Timeline();
            KeyValue kv = new KeyValue(horizontalMenu.layoutXProperty(),horizontalMenu.getLayoutX()-400, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.4), kv);
            time.getKeyFrames().add(kf);
            time.setOnFinished(t->{
                // remove pane and restore scene 1
                //root1.getChildren().setAll(rectangle1);
                // set scene 2
                //primaryStage.setScene(scene2);
            });
            time.play();
        });










    }

    public void getName(String userEmail) {
        RetrieveInfoFromDatabase newRetrieve=new RetrieveInfoFromDatabase();
        name.setText(newRetrieve.getName(userEmail));
    }
    public ArrayList<String> ReadNames() {
        File folder = new File("C:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Graphics\\Records");
        File[] listOfFiles = folder.listFiles();
        System.out.println(folder.listFiles());

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                names.add(listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return names;

    }
    public void AnimationOne(StackPane s){

        ArrayList<Node> q=new ArrayList<>();
        for(Node i : s.getChildren()){
            q.add(i);
        }
        for(int ii=0;ii<2;ii++){
            ImageView newOne=(ImageView)q.get(ii);
            Timeline time=new Timeline();
                KeyValue kv = new KeyValue(newOne.fitWidthProperty(), 350, Interpolator.EASE_BOTH);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.15), kv);
                time.getKeyFrames().add(kf);
                time.setOnFinished(t->{
                    // remove pane and restore scene 1
                    //root1.getChildren().setAll(rectangle1);
                    // set scene 2
                    //primaryStage.setScene(scene2);
                });
                time.play();
                if(newOne.getId()=="informationPane"){
                    newOne.setVisible(true);
                    newOne.setOpacity(0);
                    Timeline time1=new Timeline();
                    KeyValue kv1 = new KeyValue(newOne.opacityProperty(), 100, Interpolator.EASE_BOTH);
                    KeyFrame kf1 = new KeyFrame(Duration.seconds(4), kv1);
                    time1.getKeyFrames().add(kf1);
                    time1.setOnFinished(t->{
                        // remove pane and restore scene 1
                        //root1.getChildren().setAll(rectangle1);
                        // set scene 2
                        //primaryStage.setScene(scene2);
                    });
                    time1.play();
                }
        }
    }
    public void AnimationTwo(StackPane s){
        ArrayList<Node> q=new ArrayList<>();
        for(Node i : s.getChildren()){
            q.add(i);
        }
        for(int ii=0;ii<2;ii++){
            Timeline time=new Timeline();
            ImageView newOne=(ImageView)q.get(ii);
            KeyValue kv = new KeyValue(newOne.fitWidthProperty(), 250, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.15), kv);
            time.getKeyFrames().add(kf);
            time.setOnFinished(t->{
                // remove pane and restore scene 1
                //root1.getChildren().setAll(rectangle1);
                // set scene 2
                //primaryStage.setScene(scene2);
            });
            time.play();
            if(newOne.getId()=="informationPane"){
                newOne.setVisible(false);
            }
        }
    }
    public VBox SetText(VBox h){
        ArrayList<Label> n=new ArrayList<>();
        for(Node l : h.getChildren()){
            n.add((Label)l);

        }
        for(int i=0;i<n.size();i++){
            n.get(i).setText(s[i]);
        }
        return h;
    }
    public VBox CreateLabels(VBox h,ImageView iv){
        for(String i : s){
            Label newLabel=new Label();
            newLabel.setStyle("-fx-text-fill: #ffffff");
            newLabel.opacityProperty().bind(h.opacityProperty());
            newLabel.visibleProperty().bind(h.visibleProperty());
            newLabel.setAlignment(Pos.CENTER);
            h.getChildren().add(newLabel);
        }
        h.setAlignment(Pos.CENTER);
        h.setOpacity(0);
        h.opacityProperty().bind(iv.opacityProperty());
        h.visibleProperty().bind(iv.visibleProperty());
        return SetText(h);
    }
}
