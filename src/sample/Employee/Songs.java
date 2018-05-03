package sample.Employee;

public class Songs {

    private String idsong;
    private String songName;
    private String playtime;
    private String SongArtist;

    public Songs(String idsong, String songName, String playtime, String songArtist) {
        this.idsong = idsong;
        this.songName = songName;
        this.playtime = playtime;
        SongArtist = songArtist;
    }

    public String getIdsong() {
        return idsong;
    }

    public String getSongName() {
        return songName;
    }

    public String getPlaytime() {
        return playtime;
    }

    public String getSongArtist() {
        return SongArtist;
    }
}
