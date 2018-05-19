package sample;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;
import sample.DatabaseConnection.DbconnectionMusic;
import sample.DatabaseConnection.ReadAlbumInfo;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
    @FXML
    StackPane profilePane;

    Stage stage;
    private MusicPlayer musicPlayer=MusicPlayer.getInstance();
    private boolean musicState;
    public String userEmail;
    public ArrayList<String> arrayList;
    public Label count;
    public Circle circle;
    public Label newLabel1;
    private ArrayList<Album> albums;


    ArrayList<String> albumNames=new ArrayList<>();


    public void SetStage(Stage stage){
        this.stage=stage;

    }


    public void setUserEmail(String userEmail){
        this.userEmail=userEmail;
        getName();
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


    DiscoverMenu discoverMenu= new DiscoverMenu();


    ShoppingCartMenu shoppingCartMenu = new ShoppingCartMenu(this);


    ProfileMenu profileMenu=new ProfileMenu(this);



    ReadAlbumInfo readAlbumInfo;


    final static Image image12=new Image(("File:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Graphics\\cover.png").toString());



























    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainMenu();
        ReadNames();






        SearchBar();



        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Trending",
                        "A-Z",
                        "Z-A",
                        "Price: High to Low",
                        "Price: Low to High"
                );
        final ComboBox comboBox = new ComboBox(options);


        base.getChildren().add(comboBox);


        comboBox.setTranslateY(40);
        comboBox.setTranslateX(18);


        comboBox.setValue("Trending");


        comboBox.valueProperty().addListener(observable -> {


            if(comboBox.getValue()=="A-Z"){
                readAlbumInfo.sortAlphabetically(" asc");



                sortPrice(readAlbumInfo.data);
            }else if(comboBox.getValue()=="Z-A"){
                readAlbumInfo.sortAlphabetically(" desc");



                sortPrice(readAlbumInfo.data);
            }else if(comboBox.getValue()=="Price: Low to High"){
                readAlbumInfo.SortByPrice(" asc");



                sortPrice(readAlbumInfo.data);
            }else if(comboBox.getValue()=="Price: High to Low"){
                readAlbumInfo.SortByPrice(" desc");



                sortPrice(readAlbumInfo.data);
            }

        });







        horizontalMenu.setSpacing(14);
        horizontalMenu.setMinWidth(400*names.size());
        horizontalMenu.setAlignment(Pos.CENTER_LEFT);



        albums=new ArrayList<Album>();

        for(int i=0;i<readAlbumInfo.data.size();i++) {
            StackPane stackPane=new StackPane();
            StackPane stackPane11=new StackPane();
            ImageView imageView=new ImageView();
            AnchorPane pane=new AnchorPane();
            Album album=readAlbumInfo.data.get(i);


            albums.add(album);

            Image image=new Image(getClass().getResource("Graphics/Records/"+album.getAlbumName()+".jpg").toString());


            album.setImage(image);


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
            VBox albumInfo1=new VBox();


            albumInfo1.setAlignment(Pos.BASELINE_CENTER);



            stackPane11.getChildren().addAll(albumInfo1, stackPane);

            Label name=new Label(album.getAlbumName().replace("_"," "));


            Label artist=new Label(album.artist);


            albumInfo1.getChildren().addAll(name, artist);



            stackPane.getChildren().addAll(imageView,imageView2,CreateLabels(albumInfo,imageView2,imageView, i, readAlbumInfo.data.get(i)));
            stackPane.setMaxWidth(250);
            stackPane.setMaxHeight(250);


            albumInfo1.setMaxSize(254, 300);


            albumInfo1.setPrefWidth(254);




            name.setTranslateY(0);


            artist.setTranslateY(albumInfo1.getMaxHeight()-48);



            albumInfo1.setStyle("-fx-background-color: rgba(0,0,0,0.42); -fx-background-radius: 20px;");




            horizontalMenu.getChildren().add(stackPane11);


            Image image11=new Image(getClass().getResource("Graphics/view.png").toString());


            ImageView imageView1=new ImageView(image11);





            imageView1.setPreserveRatio(false);


            imageView1.setFitHeight(1);


            stackPane.getChildren().addAll(imageView1);


            imageView1.setVisible(false);


            int finalI = i;
            imageView1.setOnMouseClicked(event -> {
                CreateMore(readAlbumInfo.data.get(finalI));
            });




            stackPane.setOnMouseEntered(event -> {
                AnimationOne(stackPane, imageView1);

            });
            stackPane.setOnMouseExited(event -> {
                AnimationTwo(stackPane, imageView1);

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



        readAlbumInfo= new ReadAlbumInfo();
        //readAlbumInfo.GetSpecificData("sonicX");
        readAlbumInfo.GetData();

        AnchorPane mainMenu=new AnchorPane();
        mainMenu.setPrefSize(base.getPrefWidth(), base.getPrefHeight());
        mainMenu.setStyle("-fx-background: null");


        HBox h1=new HBox();


        h1.setAlignment(Pos.CENTER);




        base1.getChildren().add(h1);


        h1.setMinSize(200,40+14);
        h1.setMaxSize(200,40+14);



        h1.setTranslateX(base1.getPrefWidth()-h1.getMinWidth()*1.59);
        h1.setTranslateY(14);










        StackPane rootPane=new StackPane();


        MilkGlassPane milkGlassPane=new MilkGlassPane(base);


        milkGlassPane.setPrefSize(base.getPrefWidth(),base.getPrefHeight());


        rootPane.getChildren().addAll(milkGlassPane, mainMenu);


        VBox v=new VBox();


        HBox h=new HBox();












        v.getChildren().addAll(h);



        mainMenu.getChildren().addAll(v);


        v.setAlignment(Pos.CENTER);


        h.setAlignment(Pos.CENTER);












       //base1.getChildren().add(rootPane);


        AnchorPane ap1=new AnchorPane();


        base1.getChildren().add(discoverMenu.createDiscover(base));





        base1.getChildren().add(shoppingCartMenu.createShoppingCart(base, base1));




        base1.getChildren().add(profileMenu.createProfile(base));


        profileMenu.pane1=base1;






        mainMenuIcons(h1);













    }

    public void getName() {
        RetrieveInfoFromDatabase newRetrieve=new RetrieveInfoFromDatabase();
        arrayList=new ArrayList<>(newRetrieve.getName((userEmail)));
        if(arrayList.size()>0) {
            name.setText(arrayList.get(0).substring(0,1).toUpperCase()+""+(arrayList.get(1).substring(0,1).toUpperCase()));
            name.setOpacity(0);
            circle=new Circle();
            circle.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));


            circle.setRadius(24);


            DropShadow newDropShadow=new DropShadow();


            newDropShadow.setColor(new Color(Math.random(), Math.random(), Math.random(), 1));


            newDropShadow.setRadius(14);


            InnerShadow innerShadow=new InnerShadow();


            innerShadow.setRadius(9);


            innerShadow.setChoke(0.3);








            circle.setEffect(innerShadow);




            newLabel1=new Label();


            newLabel1.setText(name.getText());


            StackPane newStackPane1=new StackPane(circle, newLabel1);


            newStackPane1.setOnMouseEntered(event -> {
                DropShadow newDropShadow11=new DropShadow();


                newDropShadow11.setColor(new Color(1,1,1,1));


                newDropShadow11.setRadius(20);



                newStackPane1.setEffect(newDropShadow11);


            });


            newStackPane1.setOnMouseExited(event -> {
                newStackPane1.setEffect(null);
            });


            newStackPane1.setOnMouseClicked(event -> {
                profileMenu.showProfileMenu();
            });


            profileMenu.addData(arrayList);






            newStackPane1.setTranslateY(14);
            newStackPane1.setTranslateX(base.getPrefWidth()-80);



            base.getChildren().add(newStackPane1);




        }else{
        }
    }
    public ArrayList<String> ReadNames() {
        File folder = new File("C:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Graphics\\Records");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                names.add(listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
            }
        }
        return names;

    }
    public ArrayList<String> ReadNamesMusic(Album album11) {
        File folder = new File("C:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Music");
        File[] listOfFiles = folder.listFiles();


        ArrayList<String> songs=new ArrayList<>(readAlbumInfo.GetSongs(album11.getAlbumId()));


        for (int i = 0; i < songs.size(); i++) {
            nameMusic.add(songs.get(i));
        }
        return nameMusic;

    }
    public void AnimationOne(StackPane s, ImageView imageView1){


        imageView1.setVisible(true);





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


            time.setOnFinished(event -> {


                Timeline timeline=new Timeline();
                KeyValue kv11=new KeyValue(imageView1.fitHeightProperty(), 90, Interpolator.EASE_BOTH);
                KeyFrame kf11=new KeyFrame(Duration.seconds(0.24), kv11);
                timeline.getKeyFrames().addAll(kf11);
                timeline.play();

            });

        }
    }
    public void AnimationTwo(StackPane s, ImageView imageView1){

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


            time.setOnFinished(event -> {


                Timeline timeline=new Timeline();
                KeyValue kv11=new KeyValue(imageView1.fitHeightProperty(), 1, Interpolator.EASE_BOTH);
                KeyFrame kf11=new KeyFrame(Duration.seconds(0.17), kv11);
                timeline.getKeyFrames().addAll(kf11);
                timeline.play();


                timeline.setOnFinished(event1 -> {
                    imageView1.setVisible(false);
                });


            });


        }
    }
    public VBox SetText(VBox h,Image image,Integer ii, Album album){
        ArrayList<Label> n=new ArrayList<>();




        HBox m=new HBox();
        Button newButton=new Button();
        newButton.setText("More");
        newButton.setAlignment(Pos.BOTTOM_CENTER);
        newButton.setOnMouseClicked(event -> {
            CreateMore(readAlbumInfo.data.get(ii));
        });
        newButton.opacityProperty().bind(h.opacityProperty());
        newButton.visibleProperty().bind(h.visibleProperty());
        Button newButton1=new Button();
        newButton1.setText("Buy");
        newButton1.setAlignment(Pos.BOTTOM_CENTER);
        newButton1.opacityProperty().bind(h.opacityProperty());
        newButton1.visibleProperty().bind(h.visibleProperty());
        newButton1.setOnMouseClicked(event -> {


            Integer count1=Integer.parseInt(count.getText());




            count.setText(String.valueOf(count1+1));



            shoppingCartMenu.albums.add(readAlbumInfo.data.get(ii));

            String toastMsg = "added to basket";
            int toastMsgTime = 300;
            int fadeInTime = 500;
            int fadeOutTime= 500;
            Toast.makeText(this.stage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
        });
        return h;
    }
    public VBox CreateLabels(VBox h,ImageView iv,ImageView cover,Integer ii,Album album){
        ImageView imageView=new ImageView();
        Image image1=new Image(getClass().getResource("Graphics/addToCart.png").toString());

        imageView.setImage(image1);
        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);


        Label name=new Label("title: ");
        name.setStyle("-fx-text-fill: #b3b3b3; -fx-font-size: 14px");
        Label name1=new Label(album.getAlbumName().replace("_", " "));



        Label artist=new Label("by: ");
        artist.setStyle("-fx-text-fill: #b3b3b3; -fx-font-size: 14px");
        Label artist1=new Label(album.artist);


        Label plates=new Label("no. disks: ");
        plates.setStyle("-fx-text-fill: #b3b3b3; -fx-font-size: 14px");
        Label plates1=new Label(album.getAlbumId());


        Label date=new Label("date: ");
        date.setStyle("-fx-text-fill: #b3b3b3; -fx-font-size: 14px");
        Label date1=new Label(album.getDate());


        Label price=new Label("price: ");
        price.setStyle("-fx-text-fill: #b3b3b3; -fx-font-size: 14px");
        Label price1=new Label(album.getPrice());


        VBox v=new VBox(new HBox(name, name1), new HBox(artist, artist1), new HBox(date, date1), new HBox(plates, plates1), new HBox(price, price1));


        v.setSpacing(0);


        v.setTranslateY(-78);





        h.getChildren().addAll(v);



        v.setOnMouseClicked(event -> {
        });








        h.getChildren().add(createShoppingCartButton(imageView, readAlbumInfo.data.get(ii)));



        h.setAlignment(Pos.CENTER);
        h.setOpacity(0);
        h.opacityProperty().bind(iv.opacityProperty());
        h.visibleProperty().bind(iv.visibleProperty());






        return SetText(h,cover.getImage(),ii,readAlbumInfo.data.get(ii));
    }

    public void CreateMore(Album album){


        ReadNamesMusic(album);





        if(moreAp!=null){
            showMoreAnimationTwo(moreAp);
        }




        moreAp=new AnchorPane();
        VBox v=new VBox();
        HBox h=new HBox();
        HBox h1=new HBox();
        HBox h2=new HBox();








        ImageView iv=new ImageView();

        Button close=new Button();

        Button buy=new Button();


        StackPane stackPane=new StackPane();


        iv.setImage(album.getImage());
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


        Pane pane= showCloseButton();





        close.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ff1919; -fx-border-radius: 8px; -fx-border-color: #ff1919; -fx-border-width:2px;");
        close.setText("X");
        close.setOnMouseEntered(event -> {
            close.setStyle("-fx-background-color: #ff1919; -fx-text-fill: #fdfffd; -fx-border-radius: 8px; -fx-border-color: #fdfffd; -fx-border-width:2px;");

        });
        close.setOnMouseExited(event -> {
            close.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-text-fill: #ff1919; -fx-border-radius: 8px; -fx-border-color: #ff1919; -fx-border-width:2px;");

        });


        h1.setMinWidth(moreAp.getWidth());




        h1.getChildren().addAll(stackPane, pane);

        pane.setTranslateX(80+24);
        pane.setTranslateY(8);





        h1.minWidthProperty().bind(v.minWidthProperty());


        v.getChildren().add(h1);

        VBox v3=new VBox();

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








        Media media=new Media(new File("C:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\musicOne.mp3").toURI().toString());
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

        ap.setMinHeight(600);
        ap.setMinWidth(390);

        ap.setTranslateX(-400);
        for(Node n : ap.getChildren()){
            n.setOpacity(100);
            n.setStyle("-fx-background-color: rgba(0,0,0,0.59)");
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
        KeyValue kv1 = new KeyValue(plate.translateXProperty(), 180, Interpolator.EASE_BOTH);
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
        KeyFrame kf = new KeyFrame(Duration.seconds(0.49), kv);
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


                Media media = new Media(new File("C:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Music\\"+(nameMusic.get(i)).toString()).toURI().toString()+".mp3");
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
                });


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
            label.setText(musicPlayer.getTitle());
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




            ap.setMinWidth(800);




            ap.setTranslateY(555);
            ap.setTranslateX(120);


            ap.setEffect(new DropShadow());










            base1.getChildren().add(ap);


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


    public Pane showCloseButton(){


        Pane pane=new Pane();


        pane.setId("1");



        pane.setMaxSize(28,28);
        pane.setMinSize(28,28);



        Line line=new Line();
        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(0);
        line.setEndY(0);


        Line line1=new Line();
        line1.setStartX(pane.getMaxWidth());
        line1.setStartY(pane.getMaxHeight());
        line1.setEndX(pane.getMaxWidth());
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


        Timeline time = new Timeline();
        KeyValue kv = new KeyValue(line.startXProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv1 = new KeyValue(line1.startXProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv2 = new KeyValue(line.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
        KeyValue kv3 = new KeyValue(line1.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
        KeyValue kv4 = new KeyValue(line.startYProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv5 = new KeyValue(line1.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
        KeyValue kv6 = new KeyValue(line.endYProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv7 = new KeyValue(line1.endYProperty(),  pane.getMaxHeight(), Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.49), kv, kv1, kv2, kv3, kv4, kv5, kv6, kv7);
        time.getKeyFrames().add(kf);
        time.setOnFinished(t -> {
        });
        time.play();
        pane.setId("2");


        time.setOnFinished(event1 -> {


                    Timeline time1 = new Timeline();
                    KeyValue kv11 = new KeyValue(line.startXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
                    KeyValue kv111 = new KeyValue(line1.startXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
                    KeyValue kv211 = new KeyValue(line.endXProperty(), 0, Interpolator.EASE_BOTH);
                    KeyValue kv31 = new KeyValue(line1.endXProperty(), 0, Interpolator.EASE_BOTH);
                    KeyValue kv41 = new KeyValue(line.startYProperty(), 0, Interpolator.EASE_BOTH);
                    KeyValue kv51 = new KeyValue(line1.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
                    KeyValue kv61 = new KeyValue(line.endYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
                    KeyValue kv71 = new KeyValue(line1.endYProperty(), 0, Interpolator.EASE_BOTH);
                    KeyFrame kf1 = new KeyFrame(Duration.seconds(0.49), kv11, kv111, kv211, kv31, kv41, kv51, kv61, kv71);
                    time1.getKeyFrames().add(kf1);
                    time1.setOnFinished(t -> {
                    });
                    time1.play();


                });














        pane.setOnMouseClicked(event -> {


                Timeline time11 = new Timeline();
                KeyValue kv1111 = new KeyValue(line.startXProperty(), 0, Interpolator.EASE_BOTH);
                KeyValue kv11111 = new KeyValue(line1.startXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
                KeyValue kv21 = new KeyValue(line.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
                KeyValue kv311 = new KeyValue(line1.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
                KeyValue kv411 = new KeyValue(line.startYProperty(), 0, Interpolator.EASE_BOTH);
                KeyValue kv511 = new KeyValue(line1.startYProperty(), 0, Interpolator.EASE_BOTH);
                KeyValue kv611 = new KeyValue(line.endYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
                KeyValue kv711 = new KeyValue(line1.endYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
                KeyValue kv8 = new KeyValue(arc.lengthProperty(), 0, Interpolator.EASE_BOTH);
                KeyFrame kf11 = new KeyFrame(Duration.seconds(0.49), kv1111, kv11111, kv21, kv311, kv411, kv511, kv611, kv711, kv8);
                time11.getKeyFrames().add(kf11);
                time11.setOnFinished(t -> {
                });
                time11.play();


                pane.setId("1");


                showMoreAnimationTwo(moreAp);
                showSongsAnimationTwo(musicAp);
                musicPlayer.slider.setValue(sliderPlayer.getValue());
                showPlayer();


        });


        line.setFill(new Color(0.8,0.1,0.1,1));
        line1.setFill(line.getFill());
        line.setStroke(line.getFill());
        line1.setStroke(line.getFill());
        line.setStrokeWidth(4);
        line1.setStrokeWidth(4);
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


    public void mainMenuIcons(Pane pane){


        ImageView baseImageView=new ImageView();


        baseImageView.setPreserveRatio(true);

        DropShadow dropShadow=new DropShadow();


        dropShadow.setColor(new Color(1,1,1,1));


        ColorAdjust colorAdjust=new ColorAdjust();


        colorAdjust.setBrightness(1);


        dropShadow.setRadius(40);




        dropShadow.setInput(colorAdjust);


        ColorAdjust colorAdjust1=new ColorAdjust();


        colorAdjust1.setBrightness(-0.39);


        ArrayList<ImageView> icons=new ArrayList<>();


        Rectangle rect = new Rectangle(0, 0, 100, 100);
        Tooltip t = new Tooltip("Search");
        Tooltip t1 = new Tooltip("Discover");
        Tooltip t2 = new Tooltip("Profile");
        Tooltip t3 = new Tooltip("Saved");














        ImageView imageView=new ImageView();
        ImageView imageView1=new ImageView();
        ImageView imageView2=new ImageView(("File:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Graphics\\cart.png"));
        ImageView imageView3=new ImageView(("File:\\Users\\NoFox\\Downloads\\MusicApp\\src\\sample\\Graphics\\ic_favorite_border_white_48pt_3x.png"));


        Label label=new Label("Search");
        Label label1=new Label("Discover");
        Label label2=new Label("Profile");
        Label label3=new Label("Saved");


        label.setStyle("-fx-text-fill: #ffffff;");
        label1.setStyle("-fx-text-fill: #ffffff;");
        label2.setStyle("-fx-text-fill: #ffffff;");
        label3.setStyle("-fx-text-fill: #ffffff;");


        label.setMaxSize(pane.getMinWidth()*1/8, pane.getMinHeight()*1/8);
        label1.setMaxSize(pane.getMinWidth()*1/8, pane.getMinHeight()*1/8);
        label2.setMaxSize(pane.getMinWidth()*1/8, pane.getMinHeight()*1/8);
        label3.setMaxSize(pane.getMinWidth()*1/8, pane.getMinHeight()*1/8);




        imageView.setFitWidth(baseImageView.getFitWidth());
        imageView1.setFitWidth(baseImageView.getFitWidth());
        imageView2.setFitWidth(baseImageView.getFitWidth());
        imageView3.setFitWidth(baseImageView.getFitWidth());


        imageView.setPreserveRatio(true);
        imageView1.setPreserveRatio(true);
        imageView2.setPreserveRatio(true);
        imageView3.setPreserveRatio(true);


        VBox hBox=new VBox();
        VBox hBox1=new VBox();
        VBox hBox2=new VBox();
        VBox hBox3=new VBox();


        hBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);



        hBox.getChildren().addAll(imageView);
        hBox1.getChildren().addAll(imageView1);
        hBox2.getChildren().addAll(imageView2);
        hBox3.getChildren().addAll(imageView3);




        icons.add(imageView);
        icons.add(imageView1);
        icons.add(imageView2);
        icons.add(imageView3);


        imageView.setEffect(colorAdjust1);
        imageView1.setEffect(colorAdjust1);
        imageView2.setEffect(colorAdjust1);
        imageView3.setEffect(colorAdjust1);


        VBox[] v1=new VBox[]{hBox, hBox1, hBox2, hBox3};


        hBox.setOnMouseClicked(event -> {
            shoppingCartMenu.showShoppingCartMenu();


            shoppingCartMenu.addData();
        });

        hBox1.setOnMouseClicked(event -> {
        });


        hBox2.setOnMouseClicked(event -> {
            shoppingCartMenu.showShoppingCartMenu();


            shoppingCartMenu.addData();
        });


        hBox3.setOnMouseClicked(event -> {
            shoppingCartMenu.showShoppingCartMenu();


            shoppingCartMenu.addData();
        });









        for(VBox i : v1){


            i.setMaxSize(40+14, 40+14);


            ImageView imageView11=(ImageView)i.getChildren().get(0);


            imageView11.setFitWidth(40+14);






            /*i.setOnMouseEntered(event -> {
                if(i.getId()!="2") {
                    i.setMinSize(pane.getMinWidth()*1/7, pane.getMinWidth()*1/7);
                    i.setEffect(dropShadow);
                }
            });


            i.setOnMouseExited(event -> {
                if(i.getId()!="2") {
                    i.setMinSize(pane.getMinWidth()*1/8, pane.getMinWidth()*1/8);
                    i.setEffect(colorAdjust1);
                }
            });


            i.setOnMouseClicked(event -> {
                i.setMinSize(pane.getMinWidth()*1/6, pane.getMinWidth()*1/6);
                System.out.println(i.getChildren().size());


                i.setId("2");



                for(VBox ii : v1){
                    if(ii!=i){
                        ii.setEffect(colorAdjust1);
                        i.setMinSize(pane.getMinWidth()*1/8, pane.getMinWidth()*1/8);
                        ii.setId("1");


                    }
                }

            });*/



        }


        StackPane stackPane11=new StackPane();


        stackPane11.getChildren().add(hBox2);




        Circle circle=new Circle();


        circle.setRadius(9);


        circle.setFill(new Color(0.98,0.4,0.4,1));


        circle.setTranslateY(24);


        circle.setTranslateX(18);




        count=new Label("0");



        count.setTranslateY(24);


        count.setTranslateX(18);



        stackPane11.getChildren().addAll(circle, count);








        pane.getChildren().addAll(hBox, hBox1, stackPane11, hBox3);


        ((HBox)pane).setSpacing(4);








        Tooltip.install(hBox,t);
        Tooltip.install(hBox1,t1);
        Tooltip.install(hBox2,t2);
        Tooltip.install(hBox3,t3);


        pane.toFront();
















    }


    public void sortPrice(ObservableList<Album> data) {


            horizontalMenu.getChildren().clear();




            for(int i=0; i<data.size(); i++) {



                Image image=new Image(getClass().getResource("Graphics/Records/"+data.get(i).getAlbumName()+".jpg").toString());



                StackPane stackPane = new StackPane();
                StackPane stackPane11 = new StackPane();
                ImageView imageView = new ImageView(image);
                AnchorPane pane = new AnchorPane();

                imageView.setImage(image);
                imageView.setId("cover");
                imageView.setFitWidth(250);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);
                DropShadow dropShadow = new DropShadow();
                dropShadow.radiusProperty().setValue(15);
                imageView.setEffect(dropShadow);

                ImageView imageView2 = new ImageView();
                Image image1 = new Image(getClass().getResource("Graphics/tinyBlackSquare.png").toString());
                imageView2.setImage(image1);
                imageView2.setFitWidth(250);
                imageView2.setPreserveRatio(true);
                imageView2.setVisible(false);
                imageView2.setId("informationPane");


                VBox albumInfo = new VBox();
                VBox albumInfo1 = new VBox();


                albumInfo1.setAlignment(Pos.BASELINE_CENTER);


                stackPane11.getChildren().addAll(albumInfo1, stackPane);


                pane.setStyle("-fx-background-color: null;");

                Label name = new Label(data.get(i).getAlbumName().replace("_", " "));


                Label artist = new Label(data.get(i).artist);


                albumInfo1.getChildren().addAll(name, artist);


                stackPane.getChildren().addAll(imageView, imageView2, CreateLabels(albumInfo, imageView2, imageView, i, data.get(i)));
                stackPane.setMaxWidth(250);
                stackPane.setMaxHeight(250);


                albumInfo1.setMaxSize(254, 300);


                albumInfo1.setPrefWidth(254);


                name.setTranslateY(0);


                artist.setTranslateY(albumInfo1.getMaxHeight() - 48);


                albumInfo1.setStyle("-fx-background-color: rgba(0,0,0,0.42); -fx-background-radius: 20px;");


                Image image11=new Image(getClass().getResource("Graphics/view.png").toString());


                ImageView imageView1=new ImageView(image11);





                imageView1.setPreserveRatio(false);


                imageView1.setFitHeight(1);


                stackPane.getChildren().addAll(imageView1);


                imageView1.setVisible(false);


                int finalI = i;
                imageView1.setOnMouseClicked(event -> {
                    CreateMore(readAlbumInfo.data.get(finalI));
                });



                stackPane.setOnMouseEntered(event -> {
                    AnimationOne(stackPane, imageView1);

                });
                stackPane.setOnMouseExited(event -> {
                    AnimationTwo(stackPane, imageView1);

                });



                horizontalMenu.getChildren().add(stackPane11);




            }



        }


    private Node createShoppingCartButton(ImageView imageView, Album album) {


        StackPane stackPane=new StackPane();


        stackPane.setStyle("-fx-background-color: null;");



        stackPane.setMinSize(140,80);
        stackPane.setMaxSize(140,80);



        ImageView iv=imageView;


        iv.setFitWidth(24);



        Label label=new Label("Add to cart");


        HBox h=new HBox(iv, label);


        h.setSpacing(4);



        h.setTranslateX(18);


        h.setTranslateY(30);





        Line line=new Line();


        line.setStartX(0);
        line.setStartY(0);


        line.setEndX(stackPane.getMinWidth());
        line.setEndY(0);


        line.setStrokeLineCap(StrokeLineCap.ROUND);


        line.setStrokeWidth(40);


        line.setStroke(new Color(0.4,0.2,0.8,0.8));


        Line line1=new Line();


        line1.setStartX(0);
        line1.setStartY(0);


        line1.setEndX(0);
        line1.setEndY(0);


        line1.setStrokeLineCap(StrokeLineCap.ROUND);


        line1.setStrokeWidth(40);


        line1.setStroke(new Color(1,0.8,0.4,1));


        line1.setOpacity(0);



        stackPane.setOnMouseClicked(event -> {


            Timeline timeline=new Timeline();


            KeyValue kv=new KeyValue(line1.endXProperty(), stackPane.getMinWidth(), Interpolator.EASE_BOTH);


            KeyValue kv1=new KeyValue(line1.opacityProperty(), 1, Interpolator.EASE_IN);


            KeyFrame kf=new KeyFrame(Duration.seconds(0.3), kv);


            KeyFrame kf1=new KeyFrame(Duration.seconds(0.12), kv1);


            timeline.getKeyFrames().addAll(kf, kf1);


            timeline.play();


            label.setText("Added to cart");


            Integer count1=Integer.parseInt(count.getText());




            count.setText(String.valueOf(count1+1));



            shoppingCartMenu.albums.add(album);









        });





        stackPane.getChildren().addAll(line, line1, h);


        stackPane.setTranslateY(80);



        return stackPane;















    }


    public void SearchBar(){
        DbconnectionMusic dbconnectionMusic=new DbconnectionMusic();


        dbconnectionMusic.connect();



        String string=new String();


        string="select * from album";


        ResultSet resultSet= null;
        ResultSet resultSet1= null;


        search.textProperty().addListener(observable -> {
            searhBarResults();
        });


        try {
            resultSet = dbconnectionMusic.connect().createStatement().executeQuery(string);
            search.textProperty().addListener(observable -> {

            });
            resultSet1 = dbconnectionMusic.connect().createStatement().executeQuery("select * from album where albumName like '%"+search.getText()+"%'");

            while(resultSet.next()){
                albumNames.add(new String(resultSet.getString(2)));
                TextFields.bindAutoCompletion(search, albumNames);
            }


            while(resultSet1.next()){
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


    public void searhBarResults() {


        horizontalMenu.getChildren().clear();


        ReadAlbumInfo readAlbumInfo1=new ReadAlbumInfo();


        DbconnectionMusic dbconnectionMusic=new DbconnectionMusic();


        dbconnectionMusic.connect();

        ResultSet resultSet= null;
        ResultSet resultSet1= null;


        ArrayList<String> searchBarResults=new ArrayList<>();



        try {
            resultSet1 = dbconnectionMusic.connect().createStatement().executeQuery("select * from album where albumName like '%"+search.getText()+"%'");


            while(resultSet1.next()){
                searchBarResults.add(resultSet1.getString(2));
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }





        for(int i=0; i<searchBarResults.size(); i++) {




            readAlbumInfo1 = new ReadAlbumInfo();
            //readAlbumInfo.GetSpecificData("sonicX");
            readAlbumInfo1.GetSpecificData(searchBarResults.get(i));


            Image image=new Image(getClass().getResource("Graphics/Records/"+searchBarResults.get(i)+".jpg").toString());



                StackPane stackPane = new StackPane();
                StackPane stackPane11 = new StackPane();
                ImageView imageView = new ImageView(image);
                AnchorPane pane = new AnchorPane();

                imageView.setImage(image);
                imageView.setId("cover");
                imageView.setFitWidth(250);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);
                DropShadow dropShadow = new DropShadow();
                dropShadow.radiusProperty().setValue(15);
                imageView.setEffect(dropShadow);

                ImageView imageView2 = new ImageView();
                Image image1 = new Image(getClass().getResource("Graphics/tinyBlackSquare.png").toString());
                imageView2.setImage(image1);
                imageView2.setFitWidth(250);
                imageView2.setPreserveRatio(true);
                imageView2.setVisible(false);
                imageView2.setId("informationPane");


                VBox albumInfo = new VBox();
                VBox albumInfo1 = new VBox();


                albumInfo1.setAlignment(Pos.BASELINE_CENTER);


                stackPane11.getChildren().addAll(albumInfo1, stackPane);


                pane.setStyle("-fx-background-color: null;");

                Label name = new Label(readAlbumInfo1.data1.getAlbumName().replace("_", " "));


                Label artist = new Label(readAlbumInfo1.data1.artist);


                albumInfo1.getChildren().addAll(name, artist);


                stackPane.getChildren().addAll(imageView, imageView2, CreateLabels(albumInfo, imageView2, imageView, i, readAlbumInfo1.data1));
                stackPane.setMaxWidth(250);
                stackPane.setMaxHeight(250);


                albumInfo1.setMaxSize(254, 300);


                albumInfo1.setPrefWidth(254);


                name.setTranslateY(0);


                artist.setTranslateY(albumInfo1.getMaxHeight() - 48);


                albumInfo1.setStyle("-fx-background-color: rgba(0,0,0,0.42); -fx-background-radius: 20px;");


            Image image11=new Image(getClass().getResource("Graphics/view.png").toString());


            ImageView imageView1=new ImageView(image11);





            imageView1.setPreserveRatio(false);


            imageView1.setFitHeight(1);


            stackPane.getChildren().addAll(imageView1);


            imageView1.setVisible(false);


            int finalI = i;
            imageView1.setOnMouseClicked(event -> {
                CreateMore(readAlbumInfo.data.get(finalI));
            });



            stackPane.setOnMouseEntered(event -> {
                AnimationOne(stackPane, imageView1);

            });
            stackPane.setOnMouseExited(event -> {
                AnimationTwo(stackPane, imageView1);

            });



                horizontalMenu.getChildren().add(stackPane11);




        }



    }

















}
