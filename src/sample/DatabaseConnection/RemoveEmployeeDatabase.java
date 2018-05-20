package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.xml.transform.Result;
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
        try {
            String searchQuery = "SELECT * FROM login WHERE email = '" + email + "';";

            st = connection.createStatement();
            ResultSet rs = st.executeQuery(searchQuery);

            if(rs.next()) {

                try {
                    String query = "DELETE FROM person WHERE Login_Email ='" + email + "';";
                    st = connection.createStatement();
                    st.executeUpdate(query);
                    System.out.println("Deleted from Person table");

                    String query2 = "DELETE FROM login WHERE Email ='" + email + "';";
                    st.executeUpdate(query2);
                    System.out.println("Deleted from Login table");

                    //Message for complete
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully removed the selected user from the database.", ButtonType.OK);
                    alert.setHeaderText("REMOVAL COMPLETE");
                    alert.showAndWait();

                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to delete the data in the Database", ButtonType.OK);
                    alert.showAndWait();
                }

            }
            else
            {
                //Id does not exist message
                Alert alert = new Alert(Alert.AlertType.ERROR, "The email you filled in does not exist.", ButtonType.OK);
                alert.setHeaderText("ERROR");
                alert.showAndWait();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}


