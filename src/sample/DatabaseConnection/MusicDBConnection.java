package sample.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Class to connect to the musicdb2
public class MusicDBConnection {

    //Variables
    Statement st;
    public Connection connection;

    public MusicDBConnection() {

        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/musicdb2";
        String username = "mass@mass-music";
        String password = "Firmwar3";

        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection=connection;

            System.out.println("Connection Established");
            
        } catch (SQLException e) {
            throw new IllegalStateException("Connection Failed", e);
        }

    }

}


