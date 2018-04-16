package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class RemoveAlbumDatabase {

    private Statement st;
    private Connection connection;
    private String url = "jdbc:mysql://music-app.mysql.database.azure.com:3306/musicdb2";
    private String username = "evilBook@music-app";
    private String password = "Firmwar3";
    private String albumKey;
    private String songKey;

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

    public void removeAlbum(String albumId){

        try
        {
            String queryDelete = "DELETE FROM album WHERE album_idAlbum = " + albumId + ";";

            st = connection.createStatement();
            ResultSet rs = st.executeQuery(queryDelete);

            System.out.println("REMOVED FROM DATABASE\n");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
