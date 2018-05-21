package sample.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UpdateDatabase {

    Statement st;
    Connection connection;

    public <T, A> void UpdateTableForUserCreation(T t, A a) {
        String url = "jdbc:mysql://music-app.mysql.database.azure.com:3306/persondb";
        String username = "evilBook@music-app";
        String password = "Firmwar3";


        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection=connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Connection failed", e);
        }


        try {
            String one=t.toString();
            String two=a.toString();
            String three="insert into login "+" VALUES ('"+one+"','"+two+"')";
            System.out.println(one+two);
            st=connection.createStatement();
            st.executeUpdate(three);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");
        try {
            ResultSet rs = st.executeQuery("select login.email,login.password from login");
            while (rs.next()) {
                System.out.println("email: " + rs.getString(1) + " password: " + rs.getString(2));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public <T, A> boolean CheckLogIn(T t, A a) {

        String url = "jdbc:mysql://music-app.mysql.database.azure.com:3306/persondb";
        String username = "evilBook@music-app";
        String password = "Firmwar3";


        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection=connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Connection failed", e);
        }





        String selectSQL = "SELECT USER_ID, USERNAME FROM DBUSER WHERE USER_ID = " + t;
        try {
            ResultSet rs = st.executeQuery("select email,password from login where email= '" + t.toString()+"'");
            while (rs.next()) {
                String userMail = rs.getString(1);
                String userPassword = rs.getString(2);
                if (userMail.equals(t) && userPassword.equals(a)) {
                    System.out.println("Works BIIIIIIIIIIIITCH");
                    return true;
                } else {
                    System.out.println("Illiterate fuck");
                    System.out.println(t+""+a);
                    System.out.println(userMail+""+userPassword);
                    return false;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
     /*   try {
            ResultSet rs = st.executeQuery("select employees.firstName,employees.lastName,offices.city from employees,offices where offices.officeCode like '%1%'");
            while (rs.next()) {
                System.out.println("FirstName: " + rs.getString(1) + " LastName: " + rs.getString(2) + " City Name: " + rs.getString(3));
            }*/


       /* } catch (SQLException e) {
            e.printStackTrace();
        }*/
        }
        return false;
    }
    public <T> void AddUserCreationData(T t) {
        String url = "jdbc:mysql://music-app.mysql.database.azure.com:3306/persondb";
        String username = "evilBook@music-app";
        String password = "Firmwar3";
        ArrayList<String> userData= (ArrayList<String>) t;


        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection=connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Connection failed", e);
        }


        try {
            String FirstName=userData.get(0);
            String LastName=userData.get(1);
            String UserEmial=userData.get(2);
            String three="insert into person(FirstName, LastName, Login_Email) "+" VALUES ('"+FirstName+"','"+LastName+"','"+UserEmial+"')";
            st=connection.createStatement();
            st.executeUpdate(three);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");



    }


    public <T> void ModifyUserData(T t, String email) {
        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
        String username = "mass@mass-music";
        String password = "Firmwar3";


        System.out.println(((HashMap<String, String>) t).entrySet());



        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection=connection;
        } catch (SQLException e) {

        }


        try {
            String three="UPDATE person SET FirstName='"+((HashMap<String, String>) t).get("First Name:")+"',LastName='"+((HashMap<String, String>) t).get("Last Name:")+"',DoB='"+((HashMap<String, String>) t).get("Birthday (for some reason):")+"',PhoneNumber='"+((HashMap<String, String>) t).get("Phone Number:")+"',Address='"+((HashMap<String, String>) t).get("Address:")+"' WHERE Login_Email='"+email+"'";
            st=connection.createStatement();
            st.executeUpdate(three);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");



    }




}
