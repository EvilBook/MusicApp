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
import sample.DatabaseConnection.UpdateDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileMenuEdit {


    private final ProfileMenu profileMenu;
    Pane base;


    StackPane stackPane;
    private Pane pane;


    HashMap<String, String> newInfo=new HashMap<>();

    public ProfileMenuEdit(ProfileMenu profileMenu) {


        this.profileMenu=profileMenu;

    }


    public StackPane createProfile(Pane pane, Pane base){


        this.pane=pane;



        MilkGlassPane milkGlassPane=new MilkGlassPane(pane);



        this.base =new Pane();


        this.base.setPrefSize(pane.getPrefWidth()*1/3, pane.getPrefHeight());



        milkGlassPane.translateYProperty().bind(this.base.translateYProperty());
        milkGlassPane.translateXProperty().bind(this.base.translateXProperty());


        stackPane=new StackPane(milkGlassPane, this.base);


        stackPane.setTranslateY(pane.getPrefHeight()+ this.base.getPrefWidth());


        stackPane.setTranslateX(pane.getPrefWidth()- this.base.getPrefWidth());



        stackPane.setOpacity(0);


        stackPane.setMaxHeight(400);







        return stackPane;















    }


    public void showProfileMenu(){





        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateYProperty(), 0+140);
        KeyValue kv1= new KeyValue(stackPane.opacityProperty(), 1);


        KeyFrame kf=new KeyFrame(Duration.seconds(0.38), kv, kv1);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void hideProfileMenu(){



        Timeline timeline=new Timeline();


        KeyValue kv= new KeyValue(stackPane.translateYProperty(), pane.getPrefHeight());
        KeyValue kv1= new KeyValue(stackPane.opacityProperty(), 0);


        KeyFrame kf=new KeyFrame(Duration.seconds(0.19), kv, kv1);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void addData(ArrayList<String> arrayList){




        Label profile=new Label("Edit");


        profile.setStyle("-fx-font-size: 28px;");



        VBox v= new VBox(profile);



        for(int i=0; i<arrayList.size()-1; i++){

            Label label = new Label("First Name:");
            TextField labe11 = new TextField();


            labe11.setStyle("-fx-background-color: null; -fx-border-width: 0px 0px 1px 0px;");


            labe11.setMaxWidth(140);







            if(i==0) {
                label.setText("First Name:");
                labe11.setText(arrayList.get(i));


                newInfo.put(label.getText(), labe11.getText());

            }else if(i==1) {
                label.setText("Last Name:");
                labe11.setText(arrayList.get(i));

                newInfo.put(label.getText(), labe11.getText());
            }else if(i==2) {
                label.setText("Birthday (for some reason):");
                labe11.setText(arrayList.get(i));

                newInfo.put(label.getText(), labe11.getText());
            }else if(i==3) {
                label.setText("Phone Number:");
                labe11.setText(arrayList.get(i));

                newInfo.put(label.getText(), labe11.getText());

            }else if(i==4) {
                label.setText("Address:");
                labe11.setText(arrayList.get(i));


                newInfo.put(label.getText(), labe11.getText());

            }else if(i==5) {
            }

            label.setStyle("-fx-text-fill: #afafaf; -fx-border-width: 0px 0px 0px 0px; -fx-border-color: #afafaf; -fx-font-size: 11px;");
            VBox v1=new VBox(label, labe11);


            v.getChildren().add(v1);


            labe11.textProperty().addListener((observable, oldValue, newValue) -> {
                newInfo.replace(label.getText(), newValue);
            });







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

        v.setPadding(new Insets(0,0,0,40));


        Button button=new Button("Save");


        button.setOnMouseClicked(event -> {


            UpdateDatabase updateDatabase=new UpdateDatabase();


            updateDatabase.ModifyUserData(newInfo, arrayList.get(5));


            hideProfileMenu();


            profileMenu.updateData();




        });




        Button button1=new Button("Cancel");


        button1.setOnMouseClicked(event -> {


            hideProfileMenu();

        });



        v.getChildren().addAll(button, button1);










    }





}
