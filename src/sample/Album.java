package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Album implements Comparable<Album> {



    private String AlbumId;
    private String AlbumName;
    private String date;
    private String price;
    private String label;
    public String artist;
    private Image image;


    public ArrayList<String> playlist=new ArrayList<>();


    public Album(String albumId, String albumName, String date, String price, String label, String artist) {
        AlbumId = albumId;
        AlbumName = albumName;
        this.date = date;
        this.price = price;
        this.label = label;



        this.artist=artist;

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


    public Image getImage() {
        return image;
    }


    public void setImage(Image image) {
        this.image = image;
    }


    @Override
    public int compareTo(Album o) {
        return Integer.compare((int) Double.parseDouble(getPrice()), (int) Double.parseDouble(o.getPrice()));
    }
}
