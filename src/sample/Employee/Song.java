package sample.Employee;

public class Song {

    private String idSong;
    private String songName;
    private String playtime;
    private String songArtist;


    public Song(String idSong, String songName,String songArtist,String playtime) {
        this.idSong = idSong;
        this.songName = songName;
        this.playtime = playtime;
        this.songArtist = songArtist;
    }

    public String getPlaytime() {
        return playtime;
    }

    public String getSongName() {
        return songName;
    }

    public String getIdSong() {
        return idSong;
    }

    public String getSongArtist() {
        return songArtist;
    }
}
