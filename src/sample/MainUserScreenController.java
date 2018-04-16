package sample;

import javafx.animation.*;
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

    @FXML
    AnchorPane base;

    Stage stage;
    public void SetStage(Stage stage){
        this.stage=stage;

    }


    ArrayList<String> names=new ArrayList<>();

    String[] s=new String[]{"Moderat","by: ","Release Date: 12-12-1999","Genre: HARD","Price: 5$"};
    String[] sFull=new String[]{"Moderat","by: ","Release Date: 12-12-1999","Genre(s): HARD","Label: Random","Featuring: More people","Disks: 1","Price: 5$"};


    AnchorPane moreAp;

    ImageView plate;









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
            stackPane.getChildren().addAll(imageView,imageView2,CreateLabels(albumInfo,imageView2,imageView));
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
    public VBox SetText(VBox h,Image image){
        ArrayList<Label> n=new ArrayList<>();
        for(Node l : h.getChildren()){
            n.add((Label)l);

        }
        for(int i=0;i<n.size();i++){
            n.get(i).setText(s[i]);
        }
        HBox m=new HBox();
        Button newButton=new Button();
        newButton.setText("More");
        newButton.setAlignment(Pos.BOTTOM_CENTER);
        newButton.setOnMouseClicked(event -> {
            CreateMore(image);
        });
        newButton.opacityProperty().bind(h.opacityProperty());
        newButton.visibleProperty().bind(h.visibleProperty());
        Button newButton1=new Button();
        newButton1.setText("Buy");
        newButton1.setAlignment(Pos.BOTTOM_CENTER);
        newButton1.opacityProperty().bind(h.opacityProperty());
        newButton1.visibleProperty().bind(h.visibleProperty());
        newButton1.setOnMouseClicked(event -> {
            String toastMsg = "added to basket";
            int toastMsgTime = 1500; //3.5 seconds
            int fadeInTime = 500; //0.5 seconds
            int fadeOutTime= 500; //0.5 seconds
            Toast.makeText(this.stage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
        });
        m.getChildren().addAll(newButton,newButton1);
        m.setAlignment(Pos.CENTER);
        h.getChildren().add(m);
        return h;
    }
    public VBox CreateLabels(VBox h,ImageView iv,ImageView cover){
        for(String i : s){
            Label newLabel=new Label();
            newLabel.setText(i);
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
        return SetText(h,cover.getImage());
    }
    public void CreateMore(Image image){
        if(moreAp!=null){
            showMoreAnimationTwo(moreAp);
        }
        System.out.println("Doesnt Work");
        moreAp=new AnchorPane();
        VBox v=new VBox();
        HBox h=new HBox();
        HBox h1=new HBox();

        ImageView iv=new ImageView();

        Button close=new Button();

        Button buy=new Button();


        StackPane stackPane=new StackPane();


        iv.setImage(image);
        iv.setId("cover");
        iv.setFitWidth(250);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        DropShadow dropShadow=new DropShadow();
        dropShadow.radiusProperty().setValue(15);
        iv.setEffect(dropShadow);

        ImageView imageView3=new ImageView();
        Image image2=new Image(getClass().getResource("Graphics/cover.png").toString());
        imageView3.setImage(image2);
        imageView3.setFitWidth(250);
        imageView3.setPreserveRatio(true);
        imageView3.setVisible(false);
        imageView3.setId("informationPane");
        imageView3.setStyle("-fx-background-color: #ffffff");
        plate=imageView3;


        stackPane.getChildren().addAll(imageView3,iv);



        h.getChildren().add(buy);
        v.visibleProperty().bind(moreAp.visibleProperty());
        h.visibleProperty().bind(moreAp.visibleProperty());
        v.minHeightProperty().bind(moreAp.minHeightProperty());
        v.minWidthProperty().bind(moreAp.minWidthProperty());

        close.setAlignment(Pos.TOP_RIGHT);
        close.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ff1919; -fx-border-radius: 8px; -fx-border-color: #ff1919; -fx-border-width:2px;");
        close.setText("X");
        close.setOnMouseEntered(event -> {
            close.setStyle("-fx-background-color: #ff1919; -fx-text-fill: #fdfffd; -fx-border-radius: 8px; -fx-border-color: #fdfffd; -fx-border-width:2px;");

        });
        close.setOnMouseExited(event -> {
            close.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ff1919; -fx-border-radius: 8px; -fx-border-color: #ff1919; -fx-border-width:2px;");

        });
        close.setOnMouseClicked(event -> {
            showMoreAnimationTwo(moreAp);
        });

        h1.getChildren().addAll(stackPane,close);
        h1.minWidthProperty().bind(v.minWidthProperty());


        v.getChildren().add(h1);

        for(String i : sFull){
            Label newLabel=new Label();
            newLabel.setStyle("-fx-text-fill: #ffffff");
            newLabel.setText(i);
            newLabel.opacityProperty().bind(h.opacityProperty());
            newLabel.visibleProperty().bind(h.visibleProperty());
            newLabel.setAlignment(Pos.CENTER);
            v.getChildren().add(newLabel);
        }
        moreAp.getChildren().add(v);

        h.setStyle("-fx-background-color: rgba(255,58,71,0.17)");
        h.setMinHeight(100);

        v.getChildren().add(h);



        ShowMore(moreAp);


    }
    public void ShowMore(AnchorPane ap){
        System.out.println("still dont");
        ap.setStyle("-background-color: #000000");
        ap.setMinHeight(600);
        ap.setMinWidth(350);

        ap.setTranslateX(-400);
        System.out.println(ap.getChildren().size());
        for(Node n : ap.getChildren()){
            n.setOpacity(100);
            n.setStyle("-fx-background-color: rgba(0,0,0,0.8)");
            System.out.println(n.getOpacity()+"WHAT");
        }
        base.getChildren().add(ap);

        showMoreAnimationOne(ap);
    }
    public void showMoreAnimationOne(AnchorPane ap){

        Timeline time=new Timeline();
        KeyValue kv = new KeyValue(ap.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.4), kv);
        time.getKeyFrames().add(kf);
        time.setOnFinished(t->{
            // remove pane and restore scene 1
            //root1.getChildren().setAll(rectangle1);
            // set scene 2
            //primaryStage.setScene(scene2);
        });
        time.play();


        Timeline time1=new Timeline();
        KeyValue kv1 = new KeyValue(plate.translateXProperty(), 300, Interpolator.EASE_BOTH);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0.4), kv1);
        time1.getKeyFrames().add(kf1);
        time1.setOnFinished(t->{
            // remove pane and restore scene 1
            //root1.getChildren().setAll(rectangle1);
            // set scene 2
            //primaryStage.setScene(scene2);
        });
        time1.play();


    }
    public void showMoreAnimationTwo(AnchorPane ap){
        Timeline time=new Timeline();
        KeyValue kv = new KeyValue(ap.translateXProperty(), -400, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.31), kv);
        time.getKeyFrames().add(kf);
        time.setOnFinished(t->{
            // remove pane and restore scene 1
            //root1.getChildren().setAll(rectangle1);
            // set scene 2
            //primaryStage.setScene(scene2);
        });
        time.play();


        Timeline time1=new Timeline();
        KeyValue kv1 = new KeyValue(plate.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0.4), kv1);
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
