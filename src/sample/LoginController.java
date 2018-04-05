package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DatabaseConnection.UpdateDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class LoginController implements Initializable{


    @FXML private TextField userNameTextField;
    @FXML private PasswordField PasswordTextField;
    @FXML private AnchorPane base;
    @FXML private ImageView one;
    @FXML private MediaView loop;
    @FXML private AnchorPane LogIn;
    @FXML private AnchorPane signUp;
    @FXML private AnchorPane signUp2;
    @FXML private ScrollPane scrollPane;
    @FXML private AnchorPane logInPane;

    @FXML
    private TextField firstNameTextField;


    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField confirmEmailTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button submitButton;

    String userEmail;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        base.setBackground(Background.EMPTY);

        signUp.setTranslateY(base.getMaxHeight()*2);
        signUp.setOpacity(0);






        Media m = new Media(getClass().getResource("Graphics/loop.mp4").toString());
        MediaPlayer mediaPlayer=new MediaPlayer(m);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(99);
        mediaPlayer.setMute(true);
        loop.setPreserveRatio(true);
        loop.fitWidthProperty().bind(base.maxWidthProperty());
        loop.fitHeightProperty().bind(base.maxHeightProperty());
        loop.setMediaPlayer(mediaPlayer);





    }

    @FXML
    public void handleLoginButton(ActionEvent event) throws IOException {

        UpdateDatabase updateDatabase=new UpdateDatabase();
        if(updateDatabase.CheckLogIn(userNameTextField.getText(),PasswordTextField.getText())){
            userEmail=userNameTextField.getText();
            Node node = (Node)event.getSource();
            Stage stage = (Stage)node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUserScreen.fxml"));
            Parent root;
            root = loader.load();
            MainUserScreenController one = loader.getController();
            one.getName(userEmail);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }





    }

    @FXML
    public void handleCreateButton(ActionEvent event) throws IOException {
        signUp.setBackground(Background.EMPTY);
        signUp2.setBackground(Background.EMPTY);
        scrollPane.setBackground(Background.EMPTY);
        Timeline time=new Timeline();
        KeyValue kv = new KeyValue(signUp.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        time.getKeyFrames().add(kf);
        time.setOnFinished(t->{
            // remove pane and restore scene 1
            //root1.getChildren().setAll(rectangle1);
            // set scene 2
            //primaryStage.setScene(scene2);
        });
        time.play();

        Timeline time2=new Timeline();
        KeyValue kv2 = new KeyValue(signUp.opacityProperty(), 1, Interpolator.EASE_BOTH);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
        time2.getKeyFrames().add(kf2);
        time2.setOnFinished(t->{
            // remove pane and restore scene 1
            //root1.getChildren().setAll(rectangle1);
            // set scene 2
            //primaryStage.setScene(scene2);
        });
        time2.play();

        Timeline time3=new Timeline();
        KeyValue kv3 = new KeyValue(logInPane.translateYProperty(), base.getMaxHeight()*2, Interpolator.EASE_BOTH);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(1), kv3);
        time3.getKeyFrames().add(kf3);
        time3.setOnFinished(t->{
            // remove pane and restore scene 1
            //root1.getChildren().setAll(rectangle1);
            // set scene 2
            //primaryStage.setScene(scene2);
        });
        time3.play();


            System.out.println("bitch");
            signUp.setTranslateY(signUp.getTranslateY()+1);
            System.out.println("bitch");


//        Node node = (Node)event.getSource();
//        Stage stage = (Stage)node.getScene().getWindow();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
//        Parent root = loader.load();
//        stage.setScene(new Scene(root));

    }
    public void handleBackButton(ActionEvent event) throws IOException {
        ReturnAnimation();


    }

    private void ReturnAnimation() {
        Timeline time=new Timeline();
        KeyValue kv = new KeyValue(signUp.translateYProperty(), base.getMaxHeight()*2, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        time.getKeyFrames().add(kf);
        time.setOnFinished(t->{
            // remove pane and restore scene 1
            //root1.getChildren().setAll(rectangle1);
            // set scene 2
            //primaryStage.setScene(scene2);
        });
        time.play();

        Timeline time3=new Timeline();
        KeyValue kv3 = new KeyValue(logInPane.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(1), kv3);
        time3.getKeyFrames().add(kf3);
        time3.setOnFinished(t->{
            // remove pane and restore scene 1
            //root1.getChildren().setAll(rectangle1);
            // set scene 2
            //primaryStage.setScene(scene2);
        });
        time3.play();
    }

    @FXML public void handleSubmitButton(ActionEvent event) throws IOException {
        UpdateDatabase database=new UpdateDatabase();
        database.UpdateTableForUserCreation(emailTextField.getText(),passwordPasswordField.getText());

        ArrayList<String> userInfo=new ArrayList<String>();
        userInfo.add(firstNameTextField.getText());
        userInfo.add(lastNameTextField.getText());
        userInfo.add(emailTextField.getText());
        database.AddUserCreationData(userInfo);

        ReturnAnimation();
    }


}
