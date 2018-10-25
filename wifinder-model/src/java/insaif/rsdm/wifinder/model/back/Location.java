package insaif.rsdm.wifinder.model.back;

public class Location {

    private double latitude;

    private double longitude;

    private int strengh;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getStrengh() {
        return strengh;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setStrengh(int strengh) {
        this.strengh = strengh;
    }
}
