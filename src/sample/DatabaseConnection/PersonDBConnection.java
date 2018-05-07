package sample.DatabaseConnection;



import java.sql.*;



public class PersonDBConnection {



    //Variables

    Statement st;

    public Connection connection;



    public PersonDBConnection() {



        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";

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
