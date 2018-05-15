package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class ProfileMenuEdit {


    Pane base;


    StackPane stackPane;
    private Pane pane;


    public StackPane createProfile(Pane pane){


        this.pane=pane;



        MilkGlassPane milkGlassPane=new MilkGlassPane(pane);



        base=new Pane();


        base.setPrefSize(pane.getPrefWidth()*1/3, pane.getPrefHeight());


        milkGlassPane.translateYProperty().bind(base.translateYProperty());
        milkGlassPane.translateXProperty().bind(base.translateXProperty());


        stackPane=new StackPane(milkGlassPane, base);


        stackPane.setTranslateX(pane.getPrefWidth()-base.getPrefWidth());


        System.out.println(stackPane.getTranslateX());




        return stackPane;















    }


    public void showProfileMenu(){





        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateXProperty(), pane.getPrefWidth()-base.getWidth());


        KeyFrame kf=new KeyFrame(Duration.seconds(0.4), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void hideProfileMenu(){



        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateXProperty(), pane.getPrefWidth()+base.getMinWidth());


        KeyFrame kf=new KeyFrame(Duration.seconds(0.8), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void addData(ArrayList<String> arrayList){




        Label profile=new Label("Profile");


        profile.setStyle("-fx-font-size: 40px;");



        VBox v= new VBox(profile);



        for(int i=0; i<arrayList.size(); i++){

            Label label = new Label("First Name:");
            TextField labe11 = new TextField();


            labe11.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


            labe11.setMaxWidth(300);






            if(i==0) {
                label.setText("First Name:");
                labe11.setText(arrayList.get(i));
            }else if(i==1) {
                label.setText("Last Name:");
                labe11.setText(arrayList.get(i));
            }else if(i==2) {
                label.setText("Birthday (for some reason):");
                labe11.setText(arrayList.get(i));
            }else if(i==3) {
                label.setText("Phone Number:");
                labe11.setText(arrayList.get(i));
            }else if(i==4) {
                label.setText("Address:");
                labe11.setText(arrayList.get(i));
            }else if(i==5) {
                label.setText("email:");
                labe11.setText(arrayList.get(i));
            }

            label.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");
            VBox v1=new VBox(label, labe11);


            v.getChildren().add(v1);






        }




        Label label=new Label("First Name:");
        label.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");
        Label labe11=new Label("Tit");
        VBox v1=new VBox(label, labe11);

        Label label2=new Label("Last Name:");
        label2.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");
        Label labe112=new Label("Fucker");
        VBox v2=new VBox(label2, labe112);


        Label label3=new Label("email:");
        label3.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");
        Label labe113=new Label("not@cool.bruh");
        VBox v3=new VBox(label3, labe113);


        Label label4=new Label("Address:");
        label4.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");
        Label labe114=new Label("whore alley 14");
        VBox v4=new VBox(label4, labe114);


        stackPane.getChildren().add(v);


        stackPane.setStyle("-fx-background-color: rgba(0,0,0,0.35);");


        v.setSpacing(14);

        v.setPadding(new Insets(pane.getPrefHeight()*1/5,0,0,0));


        Button button=new Button("Save");



        Button button1=new Button("Cancel");


        v.getChildren().addAll(button, button1);










    }





}
