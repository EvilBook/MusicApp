package sample;



import javafx.animation.*;

import javafx.application.Platform;

import javafx.beans.InvalidationListener;

import javafx.beans.Observable;

import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.Node;

import javafx.scene.canvas.Canvas;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.*;

import javafx.scene.effect.*;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.*;

import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;

import javafx.scene.media.MediaView;

import javafx.scene.paint.Color;

import javafx.scene.shape.*;

import javafx.stage.Stage;

import javafx.util.Duration;

import sample.DatabaseConnection.RetrieveInfoFromDatabase;



import java.io.File;

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

    @FXML

    AnchorPane base1;



    Stage stage;

    private MusicPlayer musicPlayer=MusicPlayer.getInstance();

    private boolean musicState;



    public void SetStage(Stage stage){

        this.stage=stage;



    }





    ArrayList<String> names=new ArrayList<>();

    ArrayList<String> nameMusic =new ArrayList<>();



    String[] s=new String[]{"Moderat","by: ","Release Date: 12-12-1999","Genre: HARD","Price: 5$"};

    String[] sSongs=new String[]{"song1:","song2","song3","song4","song5","song8"};

    String[] sFull=new String[]{"Moderat","by: ","Release Date: 12-12-1999","Genre(s): HARD","Label: Random","Featuring: More people","Disks: 1","Price: 5$"};





    AnchorPane moreAp;



    ImageView plate;





    ArrayList<Label> moreInfoArray=new ArrayList<>();





    MediaPlayer m4;





    MediaPlayer currentSong;

    Float currentSongTime;





    Timeline currentAnimation;





    AnchorPane musicAp;





    Slider sliderPlayer=new Slider();



    Button sliderButton;



    MediaPlayer mediaPlayerMusic;



    MediaPlayer mediaPlayer;





    StackPane stackPane1=new StackPane();













































    @Override

    public void initialize(URL location, ResourceBundle resources) {

        MainMenu();

        ReadNames();

        ReadNamesMusic();



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

            });

            time.play();

        });

        moveRight.setOnMouseClicked(event -> {

            Timeline time=new Timeline();

            KeyValue kv = new KeyValue(horizontalMenu.layoutXProperty(),horizontalMenu.getLayoutX()-400, Interpolator.EASE_BOTH);

            KeyFrame kf = new KeyFrame(Duration.seconds(0.4), kv);

            time.getKeyFrames().add(kf);

            time.setOnFinished(t->{

            });

            time.play();

        });





















    }



    public void MainMenu() {






    }



    public void getName(String userEmail) {

        RetrieveInfoFromDatabase newRetrieve=new RetrieveInfoFromDatabase();

        name.setText(newRetrieve.getName(userEmail));

    }

    public ArrayList<String> ReadNames() {

        File folder = new File("C:\\Users\\jjoog\\Desktop\\MusicApp\\src\\sample\\Graphics\\Records");

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

    public ArrayList<String> ReadNamesMusic() {

        File folder = new File("C:\\Users\\jjoog\\Desktop\\MusicApp\\src\\sample\\Music");

        File[] listOfFiles = folder.listFiles();

        System.out.println(folder.listFiles());



        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {

                System.out.println("File " + listOfFiles[i].getName());

                nameMusic.add(listOfFiles[i].getName());

            } else if (listOfFiles[i].isDirectory()) {

                System.out.println("Directory " + listOfFiles[i].getName());

            }

        }

        return nameMusic;



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

            if(l instanceof Label) {

                n.add((Label) l);

            }



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

            int toastMsgTime = 1500;

            int fadeInTime = 500;

            int fadeOutTime= 500;

            //Toast.makeText(this.stage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);

        });

        m.getChildren().addAll(newButton,newButton1);

        m.setAlignment(Pos.CENTER);

        h.getChildren().add(m);

        return h;

    }

    public VBox CreateLabels(VBox h,ImageView iv,ImageView cover){

        ImageView imageView=new ImageView();

        Image image1=new Image(getClass().getResource("Graphics/andrei.png").toString());



        imageView.setImage(image1);

        imageView.setFitWidth(80);

        ColorAdjust colorAdjust=new ColorAdjust();

        colorAdjust.setBrightness(100);

        DropShadow dropShadow=new DropShadow();

        dropShadow.setColor(new Color(1,1,1,1));

        dropShadow.setRadius(0);

        colorAdjust.setInput(dropShadow);

        imageView.setEffect(colorAdjust);

        imageView.setPreserveRatio(true);

        imageView.setSmooth(true);

        imageView.setCache(true);

        h.getChildren().add(imageView);

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

        HBox h2=new HBox();

















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

        imageView3.setFitWidth(230);

        imageView3.setPreserveRatio(true);

        imageView3.setStyle("-fx-background-color: #ffffff");

        plate=imageView3;





        stackPane.getChildren().addAll(imageView3,iv);















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

            showSongsAnimationTwo(musicAp);

            musicPlayer.slider.setValue(sliderPlayer.getValue());

            showPlayer();

        });



        h1.getChildren().addAll(stackPane,close);

        h1.minWidthProperty().bind(v.minWidthProperty());





        v.getChildren().add(h1);



        VBox v3=new VBox();





        for(String i : sFull){

            Label newLabel=new Label();

            newLabel.setStyle("-fx-text-fill: #ffffff");

            newLabel.setText(i);

            newLabel.opacityProperty().bind(h.opacityProperty());

            newLabel.visibleProperty().bind(h.visibleProperty());

            newLabel.setAlignment(Pos.CENTER);

            v3.getChildren().add(newLabel);

            v3.setAlignment(Pos.BOTTOM_CENTER);

            moreInfoArray.add(newLabel);

        }

        VBox v2=new VBox();





        for(String i : sSongs){

            Label newLabel=new Label();

            newLabel.setStyle("-fx-text-fill: #ffffff");

            newLabel.setText(i);

            newLabel.opacityProperty().bind(h.opacityProperty());

            newLabel.visibleProperty().bind(h.visibleProperty());

            v2.getChildren().add(newLabel);

        }









        v.getChildren().addAll(h2, v3);

















        Media media=new Media(new File("C:\\Users\\jjoog\\Desktop\\MusicApp\\src\\sample\\musicOne.mp3").toURI().toString());

        MediaPlayer mediaPlayer=new MediaPlayer(media);

        MediaView mediaView=new MediaView(mediaPlayer);































        moreAp.getChildren().addAll(v);









        ShowMore(moreAp);

        ShowMusic();





    }

    public void ShowMore(AnchorPane ap){







        StackPane rootPane = new StackPane();











        rootPane.translateXProperty().bind(ap.translateXProperty());







        // circle container

        Pane container = new Pane();



        // circle container is a child of the root pane

        //rootPane.getChildren().addAll(container, moreAp);



        // background style for the container

        container.setStyle("-fx-background-color: rgba(25,40,80,0)");



        // create the milk glass pane

        MilkGlassPane milkGlassPane = new MilkGlassPane(base);

        milkGlassPane.setMaxSize(390, 600);





        milkGlassPane.translateXProperty().bind(moreAp.translateXProperty());





        rootPane.getChildren().addAll(milkGlassPane, moreAp);





        base1.getChildren().add(rootPane);



        System.out.println("still dont");

        ap.setMinHeight(600);

        ap.setMinWidth(390);



        ap.setTranslateX(-400);

        System.out.println(ap.getChildren().size());

        for(Node n : ap.getChildren()){

            n.setOpacity(100);

            n.setStyle("-fx-background-color: rgba(0,0,0,0.16)");

            System.out.println(n.getOpacity()+"WHAT");

        }



        showMoreAnimationOne(ap);

    }

    public void showMoreAnimationOne(AnchorPane ap){



        Timeline time=new Timeline();

        KeyValue kv = new KeyValue(ap.translateXProperty(), 0, Interpolator.EASE_BOTH);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.4), kv);

        time.getKeyFrames().add(kf);

        time.setOnFinished(t->{

        });

        time.play();





        Timeline time1=new Timeline();

        KeyValue kv1 = new KeyValue(plate.translateXProperty(), 150, Interpolator.EASE_BOTH);

        KeyValue kv2 = new KeyValue(plate.rotateProperty(), 300, Interpolator.EASE_BOTH);

        KeyFrame kf1 = new KeyFrame(Duration.seconds(0.2), kv1,kv2);

        time1.getKeyFrames().add(kf1);

        time1.setOnFinished(t->{

        });

        time1.play();





    }

    public void showMoreAnimationTwo(AnchorPane ap){

        Timeline time=new Timeline();

        KeyValue kv = new KeyValue(ap.translateXProperty(), -400, Interpolator.EASE_BOTH);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.31), kv);

        time.getKeyFrames().add(kf);

        time.setOnFinished(t->{

        });

        time.play();





        Timeline time1=new Timeline();

        KeyValue kv1 = new KeyValue(plate.translateXProperty(), 0, Interpolator.EASE_BOTH);

        KeyValue kv2 = new KeyValue(plate.rotateProperty(), 0, Interpolator.EASE_BOTH);

        KeyFrame kf1 = new KeyFrame(Duration.seconds(0.4), kv1,kv2);

        time1.getKeyFrames().add(kf1);

        time1.setOnFinished(t->{

        });

        time1.play();













    }

    public void ShowMusic(){

        AnchorPane apMusic=new AnchorPane();

        VBox v=new VBox();





        apMusic.setTranslateX(0);



        apMusic.getChildren().add(v);



        apMusic.setTranslateY(534);







        base.getChildren().add(apMusic);





        ScrollPane scrollPane=new ScrollPane();





        apMusic.getChildren().add(scrollPane);





        scrollPane.setTranslateY(70);









        apMusic.setOnMouseClicked(event -> {

            System.out.println("BITCH");

        });













        CreateSongs(apMusic,v,scrollPane);





    }



    public void CreateSongs(AnchorPane ap,VBox vOne, ScrollPane scrollPane){





        VBox v = new VBox();





        Label newLabel=new Label();





        newLabel.setText("Album Songs");





        newLabel.setAlignment(Pos.CENTER);





        newLabel.setStyle("-fx-text-fill: #ffffff;");







        Button newButton=new Button();





        newButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-radius: 0px; -fx-border-width: 0px 4px 4px 0px; -fx-rotate: 45px;");





        newButton.setAlignment(Pos.CENTER);





        newButton.setOnMouseClicked(event -> {





            showSongsAnimationTwo(musicAp);



        });







        v.getChildren().addAll(showSongsAnimationButton(), newLabel);





        v.setAlignment(Pos.TOP_CENTER);





        v.setMaxSize(389,40);

        v.setMinSize(389,80);





        newLabel.setAlignment(Pos.CENTER);





        v.setSpacing(0);





        newLabel.setTranslateY(14);























        ap.getChildren().add(v);

























        for(int i = 0; i< nameMusic.size(); i++) {





            Label label = new Label();





            label.setText(nameMusic.get(i));

            label.setStyle("-fx-text-fill: #ffffff");





            Media media = new Media(new File("C:\\Users\\jjoog\\Desktop\\MusicApp\\src\\sample\\Music\\"+(nameMusic.get(i)).toString()).toURI().toString());

            MediaPlayer mediaPlayer = new MediaPlayer(media);

            MediaView mediaView = new MediaView(mediaPlayer);

            mediaPlayerMusic=mediaPlayer;

            Label l=new Label();

            Label l2=new Label();



            Pane play = musicButtonAnimation(media);









            ap.setStyle("-fx-background-color: linear-gradient(#587d9d, #2e4052); -fx-background-radius: 24px;");





            ap.setEffect(new DropShadow());





            musicPlayer.setPlaylist(media, play);

















            Slider slider = new Slider();

            slider.setMinSize(250, 50);

            ProgressBar progressBar=new ProgressBar(0);

            progressBar.setMinWidth(250);

            StackPane stackPane=new StackPane();

            musicPlayer.i=i;

            Label lTwo=new Label();

            lTwo.setText(musicPlayer.formatTime(musicPlayer.playlist.get(play).getCurrentTime(),musicPlayer.playlist.get(play).getMedia().getDuration()));

            musicPlayer.playlist.get(play).setOnPlaying(() -> {

                Timeline currentAnimation = new Timeline();

                KeyValue kv1 = new KeyValue(slider.valueProperty(), 100, Interpolator.EASE_BOTH);

                Timeline currentAnimation2=new Timeline();

                currentAnimation2.setCycleCount(Animation.INDEFINITE);

                KeyValue kv2 = new KeyValue(plate.rotateProperty(), plate.rotateProperty().intValue()+360, Interpolator.EASE_BOTH);

                KeyFrame kf2=new KeyFrame(Duration.seconds(4),kv2);

                currentAnimation2.getKeyFrames().add(kf2);

                currentAnimation2.play();

                KeyFrame kf1 = new KeyFrame(new Duration(musicPlayer.playlist.get(play).getTotalDuration().toMillis()), kv1);

                currentAnimation.getKeyFrames().add(kf1);

                currentAnimation.setOnFinished(t -> {

                    // remove pane and restore scene 1

                    //root1.getChildren().setAll(rectangle1);

                    // set scene 2

                    //primaryStage.setScene(scene2);

                });

                currentAnimation.play();

                System.out.println(mediaPlayer.getStatus());

                System.out.println(currentAnimation.getStatus());

                System.out.println(slider.getValue());

                System.out.println(musicPlayer.playlist.get(play).getTotalDuration().toSeconds());

            });



            System.out.println(slider.maxProperty());





            slider.valueProperty().addListener(observable -> {

                lTwo.setText(musicPlayer.formatTime(musicPlayer.playlist.get(play).getCurrentTime(),musicPlayer.playlist.get(play).getMedia().getDuration()));

                if(slider.isValueChanging()){

                    musicPlayer.playlist.get(musicPlayer.currentlyPlaying).seek(musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getMedia().getDuration().multiply(slider.getValue()/100));



                }

                musicPlayer.slider.setValue(slider.getValue());

                progressBar.setProgress(slider.getValue()*0.01);

            });

























            Label lOne=new Label();















            HBox h4 = new HBox();





            stackPane.getChildren().addAll(progressBar, slider);





            h4.setTranslateX(8);









            h4.getChildren().addAll(play, stackPane, lTwo);





            h4.setAlignment(Pos.CENTER);





            h4.setSpacing(6.8);













            vOne.getChildren().addAll(label, mediaView, h4);



            vOne.setPadding(new Insets(0,0,0,8));

























        }

        scrollPane.setContent(vOne);

        scrollPane.setStyle("-fx-background-color: null; -fx-border-width: 2px 0px 0px 0px; -fx-border-color: #ffffff;");

        scrollPane.setMaxSize(389,368);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setFitToWidth(true);

        DropShadow dropShadow=new DropShadow();

        musicAp=ap;







        moreAp.getChildren().add(ap);





    }



    public void showSongsAnimation(AnchorPane ap) {





        musicState=true;









        Timeline time = new Timeline();

        KeyValue kv = new KeyValue(ap.translateYProperty(), 250, Interpolator.EASE_BOTH);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), kv);

        time.getKeyFrames().add(kf);

        time.setOnFinished(t -> {

        });

        time.play();





    }

    public void showSongsAnimationTwo(AnchorPane ap){





        musicState=false;





        Timeline time = new Timeline();

        KeyValue kv = new KeyValue(ap.translateYProperty(), 534, Interpolator.EASE_BOTH);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);

        time.getKeyFrames().add(kf);

        time.setOnFinished(t -> {

        });

        time.play();







    }





    public void showPlayer(){

        AnchorPane ap=new AnchorPane();

        HBox h=new HBox();

        VBox v=new VBox();



        if(musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getStatus()!= MediaPlayer.Status.STOPPED) {





            Label lTwo=new Label();

            lTwo.setText(musicPlayer.formatTime(musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getCurrentTime(),musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getMedia().getDuration()));









            Slider slider = new Slider();

            slider.setMinSize(600, 20);

            slider.setValue((musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getCurrentTime().divide(musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getTotalDuration().divide(100))).toMillis());

            ProgressBar progressBar=new ProgressBar(0);

            progressBar.setMinWidth(600);

            StackPane stackPane=new StackPane();

            System.out.println(slider.getValue());

            Platform.runLater(new Runnable() {

                @Override

                public void run() {

                    Timeline currentAnimation = new Timeline();

                    KeyValue kv1 = new KeyValue(slider.valueProperty(), 100, Interpolator.EASE_BOTH);

                    KeyFrame kf1 = new KeyFrame(musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getTotalDuration(), kv1);

                    currentAnimation.getKeyFrames().add(kf1);

                    currentAnimation.setOnFinished(t -> {

                        // remove pane and restore scene 1

                        //root1.getChildren().setAll(rectangle1);

                        // set scene 2

                        //primaryStage.setScene(scene2);

                    });

                    currentAnimation.play();



                }

            });



            slider.valueProperty().addListener(observable -> {

                lTwo.setText(musicPlayer.formatTime(musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getCurrentTime(),musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getMedia().getDuration()));

                if(slider.isValueChanging()){

                    musicPlayer.playlist.get(musicPlayer.currentlyPlaying).seek(musicPlayer.playlist.get(musicPlayer.currentlyPlaying).getMedia().getDuration().multiply(slider.getValue()/100));



                }

                musicPlayer.slider.setValue(slider.getValue());

                progressBar.setProgress(slider.getValue()*0.01);

            });





            stackPane.getChildren().addAll(progressBar, slider);







            Label label = new Label();


            v.getChildren().addAll(label, stackPane);





            h.setMinWidth(800);







            Pane pane=musicPlayer.currentlyPlaying;





            pane.setTranslateY(-8);







            h.getChildren().addAll(pane, v, lTwo);





            v.setAlignment(Pos.CENTER);



            lTwo.setAlignment(Pos.CENTER_RIGHT);





            h.setAlignment(Pos.BOTTOM_CENTER);











            ap.getChildren().add(h);





            ap.setStyle("-fx-background-color: rgba(0,0,0,0.2); -fx-border-radius: 90px; -fx-background-radius: 90px");

            h.setStyle("-fx-border-radius: 90px; -fx-background-radius: 90px");

            DropShadow dropShadow = new DropShadow();

            dropShadow.radiusProperty().setValue(15);

            ap.setEffect(dropShadow);









            StackPane rootPane=new StackPane();





            MilkGlassPane milkGlassPane=new MilkGlassPane(base);

            milkGlassPane.setStyle("-fx-border-radius: 90px; -fx-background-radius: 90px");





            milkGlassPane.translateXProperty().bind(ap.translateXProperty());

            milkGlassPane.translateYProperty().bind(ap.translateYProperty());



            milkGlassPane.minWidthProperty().bind(ap.minWidthProperty());

            milkGlassPane.minHeightProperty().bind(ap.minHeightProperty());





            ap.setMinWidth(800);









            ap.setTranslateY(555);

            ap.setTranslateX(120);





            rootPane.getChildren().addAll(milkGlassPane, ap);













            base1.getChildren().add(rootPane);





        }











    }





    public Pane musicButtonAnimation(Media media){





        Pane pane=new Pane();





        pane.setId("1");







        pane.setMaxSize(15,15);

        pane.setMinSize(15,15);







        Line line=new Line();

        line.setStartX(0);

        line.setStartY(0);

        line.setEndX(pane.getMaxWidth());

        line.setEndY(pane.getMaxHeight()/2);





        Line line1=new Line();

        line1.setStartX(0);

        line1.setStartY(pane.getMaxHeight());

        line1.setEndX(pane.getMaxWidth());

        line1.setEndY(pane.getMaxHeight()/2);





        Line line2=new Line();

        line2.setStartX(0);

        line2.setStartY(pane.getMaxHeight()*2/4);

        line2.setEndX(0);

        line2.setEndY(pane.getMaxHeight()*2/4);







        pane.setOnMouseClicked(event -> {





            musicPlayer.Play((Pane)event.getSource());







            if(pane.getId()=="1") {





                Timeline time = new Timeline();

                KeyValue kv = new KeyValue(line.startXProperty(), pane.getMaxWidth() * 3 / 4, Interpolator.EASE_BOTH);

                KeyValue kv1 = new KeyValue(line1.startXProperty(), pane.getMaxWidth() * 1 / 4, Interpolator.EASE_BOTH);

                KeyValue kv2 = new KeyValue(line.endXProperty(), pane.getMaxWidth() * 3 / 4, Interpolator.EASE_BOTH);

                KeyValue kv3 = new KeyValue(line1.endXProperty(), pane.getMaxWidth() * 1 / 4, Interpolator.EASE_BOTH);

                KeyValue kv4 = new KeyValue(line.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyValue kv5 = new KeyValue(line1.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyValue kv6 = new KeyValue(line.endYProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv7 = new KeyValue(line1.endYProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kvFinal = new KeyValue(line2.startXProperty(), pane.getMaxWidth() * 3 / 4, Interpolator.EASE_BOTH);

                KeyValue kvFinal1 = new KeyValue(line2.endXProperty(), pane.getMaxWidth() * 3 / 4, Interpolator.EASE_BOTH);

                KeyValue kvFinal2 = new KeyValue(line2.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyValue kvFinal3 = new KeyValue(line2.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyFrame kf = new KeyFrame(Duration.seconds(0.6), kv, kv1, kv2, kv3, kv4, kv5, kv6, kv7, kvFinal, kvFinal1, kvFinal2, kvFinal3);

                time.getKeyFrames().add(kf);

                time.setOnFinished(t -> {

                });

                time.play();

                pane.setId("2");

            }else{





                Timeline time = new Timeline();

                KeyValue kv = new KeyValue(line.startXProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv1 = new KeyValue(line1.startXProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv2 = new KeyValue(line.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);

                KeyValue kv3 = new KeyValue(line1.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);

                KeyValue kv4 = new KeyValue(line.startYProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv5 = new KeyValue(line1.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyValue kv6 = new KeyValue(line.endYProperty(), pane.getMaxHeight()/2, Interpolator.EASE_BOTH);

                KeyValue kv7 = new KeyValue(line1.endYProperty(), pane.getMaxHeight()/2, Interpolator.EASE_BOTH);

                KeyValue kvFinal = new KeyValue(line2.startXProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kvFinal1 = new KeyValue(line2.endXProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kvFinal2 = new KeyValue(line2.startYProperty(), pane.getMaxHeight()*2/4, Interpolator.EASE_BOTH);

                KeyValue kvFinal3 = new KeyValue(line2.startYProperty(), pane.getMaxHeight()*2/4, Interpolator.EASE_BOTH);

                KeyFrame kf = new KeyFrame(Duration.seconds(0.6), kv, kv1, kv2, kv3, kv4, kv5, kv6, kv7, kvFinal, kvFinal1, kvFinal2, kvFinal3);

                time.getKeyFrames().add(kf);

                time.setOnFinished(t -> {

                });

                time.play();





                pane.setId("1");







            }





        });





        line.setFill(new Color(1,1,1,1));

        line1.setFill(line.getFill());

        line.setStroke(line.getFill());

        line1.setStroke(line.getFill());

        line.setStrokeWidth(4);

        line1.setStrokeWidth(4);

        line.setStrokeLineCap(StrokeLineCap.ROUND);

        line1.setStrokeLineCap(StrokeLineCap.ROUND);



        line2.setFill(line.getFill());

        line2.setStroke(line.getFill());

        line2.setStrokeWidth(4);

        line2.setStrokeLineCap(StrokeLineCap.ROUND);









































        pane.getChildren().addAll(line, line1, line2);





        pane.setStyle("-fx-background-color: null;");





        return pane;





    }





    public Pane showSongsAnimationButton(){





        Pane pane=new Pane();





        pane.setId("1");







        pane.setMaxSize(28,28);

        pane.setMinSize(28,28);







        Line line=new Line();

        line.setStartX(0-15);

        line.setStartY(0);

        line.setEndX(pane.getMaxWidth()/2);

        line.setEndY(pane.getMaxHeight());





        Line line1=new Line();

        line1.setStartX(pane.getMaxWidth()+15);

        line1.setStartY(0);

        line1.setEndX(pane.getMaxWidth()/2);

        line1.setEndY(pane.getMaxHeight());





        Arc arc=new Arc();







        arc.setStrokeWidth(4);





        arc.setCenterX(pane.getMaxWidth()/2);

        arc.setCenterY(pane.getMaxHeight()/2);

        arc.setRadiusX(pane.getMaxWidth());

        arc.setRadiusY(pane.getMaxHeight());





        arc.setStartAngle(0);





        arc.setLength(0);















        arc.setType(ArcType.OPEN);















        pane.setOnMouseClicked(event -> {





            if(pane.getId()=="1") {





                Timeline time = new Timeline();

                KeyValue kv = new KeyValue(line.startXProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv1 = new KeyValue(line1.startXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);

                KeyValue kv2 = new KeyValue(line.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);

                KeyValue kv3 = new KeyValue(line1.endXProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv4 = new KeyValue(line.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyValue kv5 = new KeyValue(line1.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyValue kv6 = new KeyValue(line.endYProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv7 = new KeyValue(line1.endYProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv8 = new KeyValue(arc.lengthProperty(), 360, Interpolator.EASE_BOTH);

                KeyFrame kf = new KeyFrame(Duration.seconds(0.49), kv, kv1, kv2, kv3, kv4, kv5, kv6, kv7, kv8);

                time.getKeyFrames().add(kf);

                time.setOnFinished(t -> {

                });

                time.play();

                pane.setId("2");



                showSongsAnimation(musicAp);





            }else{





                Timeline time = new Timeline();

                KeyValue kv = new KeyValue(line.startXProperty(), 0-15, Interpolator.EASE_BOTH);

                KeyValue kv1 = new KeyValue(line1.startXProperty(), pane.getMaxWidth()+15, Interpolator.EASE_BOTH);

                KeyValue kv2 = new KeyValue(line.endXProperty(), pane.getMaxWidth()/2, Interpolator.EASE_BOTH);

                KeyValue kv3 = new KeyValue(line1.endXProperty(), pane.getMaxWidth()/2, Interpolator.EASE_BOTH);

                KeyValue kv4 = new KeyValue(line.startYProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv5 = new KeyValue(line1.startYProperty(), 0, Interpolator.EASE_BOTH);

                KeyValue kv6 = new KeyValue(line.endYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyValue kv7 = new KeyValue(line1.endYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);

                KeyValue kv8 = new KeyValue(arc.lengthProperty(), 0, Interpolator.EASE_BOTH);

                KeyFrame kf = new KeyFrame(Duration.seconds(0.49), kv, kv1, kv2, kv3, kv4, kv5, kv6, kv7, kv8);

                time.getKeyFrames().add(kf);

                time.setOnFinished(t -> {

                });

                time.play();





                pane.setId("1");





                showSongsAnimationTwo(musicAp);









            }





        });





        line.setFill(new Color(0.8,0.4,0.6,1));

        line1.setFill(line.getFill());

        line.setStroke(line.getFill());

        line1.setStroke(line.getFill());

        line.setStrokeWidth(8);

        line1.setStrokeWidth(8);

        line.setStrokeLineCap(StrokeLineCap.ROUND);

        line1.setStrokeLineCap(StrokeLineCap.ROUND);





        arc.setFill(new Color(0,0,0,0));

        arc.setStroke(line.getFill());





        arc.setOnMouseEntered(event -> {





            arc.setFill(new Color(1,0.4,0.4,0.3));





        });





        arc.setOnMouseExited(event -> {





            arc.setFill(new Color(1,0.4,0.4,0));





        });





        pane.setOnMouseEntered(event -> {

            DropShadow dropShadow=new DropShadow();





            dropShadow.setColor(new Color(1,1,1,1));





            line.setEffect(dropShadow);

            line1.setEffect(dropShadow);





        });





        pane.setOnMouseExited(event -> {







            line.setEffect(null);

            line1.setEffect(null);





        });





























































        pane.getChildren().addAll(line, line1, arc);





        pane.setStyle("-fx-background-color: null;");





        return pane;





    }



















}