package sample.User;

import sample.Person;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class User extends Person implements Initializable{

    //Variables
    @FXML Label name;
    @FXML HBox horizontalMenu;
    @FXML Button moveLeft;
    @FXML Button moveRight;
    @FXML TextField search;
    @FXML AnchorPane base;
    Stage stage;
    AnchorPane moreAp;
    ImageView plate;
    MediaPlayer m4;
    MediaPlayer currentSong;
    Float currentSongTime;
    Timeline currentAnimation;
    AnchorPane musicAp;
    Button sliderButton;
    MediaPlayer mediaPlayerMusic;
    MediaPlayer mediaPlayer;


    //Objects
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> nameMusic = new ArrayList<>();
    ArrayList<Label> moreInfoArray = new ArrayList<>();
    Slider sliderPlayer = new Slider();

    public void SetStage(Stage stage){
        this.stage=stage;
    }


    String[] s = new String[]{"Moderat", "by: ", "Release Date: 12-12-1999", "Genre: HARD", "Price: 5$"};
    String[] sSongs = new String[]{"song1:", "song2", "song3", "song4", "song5", "song8"};
    String[] sFull = new String[]{"Moderat", "by: ", "Release Date: 12-12-1999", "Genre(s): HARD",
                                  "Label: Random","Featuring: More people","Disks: 1","Price: 5$"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReadNames();
        ReadNamesMusic();

        horizontalMenu.setSpacing(14);
        horizontalMenu.setMinWidth(400 * names.size());
        horizontalMenu.setAlignment(Pos.CENTER_LEFT);

        for(int i = 0; i < names.size(); i++) {
            StackPane stackPane = new StackPane();
            ImageView imageView = new ImageView();
            AnchorPane pane = new AnchorPane();
            Image image = new Image(getClass().getResource("Graphics/Records/"+names.get(i)).toString());

            imageView.setImage(image);
            imageView.setId("cover");
            imageView.setFitWidth(250);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            DropShadow dropShadow = new DropShadow();
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
            Timeline time = new Timeline();
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
    public ArrayList<String> ReadNamesMusic() {
        File folder = new File("C:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Music");
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
            Toast.makeText(this.stage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
        });
        m.getChildren().addAll(newButton,newButton1);
        m.setAlignment(Pos.CENTER);
        h.getChildren().add(m);
        return h;
    }
    public VBox CreateLabels(VBox h,ImageView iv,ImageView cover){
        ImageView imageView=new ImageView();
        Image image1=new Image(getClass().getResource("Graphics/ic_album_3x.png").toString());

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

        Button music=new Button();

        music.setStyle("-fx-background-color: rgba(255, 255, 255, 0);" +
                "    -fx-border-radius: 0px;\n" +
                "    -fx-border-width: 0px 18px 18px 0px;\n" +
                "    -fx-rotate: -45px;");


        music.setOnMouseClicked(event -> {
            ShowMusic();
            showSongsAnimation(musicAp);

        });


        h2.getChildren().addAll(music);
        v.getChildren().addAll(h2, v3);


        Media media=new Media(new File("C:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\musicOne.mp3").toURI().toString());
        MediaPlayer mediaPlayer=new MediaPlayer(media);
        MediaView mediaView=new MediaView(mediaPlayer);

        moreAp.getChildren().addAll(v);

        ShowMore(moreAp);


    }


    public void ShowMore(AnchorPane ap){
        System.out.println("still dont");
        ap.setStyle("-background-color: #000000");
        ap.setMinHeight(600);
        ap.setMinWidth(390);

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
        HBox h=new HBox();


        apMusic.setStyle("-background-color: #000000");
        apMusic.setMinHeight(600);
        apMusic.setMinWidth(390);

        apMusic.setTranslateX(-400);

        apMusic.getChildren().add(v);

        apMusic.setTranslateY(50);



        base.getChildren().add(apMusic);



        CreateSongs(apMusic,v);


    }

    public void CreateSongs(AnchorPane ap,VBox vOne){


        for(int i = 0; i< nameMusic.size(); i++) {

            VBox v = new VBox();
            Label label = new Label();


            label.setText(nameMusic.get(i));
            label.setStyle("-fx-text-fill: #ffffff");


            Media media = new Media(new File("C:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Music\\"+(nameMusic.get(i)).toString()).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaPlayerMusic=mediaPlayer;
            Label l=new Label();
            Label l2=new Label();

            Button play = new Button();


            Image image1=new Image(getClass().getResource("Graphics/playerIcons/ic_play_circle_outline_3x.png").toString());
            ImageView imageView2=new ImageView(image1);
            imageView2.setFitWidth(30);
            imageView2.setFitHeight(30);
            ColorAdjust colorAdjust=new ColorAdjust();
            colorAdjust.brightnessProperty().setValue(100);
            play.setEffect(colorAdjust);
            play.setGraphic(imageView2);

            play.setOnMouseClicked(event -> {
                musicPlayer.Play((Button)event.getSource());



            });

            play.setStyle("-fx-background-color: rgba(255,248,249,0); -fx-border-color: rgba(255,255,255,0);");
            play.setMinHeight(4);
            play.setMinWidth(4);

            musicPlayer.setPlaylist(media, play);




            ap.setStyle("-fx-background-color: rgba(15,15,15,0.80)");
            label.setStyle("-fx-text-fill: #ffffff");



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



            h4.getChildren().addAll(play, stackPane, lOne, lTwo);

            v.getChildren().addAll(label, mediaView, h4);

            vOne.getChildren().add(v);



        }
        musicAp=ap;

    }

    public void showSongsAnimation(AnchorPane ap) {



        Timeline time = new Timeline();
        KeyValue kv = new KeyValue(ap.translateXProperty(), 389, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), kv);
        time.getKeyFrames().add(kf);
        time.setOnFinished(t -> {
        });
        time.play();


    }
    public void showSongsAnimationTwo(AnchorPane ap){

        Timeline time = new Timeline();
        KeyValue kv = new KeyValue(ap.translateXProperty(), -800, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.06), kv);
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

            h.getChildren().addAll(musicPlayer.currentlyPlaying, v, lTwo);

            h.setMinHeight(30);

            h.setAlignment(Pos.BOTTOM_CENTER);


            ap.getChildren().add(h);


            ap.setStyle("-fx-background-color: rgba(67,61,61,0.68); -fx-border-radius: 90px; -fx-background-radius: 90px");
            h.setStyle("-fx-border-radius: 90px; -fx-background-radius: 90px");
            ap.setTranslateY(555);
            ap.setTranslateX(200);
            DropShadow dropShadow = new DropShadow();
            dropShadow.radiusProperty().setValue(15);
            ap.setEffect(dropShadow);

            base.getChildren().add(ap);

        }


    }


}
