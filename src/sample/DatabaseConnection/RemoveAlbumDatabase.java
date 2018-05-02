package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class RemoveAlbumDatabase {

    //Variables
    private Statement st;
    private Connection connection;
    private String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/musicdb2";
    private String username = "mass@mass-music";
    private String password = "Firmwar3";
    private String albumKey;
    private String songKey;

    public RemoveAlbumDatabase(){
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

    public void removeAlbum(int id) {

        String query = "DELETE FROM album WHERE idalbum = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
            alert.showAndWait();
        }
    }


}
