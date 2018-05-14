package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class AddAlbumToDatabase {

    //Variables
    private Statement st;
    private Connection connection;
    private String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/musicdb2";
    private String username = "mass@mass-music";
    private String password = "Firmwar3";

    private String albumKey;
    private String songKey;

    public AddAlbumToDatabase () {
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

    public void getAlbumID () {
        try {
            String query = "SELECT idAlbum FROM album ORDER BY idAlbum DESC LIMIT 1;";

            st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                albumKey = rs.getString(1);
                System.out.println(albumKey);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void getSongID() {
        try {
            String query="SELECT idSong FROM song ORDER BY idSong DESC LIMIT 1;";
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                songKey = rs.getString(1);
                System.out.println(songKey);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }



    //Contains: albumName, date, albumPrice, label, vynlNumber
    public <T> void addAlbum(T albumName, T albumDate, T albumPrice, T label, T vynlNumber) throws SQLException {

        connection = DriverManager.getConnection(url, username, password);
        getAlbumID();
        System.out.println("addalbum------");


        try {
            String query = "insert into album(albumName, date, price, label, vynlNumber) "+
                    " VALUES ('"+albumName+"','"+albumDate+"','"+albumPrice+"','"+label+"','"+vynlNumber+"')";

            st = connection.createStatement();
            st.executeUpdate(query);

            System.out.println("UPDATED ALBUM\n");

        }
        catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //Contains: albumArtist
    public <T> void addAlbumArtist (T albumArtist) {
        getAlbumID();
        System.out.println("addalbumartist------");

        try {
            String query="insert into albumartist(albumArtist, album_idAlbum) "+" VALUES ('"+ albumArtist+"','"+albumKey+"')";

            st = connection.createStatement();
            st.executeUpdate(query);

            System.out.println("UPDATED ALBUMARTIST\n");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //Contains: genre
    public <T> void addGenre(T genre) {
        getAlbumID();
        System.out.println("addgenre------");

        try {
            String query = "insert into genre(genre, album_idAlbum) "+" VALUES ('"+genre+"','"+albumKey+"')";

            st = connection.createStatement();
            st.executeUpdate(query);

            System.out.println("UPDATES GENRE\n");

        }
        catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //Contains: songName, playTime
    public <T> void addSong(T songName, T playTime) {
        getAlbumID();
        System.out.println("addsong------");

        try {
            String query = "insert into song(songName, playtime, album_idAlbum) "+
                    " VALUES ('"+songName+"','"+playTime+"','"+albumKey+"')";

            st = connection.createStatement();
            st.executeUpdate(query);

            System.out.println("UPDATED SONG\n");

        }
        catch  (SQLException e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //Contains: songArtist
    public <T> void addSongArtist(T songArtist) {
        getSongID();
        System.out.println("addartist------");

        try {
            String query = "insert into songartist(songArtist,song_idSong) "+" VALUES ('"+songArtist+"','"+songKey+"')";

            st = connection.createStatement();
            st.executeUpdate(query);

            System.out.println("UPDATED SONGARTIST\n");

        }
        catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong trying to save the data in the Database", ButtonType.OK);
            alert.showAndWait();
        }
    }

}