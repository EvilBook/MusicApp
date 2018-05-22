package sample;

import javafx.animation.Interpolator;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShoppingCartMenu {


    private final MainUserScreenController count;
    Pane base;


    StackPane stackPane;
    private Pane pane;


    VBox v=new VBox();
    VBox v1=new VBox();


    ScrollPane scrollPane=new ScrollPane();




    ArrayList<Album> albums=new ArrayList<>();


    Pane pane1;
    private double total=0;
    private Label price=new Label();

    public ShoppingCartMenu(MainUserScreenController count) {


        this.count=count;

    }


    public StackPane createShoppingCart(Pane pane, Pane pane1){


        this.pane=pane;


        this.pane1=pane1;




        MilkGlassPane milkGlassPane=new MilkGlassPane(pane);



        base=new Pane();


        base.setPrefSize(pane.getPrefWidth()*1/3, pane.getPrefHeight());



        base.setStyle("-fx-background-color: rgba(0,0,0,0.33);");




        milkGlassPane.translateYProperty().bind(base.translateYProperty());
        milkGlassPane.translateXProperty().bind(base.translateXProperty());


        stackPane=new StackPane(milkGlassPane, base);


        stackPane.setTranslateX(pane.getPrefWidth()+base.getPrefHeight());


        Pane pane12=showCloseButton();



        v.getChildren().addAll(pane12);


        pane12.setTranslateX(base.getPrefWidth()-pane12.getMinWidth()*1.4);


        pane12.setTranslateY(-68);


        stackPane.getChildren().add(v);

















        return stackPane;















    }


    public void showShoppingCartMenu(){


        v1.getChildren().clear();



        if(albums.size()>0) {

            v.getChildren().addAll(scrollPane);


            scrollPane.setMinSize(base.getMinWidth(), 80);


            scrollPane.setMaxSize(base.getMinWidth(), 400);


            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


            Button button = new Button("Checkout");


            button.setOnMouseClicked(event -> {
                hideShoppingCartMenu();


                CheckoutMenu checkoutMenu = new CheckoutMenu(total, this);


                pane1.getChildren().add(checkoutMenu.createCheckout(pane));


                checkoutMenu.addData();


                checkoutMenu.showCheckoutMenu();


            });


            price.setStyle("-fx-font-size: 28px;");


            v.getChildren().add(price);


            v.getChildren().add(button);


            button.setStyle("-fx-background-color: #f7d086; -fx-font-size: 14px; -fx-border-color: #ffc8a2;");


            button.setMinWidth(140);


            button.setTranslateX(base.getPrefWidth() / 2 - button.getMinWidth() / 2);
            button.setTranslateY(14);


            button.setAlignment(Pos.CENTER);


            v.setPadding(new Insets(89, 0, 0, 0));


            scrollPane.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #ffffff;");


            Label label = new Label("Shopping Cart");


            label.setStyle("-fx-font-size: 34px;");


            v1.setSpacing(20);


        }else{


            Label empty=new Label("Your cart is empty!");


            v.getChildren().add(empty);


        }


        scrollPane.setContent(v1);


        DecimalFormat df=new DecimalFormat(".##");




        for(Album a:albums){
            total+=Double.parseDouble(a.getPrice());
            System.out.println(a.getPrice()+" "+df.format(Double.parseDouble(a.getPrice()))+" "+total);
        }


        price.setText(df.format(total)+"$");






        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateXProperty(), pane.getPrefWidth()-base.getWidth());


        KeyFrame kf=new KeyFrame(Duration.seconds(0.4), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void hideShoppingCartMenu(){



        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateXProperty(), pane.getPrefWidth()+base.getMinWidth());


        KeyFrame kf=new KeyFrame(Duration.seconds(0.18), kv);


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


        Label price=new Label("price: ");
        price.setStyle("-fx-text-fill: #a6a6a6; -fx-font-size: 14px;");
        Label price1=new Label(albums.get(i).getPrice()+"$");


        price.setTranslateX(180);



        Button newButton=new Button("X");


        newButton.setTranslateX(111);



        newButton.setOnMouseClicked(event -> {


            Timeline timeline=new Timeline();

            KeyValue kv=new KeyValue(h.opacityProperty(), 0, Interpolator.EASE_BOTH);


            KeyValue kv1=new KeyValue(h.translateXProperty(), -200, Interpolator.EASE_BOTH);


            KeyFrame kf=new KeyFrame(Duration.seconds(0.4), kv, kv1);


            timeline.getKeyFrames().addAll(kf);


            timeline.play();


            timeline.setOnFinished(event1 -> {


                v1.getChildren().remove(h);


                Integer count1=Integer.parseInt(count.count.getText());




                count.count.setText(String.valueOf(count1-1));



            });

        });



        h.setMinWidth(330);


        h.getChildren().addAll(imageView, v, newButton);


        v.getChildren().addAll(name, artist, new HBox(price, price1));


        v1.getChildren().add(h);



        h.setStyle("-fx-border-width: 1px; -fx-border-color: #ffffff; -fx-background-color: rgba(0,0,0,0.48)");


        DropShadow dropShadow=new DropShadow();

        DirectoryChooser c=new DirectoryChooser();


        File f=c.showDialog(v1.getScene().getWindow());


        c.showDialog(v1.getScene().getWindow()).getPath();









        h.setEffect(dropShadow);


        imageView.setFitWidth(74);
        imageView.setPreserveRatio(true);


        v.setPadding(new Insets(0,0,0,8));


        newButton.setStyle("-fx-background-color: null; -fx-font-size: 18px; -fx-text-fill: #ff215e; -fx-border-color: null;");





























    }


    public Pane showCloseButton(){


        Pane pane=new Pane();


        pane.setId("1");



        pane.setMaxSize(28,28);
        pane.setMinSize(28,28);



        Line line=new Line();
        line.setStartX(0);
        line.setStartY(pane.getMaxHeight());
        line.setEndX(0);
        line.setEndY(pane.getMaxHeight());


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
        KeyValue kv1 = new KeyValue(line1.startXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
        KeyValue kv2 = new KeyValue(line.endXProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv3 = new KeyValue(line1.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
        KeyValue kv4 = new KeyValue(line.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
        KeyValue kv5 = new KeyValue(line1.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
        KeyValue kv6 = new KeyValue(line.endYProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv7 = new KeyValue(line1.endYProperty(),  0, Interpolator.EASE_BOTH);
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


            hideShoppingCartMenu();


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






}
