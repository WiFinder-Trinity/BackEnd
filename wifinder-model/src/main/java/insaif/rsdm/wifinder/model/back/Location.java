package insaif.rsdm.wifinder.model.back;

import javax.persistence.Embeddable;

@Embeddable
public class Location {

    private Double latitude;

    private Double longitude;

    private Integer strength;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public int getStrength() {
        return strength;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
