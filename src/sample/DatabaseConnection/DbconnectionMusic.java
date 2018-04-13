package sample.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbconnectionMusic {

    public Connection connect(){

        try {


            String url = "jdbc:mysql://music-app.mysql.database.azure.com:3306/musicdb2";
            String username = "evilBook@music-app";
            String password = "Firmwar3";



            Connection connection = DriverManager.getConnection(url,username,password);

            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;



    }
}
