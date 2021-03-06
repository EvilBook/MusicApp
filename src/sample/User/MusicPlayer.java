package sample.User;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MusicPlayer {
    private static MusicPlayer musicPlayer = new MusicPlayer();
    public Label l=new Label();


    Duration duration;


    public static MusicPlayer getInstance() {
        return musicPlayer;
    }


    MediaPlayer mediaPlayer;

    ArrayList<MediaPlayer> mediaPlayers=new ArrayList<>();


    int i;


    Button currentlyPlaying;





    Slider slider;


    HashMap<Button, MediaPlayer> playlist =new HashMap<Button, MediaPlayer>();





    private MusicPlayer() {
        slider=new Slider();
    }


    public MediaPlayer.Status getStatus(){


        return mediaPlayer.getStatus();

    }


    public void setPlaylist(Media name, Button button){

        try{
            Media media = name;
            mediaPlayer=new MediaPlayer(name);
            playlist.put(button,mediaPlayer);
            mediaPlayers.add(mediaPlayer);
            duration=mediaPlayer.getMedia().getDuration();



        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void Play(Button button){
        if(currentlyPlaying!=button && currentlyPlaying!=null){
            playlist.get(currentlyPlaying).stop();



            if(playlist.containsKey(button)){
                playlist.get(button).play();
                playlist.get(button).setVolume(20);
                currentlyPlaying=button;
            }


        }
        else if(playlist.get(button).getStatus()== MediaPlayer.Status.PLAYING) {
            playlist.get(button).pause();
            Image image2=new Image(getClass().getResource("Graphics/playerIcons/ic_play_circle_outline_3x.png").toString());
            ImageView imageView3=new ImageView(image2);
            imageView3.setFitWidth(30);
            imageView3.setFitHeight(30);
            ColorAdjust colorAdjust1=new ColorAdjust();
            colorAdjust1.brightnessProperty().setValue(100);
            currentlyPlaying.setEffect(colorAdjust1);
            currentlyPlaying.setGraphic(imageView3);
        }else{
            System.out.println(playlist.containsKey(button));
            Arrays.asList(playlist);
            if(playlist.containsKey(button)){
                playlist.get(button).play();
                currentlyPlaying=button;
                Image image2=new Image(getClass().getResource("Graphics/playerIcons/ic_pause_circle_outline_3x.png").toString());
                ImageView imageView3=new ImageView(image2);
                imageView3.setFitWidth(30);
                imageView3.setFitHeight(30);
                ColorAdjust colorAdjust1=new ColorAdjust();
                colorAdjust1.brightnessProperty().setValue(100);
                currentlyPlaying.setEffect(colorAdjust1);
                currentlyPlaying.setGraphic(imageView3);
            }
        }

    }
    public void Pause(){
        mediaPlayer.pause();
    }
    public void Stop(){
        mediaPlayer.stop();
    }
    public void Next(){

    }


    public String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;

            return String.format("%02d:%02d/%02d:%02d",
                    elapsedMinutes, elapsedSeconds,durationMinutes,
                    durationSeconds);

        } else {

            return String.format("%02d:%02d",elapsedMinutes,
                    elapsedSeconds);

        }
    }



}