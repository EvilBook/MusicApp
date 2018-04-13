package sample.Employee;

public class Album {

  private String AlbumName;
  private String date;
  private String price;
  private String label;
  private String ViynlNumber;

    public Album(String albumName, String date, String price, String label) {
        AlbumName = albumName;
        this.date = date;
        this.price = price;
        this.label = label;

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
