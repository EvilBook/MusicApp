package sample.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class RetrieveInfoFromDatabase {
    Statement st;
    Connection connection;

    public RetrieveInfoFromDatabase() {

        String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
        String username = "mass@mass-music";
        String password = "Firmwar3";


        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            new UpdateDatabase().connection=connection;
        } catch (SQLException e) {

            e.printStackTrace();


        }
    }

    public ArrayList<String> getName(String userEmail){
        ArrayList<String> name=new ArrayList<>();
        try {
            int i=1;
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("select person.FirstName,person.LastName,person.DoB,person.PhoneNumber,person.Address,person.Login_Email from person,login where person.Login_Email LIKE '"+userEmail+"'");
            if (rs.next()) {
                System.out.println("how many");
                name.add(rs.getString(1));
                name.add(rs.getString(2));
                name.add(rs.getString(3));
                name.add(rs.getString(4));
                name.add(rs.getString(5));
                name.add(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){

        }
        return name;

    }

}
