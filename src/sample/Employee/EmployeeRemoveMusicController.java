package sample.Employee;

import javafx.fxml.Initializable;
import sample.DatabaseConnection.RemoveAlbumDatabase;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeRemoveMusicController implements Initializable
{


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        removeData();
    }


    public void removeData(){
        System.out.println("removing");
        RemoveAlbumDatabase rmvDatabase = new RemoveAlbumDatabase();
    }

}
