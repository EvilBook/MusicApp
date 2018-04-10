package sample.DatabaseConnection;

import java.sql.*;

public class RetrieveInfoFromDatabase {

    //Variables
    Statement st;
    Connection connection;

    public RetrieveInfoFromDatabase() {
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

}