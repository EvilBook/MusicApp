package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DatabaseConnection.UpdateDatabase;
import sample.Employee.MainEmployeeScreenController;
import sample.User.MainUserScreenController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable{

    //Variables
    @FXML private TextField userNameTextField;
    @FXML private PasswordField PasswordTextField;
    @FXML private AnchorPane base;
    @FXML private MediaView loop;
    @FXML private AnchorPane signUp;
    @FXML private AnchorPane signUp2;
    @FXML private ScrollPane scrollPane;
    @FXML private AnchorPane logInPane;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField confirmEmailTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label passwordLabel;
    @FXML private Label confirmEmailLabel;
    @FXML private Label confirmPasswordLabel;
    public String userEmail;

    private static Pattern emailPat;
    private static Pattern passPat;
    private static Pattern namePat;



    //Objects
    ExceptionClass exceptions = new ExceptionClass();
    MainStorage access = new MainStorage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        base.setBackground(Background.EMPTY);

        signUp.setTranslateY(base.getMaxHeight() * 2);
        signUp.setOpacity(0);

        Media m = new Media(getClass().getResource("Graphics/loop.mp4").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(m);
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
        Pattern pattern = Pattern.compile("@masm");
        Matcher matcher = pattern.matcher(userNameTextField.getText());


        if(userNameTextField.getText().equals("admin") && PasswordTextField.getText().equals("password")) {
            access.mainAdminScreen(event);          //Goes to the admin screen.

            } else {

        if(!matcher.find()) {
            UpdateDatabase updateDatabase = new UpdateDatabase();

            if(updateDatabase.CheckLogIn(userNameTextField.getText(), PasswordTextField.getText())) {
                userEmail = userNameTextField.getText();

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("User/mainUserScreen.fxml"));
                Parent root;
                root = loader.load();

                MainUserScreenController one = loader.getController();
                one.getName(userEmail);

                Scene scene = new Scene(root,1066.62, 600);
                stage.setScene(scene);
                stage.show();
            }

        } else {
            UpdateDatabase updateDatabase = new UpdateDatabase();

            if(updateDatabase.CheckLogIn(userNameTextField.getText(), PasswordTextField.getText())) {
                userEmail = userNameTextField.getText();

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Employee/employeeScreen.fxml"));
                Parent root;
                root = loader.load();

                MainEmployeeScreenController emp = loader.getController();
                emp.getName(userEmail);

                Scene scene = new Scene(root,1066.62, 600);
                stage.setScene(scene);
                stage.show();
            }
          }
        }
    }

    @FXML
    public void handleSignUpButton(ActionEvent event) {

        signUp.setBackground(Background.EMPTY);
        signUp2.setBackground(Background.EMPTY);
        scrollPane.setBackground(Background.EMPTY);

        Timeline time = new Timeline();
        KeyValue kv = new KeyValue(signUp.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        time.getKeyFrames().add(kf);
        time.play();

        Timeline time2 = new Timeline();
        KeyValue kv2 = new KeyValue(signUp.opacityProperty(), 1, Interpolator.EASE_BOTH);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
        time2.getKeyFrames().add(kf2);
        time2.play();

        Timeline time3=new Timeline();
        KeyValue kv3 = new KeyValue(logInPane.translateYProperty(), base.getMaxHeight()*2, Interpolator.EASE_BOTH);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(1), kv3);
        time3.getKeyFrames().add(kf3);
        time3.play();

        signUp.setTranslateY(signUp.getTranslateY() + 1);
        firstNameLabel.setOpacity(0f);
        lastNameLabel.setOpacity(0f);
        emailLabel.setOpacity(0f);
        passwordLabel.setOpacity(0f);
        confirmEmailLabel.setOpacity(0f);
        confirmPasswordLabel.setOpacity(0f);

    }

    public void handleBackButton(ActionEvent event) {
        ReturnAnimation();
    }

    private void ReturnAnimation() {
        Timeline time = new Timeline();
        KeyValue kv = new KeyValue(signUp.translateYProperty(), base.getMaxHeight()*2, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        time.getKeyFrames().add(kf);
        time.play();

        Timeline time3 = new Timeline();
        KeyValue kv3 = new KeyValue(logInPane.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(1), kv3);
        time3.getKeyFrames().add(kf3);
        time3.play();
    }

    @FXML public void handleSubmitButton(ActionEvent event) throws InterruptedException {
        emailPat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        passPat = Pattern.compile("[a-z0-9_-]{3,15}");
        namePat = Pattern.compile("[a-zA-Z\\s]+");

        //Here it's gonna check for empty fields before all
        if(firstNameTextField.getText().isEmpty()) {
            firstNameLabel.setText("First name required");
            firstNameLabel.setOpacity(1);
            wait();

        }

        if(lastNameTextField.getText().isEmpty()) {
            lastNameLabel.setText("Last name required");
            lastNameLabel.setOpacity(1);
            wait();
        }

        if (emailTextField.getText().isEmpty()) {
            emailLabel.setText("Email required");
            emailLabel.setOpacity(1);
            wait();
        }

        if (passwordPasswordField.getText().isEmpty()) {
            passwordLabel.setText("Password required");
            passwordLabel.setOpacity(1);
            wait();

        }

        //Here it's gonna check for the rest of exceptions
        else {

            if (!namePat.matcher(firstNameTextField.getText()).matches()) {
                firstNameLabel.setText("First name contains digits");
                firstNameLabel.setOpacity(1);
                wait();
            }

            if (!namePat.matcher(lastNameTextField.getText()).matches()) {
                lastNameLabel.setText("Last name contains digits");
                lastNameLabel.setOpacity(1);
                wait();
            }

            if (!emailPat.matcher(emailTextField.getText()).matches()) {
                emailLabel.setText("Incorrect email format");
                emailLabel.setOpacity(1);
                wait();
            }

            if (!passPat.matcher(passwordPasswordField.getText()).matches()) {
                passwordLabel.setText("Incorrect password format");
                passwordLabel.setOpacity(1);
                wait();
            }


            if (!passwordPasswordField.getText().equals(confirmPasswordField.getText()) || confirmPasswordLabel.getText().isEmpty()) {
                passwordLabel.setText("Passwords don't match");
                passwordLabel.setOpacity(1);
                confirmPasswordLabel.setText("Passwords don't match");
                confirmPasswordLabel.setOpacity(1);
                wait();
            }

            if (!emailTextField.getText().equals(confirmEmailTextField.getText()) || confirmEmailTextField.getText().isEmpty()) {
                emailLabel.setText("Emails don't match");
                emailLabel.setOpacity(1);
                confirmEmailLabel.setText("Emails don't match");
                confirmEmailLabel.setOpacity(1);
                wait();
            }
            else {

                UpdateDatabase database = new UpdateDatabase();
                database.UpdateTableForUserCreation(emailTextField.getText(), passwordPasswordField.getText());

                ArrayList<String> userInfo = new ArrayList<String>();
                userInfo.add(firstNameTextField.getText());
                userInfo.add(lastNameTextField.getText());
                userInfo.add(emailTextField.getText());
                database.AddUserCreationData(userInfo);

                //Clear the fields after the signing up
                /*firstNameTextField.clear();
                lastNameTextField.clear();
                emailTextField.clear();
                confirmEmailTextField.clear();
                passwordPasswordField.clear();
                confirmPasswordField.clear();*/

                ReturnAnimation();

            }
        }
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        Platform.exit();
    }
}
