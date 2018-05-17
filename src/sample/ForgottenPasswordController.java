package sample;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.*;

import sample.DatabaseConnection.PersonDBConnection;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgottenPasswordController implements Initializable {


    @FXML

    private Label ConfirmationMessage;


    @FXML

    private TextField EmailTextfield;        // receipients email


    private String USER_NAME = "evenmoremasm";  // GMail user name (just the part before "@gmail.com")

    private String PASSWORD = "moremasm1!"; // GMail password


    private String email = "";


    String subject = "Password Recovery";


    ResultSet resultSet;


    @Override

    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML

    private void handleSendPasswordButton(ActionEvent event) throws IOException {


        email = EmailTextfield.getText();


        sendFromGMail(USER_NAME, PASSWORD, subject);

    }


    public String generateRandom() {

        Random r = new Random();


        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        final int N = alphabet.length();

        String str = "";


        for (int i = 0; i < 8; i++) {


            Character ch = alphabet.charAt(r.nextInt(N));

            str = str + ch;

        }


        return str;

    }


    private void sendFromGMail(String from, String pass, String subject) {


        String to[] = {email};


        Properties props = System.getProperties();

        String host = "smtp.gmail.com";


        props.put("mail.smtp.starttls.enable", "true");


        props.put("mail.smtp.ssl.trust", host);

        props.put("mail.smtp.user", from);

        props.put("mail.smtp.password", pass);

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);

        PersonDBConnection personDBConnection = new PersonDBConnection();

        Statement statement = null;


        try {

            String sql = "select password from login where Email = '" + email + "';";


            statement = personDBConnection.connection.createStatement();

            resultSet = statement.executeQuery(sql);


            String passwd = null;


            //Checks if there is a result

            if (resultSet.next()) {


                passwd = resultSet.getString(1);

                System.out.println(resultSet.getString(1));


                message.setFrom(new InternetAddress(from));

                InternetAddress[] toAddress = new InternetAddress[to.length];


                // To get the array of addresses

                for (int i = 0; i < to.length; i++) {

                    toAddress[i] = new InternetAddress(to[i]);

                }


                for (int i = 0; i < toAddress.length; i++) {

                    message.addRecipient(Message.RecipientType.TO, toAddress[i]);

                }


                //Change password to generated one




                String generatePw = generateRandom();

                String sql2 = "UPDATE login SET password = '" + generatePw + "' WHERE Email = '" + email + "';";

                statement.executeUpdate(sql2);
                System.out.println("GenPw:" + generatePw + " Email: " + email);
                message.setSubject(subject);

                message.setText("Your new password is " + generatePw + " Login to change it Immediately");
                Transport transport = session.getTransport("smtp");


                transport.connect(host, from, pass);

                transport.sendMessage(message, message.getAllRecipients());

                transport.close();

                System.out.println("Message sent");

                ConfirmationMessage.setText("Email with Your Password is sent to " + email);

                EmailTextfield.clear();

            } else {

                ConfirmationMessage.setText("The Email '" + email + "' Is Not Registered.");

            }

        } catch (AddressException ae) {

            ae.printStackTrace();

        } catch (MessagingException me) {

            me.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @FXML
    private void HandleReturnButton(ActionEvent event) throws IOException {

        SwitchScene RetURNbutton = new SwitchScene();
        RetURNbutton.logOut(event);

    }


}