package ifinsa.rsdm.wifinder.model.front;

import java.util.List;

public class FrontInput {

    private double latitude;

    private double longitude;

    private List<HotspotInformation> hotspots;

    public FrontInput(double latitude, double longitude, List<HotspotInformation> hotspots) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.hotspots = hotspots;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<HotspotInformation> getHotspots() {
        return hotspots;
    }
}
