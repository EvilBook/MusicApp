package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileMenu {


    private final MainUserScreenController mainUserScreenController;
    Pane base;


    StackPane stackPane;
    private Pane pane;
    public Pane pane1;
    private ProfileMenuEdit profileMenuEdit;
    private ArrayList<Label> labels=new ArrayList<>();
    private String email;


    Circle circle=new Circle();


    Label name=new Label();

    public ProfileMenu(MainUserScreenController mainUserScreenController) {


        this.mainUserScreenController=mainUserScreenController;

    }


    public StackPane createProfile(Pane pane){


        this.pane=pane;



        MilkGlassPane milkGlassPane=new MilkGlassPane(pane);


        base=new Pane();


        base.setPrefSize(pane.getPrefWidth()*1/3, pane.getPrefHeight());


        milkGlassPane.translateYProperty().bind(base.translateYProperty());
        milkGlassPane.translateXProperty().bind(base.translateXProperty());


        stackPane=new StackPane(milkGlassPane, base);


        stackPane.setTranslateX(pane.getPrefWidth()+base.getPrefHeight());



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


        KeyFrame kf=new KeyFrame(Duration.seconds(0.18), kv);


        timeline.getKeyFrames().addAll(kf);


        timeline.play();





    }


    public void addData(ArrayList<String> arrayList){




        Label profile=new Label(mainUserScreenController.newLabel1.getText());


        profile.setStyle("-fx-font-size: 26px;");


        circle.setFill(mainUserScreenController.circle.getFill());


        InnerShadow innerShadow=new InnerShadow();


        innerShadow.setRadius(24);


        innerShadow.setChoke(0.34);



        circle.setEffect(innerShadow);


        circle.setRadius(38);


        Label newLabel=new Label("Log out");


        newLabel.setUnderline(true);


        newLabel.setOnMouseEntered(event -> {
            newLabel.setStyle("-fx-text-fill: #ffe6e9;");
        });

        newLabel.setOnMouseExited(event -> {
            newLabel.setStyle("-fx-text-fill: #ff5464;");
        });

        newLabel.setOnMouseClicked(event -> {

            try {

                Stage stage = (Stage) newLabel.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root;
                root = loader.load();
                Scene scene = new Scene(root, 1066.62, 600);
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                
            }
        });







        newLabel.setStyle("-fx-text-fill: #ff5464;");


        newLabel.setTranslateY(48);







        StackPane stackPane11=new StackPane(circle, profile, newLabel);


        stackPane11.setTranslateY(18);






        VBox v= new VBox(stackPane11);


        Pane pane12=showCloseButton();










        v.getChildren().addAll(pane12);


        pane12.setTranslateX(base.getPrefWidth()-pane12.getMinWidth()*1.4);


        pane12.setTranslateY(-68);


        v.setTranslateY(-48);





        for(int i=0; i<arrayList.size(); i++){

            Label label = new Label("First Name:");
            Label labe11 = new Label("Tit");


            labels.add(labe11);






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


                email=arrayList.get(i);


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


        StackPane stackPane=new StackPane();


        stackPane.setStyle("-fx-background-color: null;");



        stackPane.setMinSize(140,80);
        stackPane.setMaxSize(140,80);



        Label label12=new Label("edit");





        Line line=new Line();


        line.setStartX(0);
        line.setStartY(0);


        line.setEndX(stackPane.getMinWidth());
        line.setEndY(0);


        line.setStrokeLineCap(StrokeLineCap.ROUND);


        line.setStrokeWidth(29);


        line.setStroke(new Color(0.4,0.7,0.94,0.8));


        Line line1=new Line();


        line1.setStartX(0);
        line1.setStartY(0);


        line1.setEndX(0);
        line1.setEndY(0);


        line1.setStrokeLineCap(StrokeLineCap.ROUND);


        line1.setStrokeWidth(29);


        line1.setStroke(new Color(1,0.4,0.8,1));


        line1.setOpacity(0);



        stackPane.setOnMouseClicked(event -> {


            Timeline timeline=new Timeline();


            KeyValue kv=new KeyValue(line1.endXProperty(), stackPane.getMinWidth(), Interpolator.EASE_BOTH);


            KeyValue kv1=new KeyValue(line1.opacityProperty(), 1, Interpolator.EASE_IN);


            KeyValue kv2=new KeyValue(line1.opacityProperty(), 0, Interpolator.EASE_BOTH);



            KeyFrame kf=new KeyFrame(Duration.seconds(0.26), kv);


            KeyFrame kf1=new KeyFrame(Duration.seconds(0.14), kv1);


            KeyFrame kf2=new KeyFrame(Duration.seconds(3), kv2);



            timeline.getKeyFrames().addAll(kf, kf1, kf2);


            timeline.play();


                profileMenuEdit=new ProfileMenuEdit(this);
                pane1.getChildren().add(profileMenuEdit.createProfile(pane, base));
                profileMenuEdit.addData(arrayList);


                profileMenuEdit.showProfileMenu();










        });





        stackPane.getChildren().addAll(line, line1, label12);


        stackPane.setTranslateX(98);




        Button button=new Button("Edit");


        button.setOnMouseClicked(event -> {
            profileMenuEdit=new ProfileMenuEdit(this);
            pane1.getChildren().add(profileMenuEdit.createProfile(pane, base));
            profileMenuEdit.addData(arrayList);


            profileMenuEdit.showProfileMenu();


        });




        v.getChildren().add(stackPane);









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


            hideProfileMenu();


            if(profileMenuEdit!=null) {


                profileMenuEdit.hideProfileMenu();


            }




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


    public void updateData() {


        RetrieveInfoFromDatabase newRetrieve=new RetrieveInfoFromDatabase();
        ArrayList<String> arrayList1=new ArrayList<>(newRetrieve.getName((email)));


        for(int i=0;i<labels.size();i++){
            labels.get(i).setText(arrayList1.get(i));
        }


    }
}
