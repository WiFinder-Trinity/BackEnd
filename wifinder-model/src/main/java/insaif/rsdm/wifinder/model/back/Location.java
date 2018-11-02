package insaif.rsdm.wifinder.model.back;

public class Location {

    private double latitude;

    private double longitude;

    private int strength;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getStrength() {
        return strength;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
