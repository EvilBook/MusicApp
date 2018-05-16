package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import sample.DatabaseConnection.RetrieveInfoFromDatabase;
import sample.DatabaseConnection.UpdateDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

    //Database
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    //Patterns
    private static Pattern emailPat;
    private static Pattern passPat;
    private static Pattern namePat;




    //Objects
    ExceptionClass exceptions = new ExceptionClass();
    MainStorage access = new MainStorage();
    RetrieveInfoFromDatabase info = new RetrieveInfoFromDatabase();

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


        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue)->{

            System.out.println(newValue);
            System.out.println(confirmPasswordField.getText());


        });


    }

    @FXML
    public void handleLoginButton(ActionEvent event) throws IOException {
        Pattern pattern = Pattern.compile("@mass");
        Matcher matcher = pattern.matcher(userNameTextField.getText());

        String email = userNameTextField.getText();
        String pass = PasswordTextField.getText();

        //check login
        info.authentication(email, pass, PasswordTextField);




        if(userNameTextField.getText().equals("admin") && PasswordTextField.getText().equals("password")) {
            access.mainAdminScreen(event);                          //Goes to the Admin screen.

        } else {
            System.out.println("first check");
            if(!matcher.find()) {
                UpdateDatabase updateDatabase = new UpdateDatabase();
                System.out.println("second check");
                if(updateDatabase.CheckLogIn(userNameTextField.getText(), PasswordTextField.getText())) {

                    userEmail = userNameTextField.getText();
                    access.mainUserScreen(event, userEmail);            //Goes to the User screen.
                    System.out.println("third check");
                }

                } else {
                System.out.println("fourth check");
                    UpdateDatabase updateDatabase = new UpdateDatabase();

                    if(updateDatabase.CheckLogIn(userNameTextField.getText(), PasswordTextField.getText())) {
                        System.out.println("5th check");
                    userEmail = userNameTextField.getText();
                    access.employeeScreen(event, userEmail);       //Goes to the Employee Screen
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
        firstNameLabel.setOpacity(0f);
        lastNameLabel.setOpacity(0f);
        emailLabel.setOpacity(0f);
        passwordLabel.setOpacity(0f);
        confirmEmailLabel.setOpacity(0f);
        confirmPasswordLabel.setOpacity(0f);


        emailPat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        passPat = Pattern.compile("[a-z0-9_-]{3,15}");
        namePat = Pattern.compile("[a-zA-Z\\s]+");


        boolean a = false;
        boolean b = false;
        boolean c = false;
        boolean d = false;


        //First Name
        if(!firstNameTextField.getText().isEmpty()) {

            if(!namePat.matcher(firstNameTextField.getText()).matches()) {
                firstNameLabel.setText("First Name contains digits");
                firstNameLabel.setOpacity(1);
            } else {
                firstNameLabel.setOpacity(0);
                a = true;
                System.out.println("this is " + a);
            }

        } else {
            firstNameLabel.setText("First Name Required");
            firstNameLabel.setOpacity(1);
        }

        //Last Name
        if(!lastNameTextField.getText().isEmpty()) {

            if(!namePat.matcher(lastNameTextField.getText()).matches()) {
                lastNameLabel.setText("Last Name Contains Digits");
                lastNameLabel.setOpacity(1);
            } else {
                lastNameLabel.setOpacity(0);
                b = true;
                System.out.println("this is " + b);
            }

        } else {
            lastNameLabel.setText("Last Name Required");
            lastNameLabel.setOpacity(1);
        }

        //Email
        if(!emailTextField.getText().isEmpty() && !confirmEmailTextField.getText().isEmpty()) {
            if(emailPat.matcher(emailTextField.getText()).matches()) {

                emailLabel.setOpacity(0);


                if(!emailTextField.getText().equals(confirmEmailTextField.getText())) {
                    emailLabel.setText("Emails Don't Match");
                    emailLabel.setOpacity(1);
                    confirmEmailLabel.setText("Emails Don't Match");
                    confirmEmailLabel.setOpacity(1);
                } else {

                    emailLabel.setOpacity(0);
                    confirmEmailLabel.setOpacity(0);
                    c = true;
                    System.out.println("this is " + c);
                }
            } else {
                emailLabel.setText("Wrong Email Format");
                emailLabel.setOpacity(1);
                confirmEmailLabel.setText("Wrong Email Format");
                confirmEmailLabel.setOpacity(1);

            }

        } else {
            if(emailTextField.getText().isEmpty()) {
                emailLabel.setText("Email Required");
                emailLabel.setOpacity(1);
            }
            if(confirmEmailTextField.getText().isEmpty()) {
                confirmEmailLabel.setText("Confirmed Email Required");
                confirmEmailLabel.setOpacity(1);
            }

        }


        // Password
        if(!confirmPasswordField.getText().isEmpty() && !passwordPasswordField.getText().isEmpty()) {
            if(passPat.matcher(passwordPasswordField.getText()).matches()) {
                passwordLabel.setOpacity(0);



                if(!passwordPasswordField.getText().equals(confirmPasswordField.getText())) {
                    passwordLabel.setText("Passwords Don't Match");
                    passwordLabel.setOpacity(1);
                    confirmPasswordLabel.setText("Passwords Don't Match");
                    confirmPasswordLabel.setOpacity(1);
                } else {

                    passwordLabel.setOpacity(0);
                    confirmPasswordLabel.setOpacity(0);
                    d = true;
                    System.out.println("this is " + d);
                }
            } else {
                passwordLabel.setText("Wrong Password Format");
                passwordLabel.setOpacity(1);
                confirmPasswordLabel.setText("Wrong Password Format");
                confirmPasswordLabel.setOpacity(1);
                //passwordLabel.setOpacity(0);
            }

        } else {
            if(passwordPasswordField.getText().isEmpty()) {
                passwordLabel.setText("Password Required");
                passwordLabel.setOpacity(1);
            }
            if(confirmPasswordField.getText().isEmpty()) {
                confirmPasswordLabel.setText("Confirmed Password Required");
                confirmPasswordLabel.setOpacity(1);
            }

        }


            if(a && b && c && d) {
                UpdateDatabase database = new UpdateDatabase();
                database.UpdateTableForUserCreation(emailTextField.getText(), passwordPasswordField.getText());

                ArrayList<String> userInfo = new ArrayList<String>();
                userInfo.add(firstNameTextField.getText());
                userInfo.add(lastNameTextField.getText());
                userInfo.add(emailTextField.getText());
                database.AddUserCreationData(userInfo);

                //Clear the fields after the signing up
                firstNameTextField.clear();
                lastNameTextField.clear();
                emailTextField.clear();
                confirmEmailTextField.clear();
                passwordPasswordField.clear();
                confirmPasswordField.clear();

                ReturnAnimation();
            }
            //}
        //}
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        Platform.exit();
    }




}
