package sample.Employee;

public class Album {



    private String AlbumId;
    private String AlbumName;
    private String date;
    private String price;
    private String label;


    public Album(String albumId, String albumName, String date, String price, String label) {
        AlbumId = albumId;
        AlbumName = albumName;
        this.date = date;
        this.price = price;
        this.label = label;
    }

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
