package sample.Employee;

public class Album {


    private String albumId;
    private String albumName;
    private String date;
    private String price;
    private String label;


    public Album(String albumId, String albumName, String date, String price, String label) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.date = date;
        this.price = price;
        this.label = label;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getLabel() {
        return label;
    }

}
