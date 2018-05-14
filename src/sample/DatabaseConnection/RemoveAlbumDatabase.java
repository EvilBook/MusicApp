package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

//Class that removes an album from the db
public class RemoveAlbumDatabase
{

    private Statement st;
    private Connection connection;
    private String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/musicdb2";
    private String username = "mass@mass-music";
    private String password = "Firmwar3";
    private String albumKey;
    private String songKey;

    //Constructor that connects to the db
    public RemoveAlbumDatabase()
    {
        try
        {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection=connection;
        }
        catch (SQLException e)
        {
            throw new IllegalStateException("Connection failed", e);
        }
    }


    //Method to remove the album with the given id
    public void removeAlbum(int id)
    {
        try
        {
            //Query that selects id album
            String searchQuery = "SELECT idalbum FROM album WHERE idalbum = " + id + ";";

            st = connection.createStatement();
            ResultSet rs = st.executeQuery(searchQuery);

            //Check if id exists
            if(rs.next())
            {
                //Remove album
                String query = "DELETE FROM album WHERE idalbum = ?";
                try (PreparedStatement ps = connection.prepareStatement(query))
                {
                    ps.setInt(1, id);
                    ps.executeUpdate();

                    //Message for complete
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully removed the selected album from the database.", ButtonType.OK);
                    alert.setHeaderText("REMOVAL COMPLETE");
                    alert.showAndWait();
                }
                catch (SQLException e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to delete the album from the database.", ButtonType.OK);
                    alert.setHeaderText("ERROR");
                    alert.showAndWait();
                }
            }
            else
            {
                //Id does not exist message
                Alert alert = new Alert(Alert.AlertType.ERROR, "The album id you filled in does not exist.", ButtonType.OK);
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
