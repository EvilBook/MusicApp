package sample.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.sql.*;


public class AddAlbumToDatabase {


    //Variables
    private String albumKey;
    private String songKey;
    private String url = "jdbc:mysql://mass-music.mysql.database.azure.com:3306/musicdb2";
    private String username = "mass@mass-music";
    private String password = "Firmwar3";
    private Statement st;
    private Connection connection;

    //Constructor that connects to music2db
    public AddAlbumToDatabase() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            st = connection.createStatement();
            System.out.println("Works");
            new UpdateDatabase().connection=connection;
        } catch(SQLException e) {
            throw new IllegalStateException("Connection failed", e);
        }
    }

    public void getAlbumID() {
        try {
            String query = "SELECT idAlbum FROM album ORDER BY idAlbum DESC LIMIT 1;";

            st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                albumKey = rs.getString(1);
                System.out.println(albumKey);
            }

        } catch(SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't save data", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void getSongID() {
        try {
            String query = "SELECT idSong FROM song ORDER BY idSong DESC LIMIT 1;";
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                songKey = rs.getString(1);
                System.out.println(songKey);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't save data", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //Contains: albumName, date, albumPrice, label, vinylNumber
    public<T> void addAlbum(T albumName, T albumDate, T albumPrice, T label, T vynlNumber) {

        getAlbumID();
        System.out.println("Adding album");


        //Add the data for album to the database
        try {
            String query = "INSERT INTO album(albumName, date, price, label, vynlNumber) "+
                           "VALUES ('" + albumName + "','" + albumDate + "','" + albumPrice +
                           "','" + label + "','" + vynlNumber + "')";

            st = connection.createStatement();
            st.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't save the data", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public <T> void addAlbumArtist(T albumArtist) {
        getAlbumID();

        try {
            String query = "INSERT INTO albumartist(albumArtist, album_idAlbum) " +
                           "VALUES('" + albumArtist + "','" + albumKey + "')";

            st = connection.createStatement();
            st.executeUpdate(query);

        } catch(SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't save the data", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public <T> void addGenre(T genre) {

        getAlbumID();

        //Add the genre to the database
        try {
            String query = "INSERT INTO genre(genre, album_idAlbum) " +
                           "VALUES ('" + genre + "','" + albumKey + "')";

            st = connection.createStatement();
            st.executeUpdate(query);

        } catch(SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't save the data", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public <T> void addSong(T songName, T playTime) {

        getAlbumID();

        //Add the song data to the databse
        try {
            String query = "INSERT INTO song(songName, playtime, album_idAlbum) " +
                           "VALUES ('" + songName + "','" + playTime+"','" + albumKey + "')";

            st = connection.createStatement();
            st.executeUpdate(query);

        } catch(SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't save the data", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public <T> void addSongArtist(T songArtist) {

        getSongID();

        try {
            String query = "INSERT INTO songartist(songArtist,song_idSong) " +
                           "VALUES ('" + songArtist + "','" + songKey + "')";

            st = connection.createStatement();
            st.executeUpdate(query);

        } catch(SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't save the data", ButtonType.OK);
            alert.showAndWait();
        }

    }

}