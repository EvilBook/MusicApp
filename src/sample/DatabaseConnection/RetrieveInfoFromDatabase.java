package sample.DatabaseConnection;

import java.sql.*;

public class RetrieveInfoFromDatabase {

    //Variables
    Statement st;
    Connection connection;

    public RetrieveInfoFromDatabase() {
        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
        String username = "mass@mass-music";
        String password = "Firmwar3";

        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection=connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Connection failed", e);
        }
    }

    public String getName(String user_email){
        String name=null;
        try {
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("select person.FirstName,person.LastName from person,login where person.Login_Email LIKE '"+user_email+"'");
            while (rs.next()) {
                name=rs.getString(1);
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
            ResultSet rs = st.executeQuery(query);
            System.out.println(email + " rs thing");


            if(rs.next()) {

                if (email.equals(rs.getString("Email"))) {
                    System.out.println("Hallo Gavna");
                    System.out.println("new thing " + rs.getString("Email"));
                } else {

                    System.out.println("diff print stttmt");

                }

            } else {

                System.out.println("email doesnt exist");

            }

        } catch(Exception e) {
            System.out.println(e);
            System.out.println("generic excuse");


        }
    }
}