package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class RemoveEmployeeDatabase {

    //Variables
    private Statement st;
    private Connection connection;
    String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/persondb";
    String username = "mass@mass-music";
    String password = "Firmwar3";


    public RemoveEmployeeDatabase(){
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

    public void removeEmployee(String email) {
        String query = "DELETE FROM person WHERE Login_Email = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
            alert.showAndWait();
        }catch(Exception i) {
            i.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "second ex", ButtonType.OK);
            alert.showAndWait();
            System.out.println(connection);
            System.out.println(email);
            System.out.println(query);
        }

    }


}
