package sample.DatabaseConnection;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.sql.*;

public class RetrieveInfoFromDatabase {

    //Variables
    Statement st;
    Connection connection;
    ResultSet rs;

    public RetrieveInfoFromDatabase() {
        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
        String username = "mass@mass-music";
        String password = "Firmwar3";

        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection = connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Connection failed", e);
        }
    }

    public String getName(String user_email) {
        String name = null;

        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT person.FirstName, person.LastName " +
                    "                            FROM person, login " +
                    "                            WHERE person.Login_Email " +
                    "                            LIKE '" + user_email + "'");
            while(rs.next()) {
                name = rs.getString(1);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    public void authentication(String email, String pass, PasswordField passwordTextField, TextField usernameTextField) {

        try {
            String equery = "SELECT Email FROM login WHERE Email = '" + email + "' ";
            String pquery = "SELECT password FROM login WHERE (password = '" + pass + " ') && (Email = '" + email + "');";
            st = connection.createStatement();
            rs = st.executeQuery(equery);


            if(rs.next()) {

                if (email.equals(rs.getString("Email"))) {

                    System.out.println("Logging in");

                } else {

                    System.out.println("Weird error");

                }

            } else {

                System.out.println("Email doesn't exist");
                usernameTextField.setStyle("-fx-background-color: rgba(255,21,33,0.67); -fx-border-color: #ff1038;");

                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(usernameTextField.translateXProperty(), usernameTextField.getTranslateX() + 5, Interpolator.EASE_BOTH);
                KeyValue keyValue1 = new KeyValue(usernameTextField.translateXProperty(), usernameTextField.getTranslateX() - 5, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), keyValue, keyValue1);

                timeline.getKeyFrames().addAll(keyFrame);
                timeline.setCycleCount(15);
                timeline.play();
                timeline.setOnFinished(event -> { usernameTextField.setStyle(""); usernameTextField.setTranslateX(0); });

            }

            rs = st.executeQuery(pquery);

            if(rs.next()) {

                System.out.println("correct password");

            } else {
                System.out.println("Incorrect password");

                passwordTextField.setStyle("-fx-background-color: rgba(255,21,33,0.67); -fx-border-color: #ff1038;");

                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(passwordTextField.translateXProperty(), passwordTextField.getTranslateX()+5, Interpolator.EASE_BOTH);
                KeyValue keyValue1 = new KeyValue(passwordTextField.translateXProperty(), passwordTextField.getTranslateX()-5, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), keyValue, keyValue1);

                timeline.getKeyFrames().addAll(keyFrame);
                timeline.setCycleCount(15);
                timeline.play();
                timeline.setOnFinished(event -> { passwordTextField.setStyle(""); passwordTextField.setTranslateX(0); });
            }

        } catch(Exception e) {
            System.out.println(e);
            System.out.println("generic excuse");
        }
    }
}