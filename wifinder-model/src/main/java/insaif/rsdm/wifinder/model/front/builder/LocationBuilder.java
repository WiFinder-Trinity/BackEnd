package insaif.rsdm.wifinder.model.front.builder;

import insaif.rsdm.wifinder.model.front.Location;

public class LocationBuilder {

    private Double latitude;

    private Double longitude;

    public static LocationBuilder get() { return new LocationBuilder(); }

    public LocationBuilder setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationBuilder setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Location build() {
        Location location = new Location();
        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);
        return location;
    }

    private LocationBuilder() {
        this.latitude = null;
        this.longitude = null;
    }
}
