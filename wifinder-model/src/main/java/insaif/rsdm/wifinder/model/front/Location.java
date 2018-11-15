package insaif.rsdm.wifinder.model.front;

import javax.validation.constraints.NotNull;

public class Location {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
