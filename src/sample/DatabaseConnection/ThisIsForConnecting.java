package sample.DatabaseConnection;

import javax.management.Query;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ThisIsForConnecting {

    Statement st;










    public ThisIsForConnecting() {


        String url = "jdbc:mysql://music-app.mysql.database.azure.com:3306/persondb";
        String username = "evilBook@music-app";
        String password = "Firmwar3";


        try {
            java.sql.Connection connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Connection Established");
            searchForQuery();
        } catch (SQLException e) {
            throw new IllegalStateException("Connection Failed", e);
        }


    }


    public void searchForQuery() {
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

    public void UpdateTable() {


        try {
            st.executeUpdate("insert  into  login" + "VaLUES ('Jjoogam@gmail.com','Mary1234')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");
        try {
            ResultSet rs = st.executeQuery("select employees.firstName,employees.lastName,offices.city from employees,offices where offices.officeCode like '%1%'");
            while (rs.next()) {
                System.out.println("FirstName: " + rs.getString(1) + " LastName: " + rs.getString(2) + " City Name: " + rs.getString(3));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void AddInTable() {


        try {
            st.executeUpdate("insert  into  login"+"VaLUES ('Jjoogam@gmail.com','Mary1234')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");
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

