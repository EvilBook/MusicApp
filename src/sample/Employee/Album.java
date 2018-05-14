package sample.Employee;

public class Album {

    //Variables
    private String AlbumId;
    private String AlbumName;
    private String date;
    private String price;
    private String label;

    //Constructor
    public Album(String albumId, String albumName, String date, String price, String label) {
        AlbumId = albumId;
        AlbumName = albumName;
        this.date = date;
        this.price = price;
        this.label = label;
    }

    //Getters
    public String getAlbumId() {
        return AlbumId;
    }

    public String getAlbumName() {
        return AlbumName;
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
