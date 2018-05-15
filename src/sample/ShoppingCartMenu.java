package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class ShoppingCartMenu {


    Pane base;


    StackPane stackPane;
    private Pane pane;


    VBox v=new VBox();
    VBox v1=new VBox();


    ScrollPane scrollPane=new ScrollPane();




    ArrayList<Album> albums=new ArrayList<>();


    Pane pane1;




    public StackPane createShoppingCart(Pane pane, Pane pane1){


        this.pane=pane;


        this.pane1=pane1;




        MilkGlassPane milkGlassPane=new MilkGlassPane(pane);



        base=new Pane();


        base.setPrefSize(pane.getPrefWidth()*1/3, pane.getPrefHeight());


        milkGlassPane.translateYProperty().bind(base.translateYProperty());
        milkGlassPane.translateXProperty().bind(base.translateXProperty());


        stackPane=new StackPane(milkGlassPane, base);


        stackPane.setTranslateX(pane.getPrefWidth()+base.getPrefHeight());


        v.getChildren().add(scrollPane);

        scrollPane.setMinSize(base.getMinWidth(), 80);


        scrollPane.setMaxSize(base.getMinWidth(),400);



        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        Button button=new Button("Checkout");


        button.setOnMouseClicked(event -> {
            hideShoppingCartMenu();


            CheckoutMenu checkoutMenu=new CheckoutMenu();


            pane1.getChildren().add(checkoutMenu.createCheckout(pane));



            checkoutMenu.addData();


            checkoutMenu.showCheckoutMenu();



        });



        Label price=new Label("Total: 1,000,000$");


        price.setStyle("-fx-font-size: 40px;");



        v.getChildren().add(price);




        v.getChildren().add(button);


        button.setStyle("-fx-background-color: #f7d086; -fx-font-size: 14px; -fx-border-color: #ffc8a2;");


        button.setMinWidth(140);


        button.setTranslateX(base.getPrefWidth()/2-button.getMinWidth()/2);
        button.setTranslateY(14);



        button.setAlignment(Pos.CENTER);







        v.setPadding(new Insets(89,0,0,0));


        scrollPane.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #ffffff;");



        Label label=new Label("Shopping Cart");


        label.setStyle("-fx-font-size: 34px;");



        v1.setSpacing(20);






        stackPane.getChildren().add(v);


        scrollPane.setContent(v1);












        return stackPane;















    }


    public void showShoppingCartMenu(){




        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateXProperty(), pane.getPrefWidth()-base.getWidth());


        KeyFrame kf=new KeyFrame(Duration.seconds(0.4), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void hideShoppingCartMenu(){



        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateXProperty(), pane.getPrefWidth()+base.getMinWidth());


        KeyFrame kf=new KeyFrame(Duration.seconds(0.8), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void addData(){


        Label label=new Label();


        for(int i=0; i<albums.size(); i++){


            addAlbums(i);





        }



    }


    public void addAlbums(Integer i){


        HBox h=new HBox();


        ImageView imageView=new ImageView(albums.get(i).getImage());



        VBox v=new VBox();



        Label name=new Label(albums.get(i).getAlbumName());


        name.setStyle("-fx-font-size: 20px;");



        Label artist=new Label("by: "+albums.get(i).getAlbumName());


        Label price=new Label("Price: "+albums.get(i).getPrice()+"$");


        price.setTranslateX(180);



        Button newButton=new Button("X");


        newButton.setTranslateX(140);



        newButton.setOnMouseClicked(event -> {
            v1.getChildren().remove(h);
        });



        h.setMinWidth(348);


        h.getChildren().addAll(imageView, v, newButton);


        v.getChildren().addAll(name, artist, price);


        v1.getChildren().add(h);



        h.setStyle("-fx-border-width: 1px; -fx-border-color: #ffffff; -fx-background-color: rgba(0,0,0,0.48)");


        DropShadow dropShadow=new DropShadow();


        h.setEffect(dropShadow);


        imageView.setFitWidth(74);
        imageView.setPreserveRatio(true);


        v.setPadding(new Insets(0,0,0,8));


        newButton.setStyle("-fx-background-color: null; -fx-font-size: 18px; -fx-text-fill: #ff215e; -fx-border-color: null;");





























    }





}
