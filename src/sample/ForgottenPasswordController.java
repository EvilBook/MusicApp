package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.DatabaseConnection.PersonDBConnection;
import sample.Employee.Album;
import sample.Employee.AlbumNameViewClass;


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
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;


public class ForgottenPasswordController implements Initializable {

    @FXML
    private Label ConfirmationMessage;


    @FXML
    private TextField EmailTextfield;        // receipients email

    private String USER_NAME = "jjoogam";  // GMail user name (just the part before "@gmail.com")
    private String PASSWORD = "nakabiri"; // GMail password

    String from = USER_NAME;
    String pass = PASSWORD;
    String subject = "Password Recovery";


    String body = "Hello Martin ....this is freaking owesome";
    ResultSet resultSet;

    String to [] = {"jjoogam@gmail.com"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    private void handleSendPasswordButton(ActionEvent event) throws IOException {

        sendFromGMail(USER_NAME,PASSWORD,subject);







        }

    private  void sendFromGMail(String from, String pass, String subject) {




        String sql = "select password from login where Email = '"+ EmailTextfield.getText() +"';";













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

            statement = personDBConnection.connection.createStatement();
            resultSet = statement.executeQuery(sql);


            String passwd = null;
            while (resultSet.next()) {
                passwd = resultSet.getString(1);
                System.out.println(resultSet.getString(1));


            }


            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }


            message.setSubject(subject);
            message.setText(passwd);


            Transport transport = session.getTransport("smtp");


            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Message sent");
            ConfirmationMessage.setText("Email with Your Password is sent to " + EmailTextfield.getText());
            EmailTextfield.clear();

        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }




}
