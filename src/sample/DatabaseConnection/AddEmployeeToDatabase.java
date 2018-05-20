package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.MainStorage;

import java.sql.*;
import java.util.ArrayList;

public class AddEmployeeToDatabase {

    //Variables
    private Statement st;
    private Connection connection;
    private String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
    private String username = "mass@mass-music";
    private String password = "Firmwar3";

    //Objects
    MainStorage sw = new MainStorage();

    public AddEmployeeToDatabase() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection = connection;
        }
        catch (SQLException e) {
            throw new IllegalStateException("Connection failed", e);
        }
    }

    public <T, A> void UpdateTableForEmpCreation(T t, A a) {
        try {
            String one = t.toString();
            String two = a.toString();
            String three = "insert into login "+" VALUES ('" + one + "','" + two + "')";

            st = connection.createStatement();
            st.executeUpdate(three);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");
        try {
            ResultSet rs = st.executeQuery("select login.email, login.password from login");
            while (rs.next()) {
                System.out.println("email: " + rs.getString(1) + " password: " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public<T> void addEmployee(T t){
        ArrayList<String> empData= (ArrayList<String>) t;

        try {
            String firstName = empData.get(0);
            String lastName = empData.get(1);
            String birth = empData.get(2);
            String phone = empData.get(3);
            String userEmail = empData.get(4);
            String address = empData.get(5);
            String password = empData.get(6);
            System.out.println(firstName + lastName + birth + phone + userEmail + address);
            String three = "INSERT INTO login(Email, password)" +
                    "VALUES ('" + userEmail + "','" + password + "')";
            String four =  "INSERT INTO person(FirstName, LastName, DoB, PhoneNumber, Address, Login_Email)" +
                    "VALUES " + "('" + firstName + "','" + lastName + "','" + birth + "','" + phone +
                    "','" + address + "','" + userEmail +"')";
            st.executeUpdate(three);
            st.executeUpdate(four);


            //Message for complete
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added the employee to the database.", ButtonType.OK);
            alert.setHeaderText("SUCCESSFULLY ADDED");
            alert.showAndWait();

        } catch (SQLException e) {
            System.out.println(" u dumb");
            e.printStackTrace();
        }
        System.out.println("UPDATE COMPLETE\n");
    }

}