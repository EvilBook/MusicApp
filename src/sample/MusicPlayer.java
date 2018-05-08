package sample;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    public String title;


    public static MusicPlayer getInstance() {
        return musicPlayer;
    }


    MediaPlayer mediaPlayer;

    ArrayList<MediaPlayer> mediaPlayers=new ArrayList<>();


    int i;


    Pane currentlyPlaying;





    Slider slider;


    HashMap<Pane, MediaPlayer> playlist =new HashMap<Pane, MediaPlayer>();





    private MusicPlayer() {
        slider=new Slider();
    }


    public MediaPlayer.Status getStatus(){


        return mediaPlayer.getStatus();

    }


    public void setPlaylist(Media name, Pane pane){

        try{
            Media media = name;
            mediaPlayer=new MediaPlayer(name);
            playlist.put(pane,mediaPlayer);
            mediaPlayers.add(mediaPlayer);
            duration=mediaPlayer.getMedia().getDuration();



        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void Play(Pane pane){
        if(currentlyPlaying!=pane && currentlyPlaying!=null){
            playlist.get(currentlyPlaying).stop();



            if(playlist.containsKey(pane)){
                playlist.get(pane).play();
                playlist.get(pane).setVolume(20);
                currentlyPlaying=pane;
            }


        }
        else if(playlist.get(pane).getStatus()== MediaPlayer.Status.PLAYING) {
            playlist.get(pane).pause();

        }else{
            playlist.get(pane).play();


            currentlyPlaying=pane;

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


    public String getTitle() {





        return "Song Name";

    }
}
