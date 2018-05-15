package sample.DatabaseConnection;

import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Album;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadAlbumInfo {






        public ObservableList<Album> data ;
        private DbconnectionMusic dc;

















        public ReadAlbumInfo() {
            dc = new DbconnectionMusic();






        }
        public void GetData(){
            Connection connection = (Connection) dc.connect();

            data = FXCollections.observableArrayList();

            //Execute Query and store result in a rs

            try {

                ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM album");
                while (rs.next()){
                    data.add(new Album(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void GetSpecificData(String albumName){
        Connection connection = (Connection) dc.connect();

        data = FXCollections.observableArrayList();

        //Execute Query and store result in a rs

        try {

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM album WHERE albumName LIKE '"+albumName+"'");
            while (rs.next()){
                data.add(new Album(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
