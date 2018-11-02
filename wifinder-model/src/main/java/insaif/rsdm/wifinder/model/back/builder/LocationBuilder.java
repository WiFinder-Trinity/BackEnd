package insaif.rsdm.wifinder.model.back.builder;

import insaif.rsdm.wifinder.model.back.Location;

public class LocationBuilder {
    private double latitude;
    private double longitude;
    private int strength;

    public static LocationBuilder get() {
        return new LocationBuilder();
    }

    public LocationBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public Location build() {
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setStrength(strength);

        return location;
    }
}
