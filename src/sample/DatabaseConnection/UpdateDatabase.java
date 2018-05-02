package sample.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;


public class UpdateDatabase {

    Statement st;
    Connection connection;

    public <T, A> void UpdateTableForUserCreation(T t, A a) {

        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
        String username = "mass@mass-music";
        String password = "Firmwar3";


        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Connection Established");
            new UpdateDatabase().connection = connection;

        } catch (SQLException e) {
            throw new IllegalStateException("Connection failed", e);
        }


        try {
            String one = t.toString();
            String two = a.toString();
            String three = "insert into login "+" VALUES ('" + one + "','" + two + "')";

            st = connection.createStatement();
            st.executeUpdate(three);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");
        try {
            ResultSet rs = st.executeQuery("select login.email, login.password from login");
            while (rs.next()) {
                System.out.println("email: " + rs.getString(1) + " password: " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public <T, A> boolean CheckLogIn(T t, A a) {

        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
        String username = "mass@mass-music";
        String password = "Firmwar3";


        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Login Connection Established");
            new UpdateDatabase().connection=connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Login Connection failed", e);

        }


        try {
            ResultSet rs = st.executeQuery("select email, password from login where email= '" + t.toString()+"'");
            while (rs.next()) {
                String userMail = rs.getString(1);
                String userPassword = rs.getString(2);
                if (userMail.equals(t) && userPassword.equals(a)) {
                    System.out.println("Login Successful");
                    return true;
                } else {
                    System.out.println("Incorrect Password");
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public <T> void AddUserCreationData(T t) {
        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
        String username = "mass@mass-music";
        String password = "Firmwar3";
        ArrayList<String> userData= (ArrayList<String>) t;


        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Add User Connection Established");
            new UpdateDatabase().connection = connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Add User Connection failed", e);
        }


        try {
            String firstName = userData.get(0);
            String lastName = userData.get(1);
            String userEmail = userData.get(2);
            String three = "INSERT INTO person(FirstName, LastName, Login_Email)" +
                           "VALUES " + "('" + firstName + "','" + lastName + "','" + userEmail +"')";
            st.executeUpdate(three);

        } catch (SQLException e) {
            System.out.println(" u dumb");
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");

    }

}
