package sample;

import br.com.moip.validators.CreditCard;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CheckoutMenu {


    private final double total;
    private final ShoppingCartMenu aClass;
    Pane base;


    StackPane stackPane;

    public CheckoutMenu(double total, ShoppingCartMenu aClass) {


        this.total=total;


        this.aClass=aClass;


    }


    public StackPane createCheckout(Pane pane){


        MilkGlassPane milkGlassPane=new MilkGlassPane(pane);


        base=new Pane();


        base.setPrefSize(800, 400);


        milkGlassPane.translateYProperty().bind(base.translateYProperty());
        milkGlassPane.translateXProperty().bind(base.translateXProperty());


        stackPane=new StackPane(milkGlassPane, base);


        stackPane.setTranslateY(base.getPrefHeight());
        stackPane.setTranslateX(base.getPrefWidth()*1/6);


        base.setStyle("-fx-background-color: rgba(0,0,0,0.59); -fx-border-width: 2px 0px 0px 0px; -fx-border-color: #ffffff; -fx-background-radius: 40px; -fx-border-radius: 40px;");


        milkGlassPane.setStyle("-fx-background-radius: 40px;");
        stackPane.setStyle("-fx-background-radius: 40px;");




        return stackPane;















    }


    public void showCheckoutMenu(){


        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateYProperty(), 0+base.getPrefHeight()*1/7.8);


        KeyFrame kf=new KeyFrame(Duration.seconds(0.4), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void hideCheckoutMenu(){


        System.out.println("WORK"+base.getPrefHeight());
        System.out.println("WORK"+base.getPrefHeight());
        System.out.println("WORK"+base.getHeight());
        System.out.println("WORK"+base.getPrefHeight());



        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateYProperty(), base.getPrefHeight()+base.getPrefHeight()/2);


        KeyFrame kf=new KeyFrame(Duration.seconds(0.2), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void addData(){




        Label profile=new Label("Checkout");


        profile.setStyle("-fx-font-size: 40px;");


        profile.setTranslateX(20);



        Button newButton=new Button("X");


        newButton.setStyle("-fx-background-color: null; -fx-font-size: 28px; -fx-text-fill: #ff215e; -fx-border-color: null;");



        newButton.setOnMouseClicked(event -> {


            hideCheckoutMenu();


        });


        Pane pane11=showCloseButton();




        HBox h1=new HBox(profile, pane11);


        pane11.setTranslateX(280);


        pane11.setTranslateY(-14);


        profile.setTranslateY(-18);




        h1.setAlignment(Pos.TOP_CENTER);



        HBox h2=new HBox();


        HBox h3=new HBox();


        HBox h4=new HBox();








        VBox v= new VBox(h1, h2);


        Button button=new Button("Pay");


        button.setStyle("-fx-background-color: #f7d086; -fx-font-size: 14px; -fx-border-color: #ffc8a2;");






        DatePicker datePicker=new DatePicker();


        VBox v1=new VBox();





            Label label = new Label("First Name:");
            TextField labe11 = new TextField();


            labe11.setMaxWidth(236);



            labe11.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        label.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");






                label.setText("Name on card:");
                labe11.setText("Name");


        Label cardNumber = new Label("Card Number:");
        TextField cardNumber1 = new TextField("1234 5678 9012 3456");


        Image image=new Image(getClass().getResource("Graphics/cards/"+"card"+".png").toString());



        ImageView imageView=new ImageView(image);


        imageView.setFitWidth(40);


        imageView.setPreserveRatio(true);




        h3.getChildren().addAll(new VBox(cardNumber, cardNumber1), imageView);




        cardNumber1.textProperty().addListener((observable, oldValue, newValue) -> {
            CreditCard creditCard=new CreditCard(cardNumber1.getText().replace(" ",""));


            if(!creditCard.isValid()){


                Image image1=new Image(getClass().getResource("Graphics/cards/"+"card"+".png").toString());


                imageView.setImage(image1);


                imageView.setFitWidth(40);




                cardNumber1.setStyle("-fx-background-color: null; -fx-text-fill: #ff585d; -fx-border-color: #ff585d; -fx-border-width: 0px 0px 1px 0px;");

            }else{


                Image image2=new Image(getClass().getResource("Graphics/cards/"+creditCard.getBrand().name().toLowerCase()+".png").toString());


                imageView.setImage(image2);


                imageView.setFitWidth(68);





                cardNumber1.setStyle("-fx-background-color: null; -fx-text-fill: #eaff9b; -fx-border-color: #d2ff93; -fx-border-width: 0px 0px 1px 0px;");



            }


            System.out.println(creditCard.isValid()+" "+creditCard.getBrand());
        });



        cardNumber1.setMaxWidth(236);



        cardNumber1.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        cardNumber.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");





        Label expirationDate = new Label("Expiration Date:");


        expirationDate.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");



        Label cvs = new Label("CVS:");
        TextField cvs1 = new TextField("000");


        cvs1.setMaxWidth(40);



        cvs1.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        cvs.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");



        h2.getChildren().addAll(new VBox(expirationDate, datePicker), new VBox(cvs, cvs1));


        h2.setSpacing(20);


        datePicker.setShowWeekNumbers(false);





        v1.getChildren().addAll(label, labe11, h3, h2);


        v1.setPadding(new Insets(4,4,4,40));


        v1.setSpacing(14);


        VBox v3=new VBox();


        Label address = new Label("Address:");
        TextField address1 = new TextField("Address");


        address1.setMaxWidth(200);



        address1.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        address.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");


        Label address2 = new Label("Address:");
        TextField address21 = new TextField("Address");


        address21.setMaxWidth(200);



        address21.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        address2.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");


        Label city = new Label("City:");
        TextField city1 = new TextField("City");


        city1.setMaxWidth(80);



        city1.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        city.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");


        Label province = new Label("Province:");
        TextField province1 = new TextField("Province");


        province1.setMaxWidth(80);



        province1.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        province.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");



        Label country = new Label("Country:");
        TextField country1 = new TextField("Country");


        country1.setMaxWidth(80);



        country1.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        country.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");


        Label code = new Label("Post Code:");
        TextField code1 = new TextField("Code");


        code1.setMaxWidth(80);



        code1.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


        code.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");



        HBox h5=new HBox(new VBox(city, city1), new VBox(province, province1));


        h5.setSpacing(14);



        HBox h6=new HBox(new VBox(country, country1), new VBox(code, code1));


        h6.setSpacing(14);


        v3.getChildren().addAll(address, address1, address2, address21, h5, h6);



        v3.setPadding(new Insets(4,4,4,200));


        v3.setSpacing(4);













        h4.getChildren().addAll(v1, v3);






            v.getChildren().add(h4);


            v.setSpacing(14);



        stackPane.getChildren().add(v);


        v.setSpacing(14);



        v.getChildren().addAll(new Label("Total: "+String.valueOf(this.total)+"$"), button);


        v.setAlignment(Pos.CENTER);



        button.setOnMouseClicked(event -> {


            aClass.albums.clear();



            Timeline timeline=new Timeline();


            KeyValue keyValue=new KeyValue(v.opacityProperty(), 0, Interpolator.EASE_BOTH);


            KeyFrame keyFrame=new KeyFrame(Duration.seconds(0.2), keyValue);


            timeline.getKeyFrames().addAll(keyFrame);


            timeline.play();


            timeline.setOnFinished(event1 -> {
                v.setDisable(true);


                Pane pane12=showSongsAnimationButton();





                Label label11=new Label("Thank you for your purchase!");


                label11.setStyle("-fx-font-size: 28px;");


                Pane pane111=showCloseButton();



                VBox vBox=new VBox(pane111, pane12, label11);


                vBox.setMinSize(base.getPrefWidth(), base.getPrefHeight());



                vBox.setAlignment(Pos.CENTER);


                label11.setTranslateY(80);



                pane111.setTranslateX(359);


                pane111.setTranslateY(-111);












                base.getChildren().addAll(vBox);


            });






        });












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


            hideCheckoutMenu();


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


    public Pane showSongsAnimationButton(){


        Pane pane=new Pane();


        pane.setId("2");



        pane.setMaxSize(80,80);
        pane.setMinSize(80,80);



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



        arc.setStrokeWidth(8);


        arc.setCenterX(pane.getMaxWidth()/2);
        arc.setCenterY(pane.getMaxHeight()/2);
        arc.setRadiusX(pane.getMaxWidth());
        arc.setRadiusY(pane.getMaxHeight());


        arc.setStartAngle(0);


        arc.setLength(0);







        arc.setType(ArcType.OPEN);







        Timeline time = new Timeline();
        KeyValue kv = new KeyValue(line.startXProperty(), pane.getMaxWidth()/2, Interpolator.EASE_BOTH);
        KeyValue kv1 = new KeyValue(line1.startXProperty(), pane.getMaxWidth()/2, Interpolator.EASE_BOTH);
        KeyValue kv2 = new KeyValue(line.endXProperty(), pane.getMaxWidth(), Interpolator.EASE_BOTH);
        KeyValue kv3 = new KeyValue(line1.endXProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv4 = new KeyValue(line.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
        KeyValue kv5 = new KeyValue(line1.startYProperty(), pane.getMaxHeight(), Interpolator.EASE_BOTH);
        KeyValue kv6 = new KeyValue(line.endYProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue kv7 = new KeyValue(line1.endYProperty(), pane.getMaxHeight()/2, Interpolator.EASE_BOTH);
        KeyValue kv8 = new KeyValue(arc.lengthProperty(), 360, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.7), kv, kv1, kv2, kv3, kv4, kv5, kv6, kv7, kv8);
        time.getKeyFrames().add(kf);
        time.setOnFinished(t -> {
        });
        time.play();


        line.setFill(new Color(0.6,0.8,0.6,1));
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


            arc.setFill(new Color(0.6,0.8,0.6,0.3));


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
