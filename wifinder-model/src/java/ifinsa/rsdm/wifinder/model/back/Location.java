package ifinsa.rsdm.wifinder.model.back;

public class Location {

    private double latitude;

    private double longitude;

    private int strengh;

    public Location(double latitude, double longitude, int strengh) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.strengh = strengh;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getStrengh() {
        return strengh;
    }
}
