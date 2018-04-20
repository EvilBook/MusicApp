package sample.Employee;

public class Songs {

    private String idsong;
    private String songName;
    private String playtime;
    private String album_idalbum;

    public Songs(String idsong, String songName, String playtime, String album_idalbum) {
        this.idsong = idsong;
        this.songName = songName;
        this.playtime = playtime;
        this.album_idalbum = album_idalbum;
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

    public String getAlbum_idalbum() {
        return album_idalbum;
    }
}
