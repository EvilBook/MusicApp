package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.MilkGlassPane;

public class DiscoverMenu {


    Pane base;


    StackPane stackPane;




    public StackPane createDiscover(Pane pane){


        MilkGlassPane milkGlassPane=new MilkGlassPane(pane);


        base=new Pane();


        base.setPrefSize(pane.getPrefWidth(), pane.getPrefHeight());


        milkGlassPane.translateYProperty().bind(base.translateYProperty());
        milkGlassPane.translateXProperty().bind(base.translateXProperty());


        stackPane=new StackPane(milkGlassPane, base);


        stackPane.setTranslateY(base.getPrefHeight()+base.getPrefHeight());


        base.setStyle("-fx-background-color: rgba(0,0,0,0.65); -fx-border-width: 2px 0px 0px 0px; -fx-border-color: #ffffff");



        return stackPane;















    }


    public void showDiscoverMenu(){


        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateYProperty(), 0+base.getPrefHeight()*1/7.8);


        KeyFrame kf=new KeyFrame(Duration.seconds(0.4), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void hideDiscoverMenu(){


        System.out.println("WORK"+base.getPrefHeight());
        System.out.println("WORK"+base.getPrefHeight());
        System.out.println("WORK"+base.getHeight());
        System.out.println("WORK"+base.getPrefHeight());



        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateYProperty(), base.getPrefHeight()+base.getPrefHeight()/2);


        KeyFrame kf=new KeyFrame(Duration.seconds(0.8), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }



}
