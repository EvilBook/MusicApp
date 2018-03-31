package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import sample.DatabaseConnection.ThisIsForConnecting;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class LoginController implements Initializable{


    @FXML private TextField userNameTextField;
    @FXML private PasswordField PasswordTextField;
    @FXML private AnchorPane base;
    @FXML private ImageView one;
    @FXML private MediaView loop;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i= ThreadLocalRandom.current().nextInt(1,4);
        switch(i) {
            case 1:
            one.setImage(new Image("/sample/Graphics/oneoneone.png"));
            break;
            case 2:
            one.setImage(new Image("/sample/Graphics/oneoneone2.png"));
            break;
            case 3:
            one.setImage(new Image("/sample/Graphics/oneoneone3.png"));
            break;
        }
        Media m = new Media(getClass().getResource("Graphics/loop.mp4").toString());
        MediaPlayer mediaPlayer=new MediaPlayer(m);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setMute(true);
        loop.setPreserveRatio(true);
        loop.fitWidthProperty().bind(base.maxWidthProperty());
        loop.fitHeightProperty().bind(base.maxHeightProperty());
        loop.setMediaPlayer(mediaPlayer);


    }

    @FXML
    public void handleLoginButton(ActionEvent event){





    }

    @FXML
    public void handleCreateButton(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

    }

}
