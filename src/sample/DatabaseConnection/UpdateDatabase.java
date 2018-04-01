package sample.DatabaseConnection;

import java.sql.*;

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

    public <T, A> void CheckLogIn(T t, A a) {

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
                } else {
                    System.out.println("Illiterate fuck");
                    System.out.println(t+""+a);
                    System.out.println(userMail+""+userPassword);
                }
                System.out.println("email: " + rs.getString(1) + " password: " + rs.getString(2));
            }


        } catch (SQLException e) {
            e.printStackTrace();
     /*   try {
            ResultSet rs = st.executeQuery("select employees.firstName,employees.lastName,offices.city from employees,offices where offices.officeCode like '%1%'");
            while (rs.next()) {
                System.out.println("FirstName: " + rs.getString(1) + " LastName: " + rs.getString(2) + " City Name: " + rs.getString(3));
            }*/


       /* } catch (SQLException e) {
            e.printStackTrace();
        }*/
        }
    }
}
