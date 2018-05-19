package sample.DatabaseConnection;

import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Album;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadAlbumInfo {






        public ObservableList<Album> data ;
        public Album data1;
        private DbconnectionMusic dc;


        String artist;



    public ReadAlbumInfo() {
            dc = new DbconnectionMusic();






        }
        public void GetData(){
            Connection connection = (Connection) dc.connect();


            if(data!=null){
                data.clear();
            }


            data = FXCollections.observableArrayList();

            //Execute Query and store result in a rs

            try {

                ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM album");
                while (rs.next()){
                    data.add(new Album(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),setArtist(rs.getString(1))));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void GetSpecificData(String albumName){
        Connection connection = (Connection) dc.connect();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM album WHERE albumName LIKE '"+albumName+"'");
            while (rs.next()){
                data1=(new Album(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),setArtist(rs.getString(1))));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void SortByPrice(String order){
        Connection connection = (Connection) dc.connect();


        data.clear();


        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM album order by price"+order);
            while (rs.next()){
                data.add(new Album(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),setArtist(rs.getString(1))));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void sortAlphabetically(String order){
        Connection connection = (Connection) dc.connect();


        data.clear();


        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM album order by albumName"+order);
            while (rs.next()){
                data.add(new Album(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),setArtist(rs.getString(1))));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String setArtist(String albumId){


        Connection connection = (Connection) dc.connect();


        String artist1=new String();

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM albumartist WHERE album_idAlbum LIKE '"+albumId+"'");
            while (rs.next()){
                artist1=rs.getString(2);

            }

        } catch (SQLException e) {
            e.printStackTrace();


            return "null";

        }


        return artist1;










    }


    ArrayList<String> songs=new ArrayList<>();



    public ArrayList<String> GetSongs(String albumId){


        songs.clear();

        Connection connection = (Connection) dc.connect();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM song WHERE album_idAlbum LIKE '"+albumId+"'");
            while (rs.next()){
                songs.add(rs.getString(2));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return songs;

    }





}
