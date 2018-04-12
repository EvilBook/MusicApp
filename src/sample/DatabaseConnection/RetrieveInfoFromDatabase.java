package sample.DatabaseConnection;

import java.sql.*;

public class RetrieveInfoFromDatabase {

    //Variables
    Statement st;
    Connection connection;
    Connection connection2;





    public RetrieveInfoFromDatabase() {
        String url = "jdbc:mysql://music-app.mysql.database.azure.com:3306/musicdb2";
        String url2 = "jdbc:mysql://music-app.mysql.database.azure.com:3306/persondb";
        String username = "evilBook@music-app";
        String password = "Firmwar3";

        try {
            connection = DriverManager.getConnection(url, username, password);
            connection2 = DriverManager.getConnection(url2,username,password);
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
            st = connection2
                    .createStatement();
            ResultSet rs = st.executeQuery("select person.FirstName,person.LastName from person,login where person.Login_Email LIKE '" + user_email + "'");
            while (rs.next()) {
                name = rs.getString(1);
                System.out.println("FirstName: " + rs.getString(1) + " LastName: " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void getAlbumTable() {

        try {
            st = connection.createStatement();
            String sql = "SELECT * FROM album";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {


                System.out.println(" idAlbum: " + rs.getString(1) + " albumName: " + rs.getString(2)+ " date: "+rs.getString(3)+
                " price: "+rs.getString(4)+" label: "+rs.getString(5)+" VynlNumber: "+rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


