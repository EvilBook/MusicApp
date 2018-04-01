package sample.DatabaseConnection;

import javax.management.Query;
import java.sql.*;

public class ThisIsForConnecting {

    Statement st;
    public Connection connection;










    public ThisIsForConnecting() {


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


    public void searchForQuerry() {
        /*try {
            ResultSet rs = st.executeQuery("select employees.firstName,employees.lastName,offices.city from employees,offices where offices.officeCode like '%1%'");
            while (rs.next()) {
                System.out.println("FirstName: " + rs.getString(1) + " LastName: " + rs.getString(2) + " City Name: " + rs.getString(3));
            }
            UpdateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


    }
}

