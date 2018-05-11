package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.LoginController;
import sample.User.User;

import java.sql.*;

public class RetrieveInfoFromDatabase {

    //Variables
    Statement st;
    Connection connection;
    PreparedStatement ps;
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
            while (rs.next()) {
                name = rs.getString(1);
                System.out.println("FirstName: " + rs.getString(1) + " LastName: " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void authentication(String email) {


        try {
            String query = "SELECT Email FROM login WHERE Email = '" + email + "' ";
            st = connection.createStatement();
            rs = st.executeQuery(query);
            System.out.println(email + " rs thing");

            while(rs.next()) {

                if (email.equals(rs.getString(1))) {
                    System.out.println("Hallo Gavna");

                } else {

                    System.out.println("else alert");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Look, an Information Dialog");
                    alert.setContentText("I have a great message for you!");
                    alert.showAndWait();
                }

            }

        } catch(Exception e) {
            System.out.println(e);
            System.out.println("No such mail");

        }
    }
}