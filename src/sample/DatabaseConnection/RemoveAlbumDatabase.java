package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

//Class that removes an album from the db
public class RemoveAlbumDatabase {

    private Statement st;
    private Connection connection;
    private String url = "jdbc:mysql://music-app.mysql.database.azure.com:3306/musicdb2";
    private String username = "evilBook@music-app";
    private String password = "Firmwar3";
    private String albumKey;
    private String songKey;

    //Constructor that connects to the db
    public RemoveAlbumDatabase(){
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
    public void removeAlbum(int id) {

        String query = "DELETE FROM album WHERE idalbum = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }


}
