package insaif.rsdm.wifinder.model.front;

import java.util.ArrayList;
import java.util.List;

public class FrontInput {

    private double latitude;

    private double longitude;

    private List<HotspotInformation> hotspots;

    public FrontInput() {
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.hotspots = new ArrayList<HotspotInformation>();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean addHotspot(HotspotInformation hotspot)
    {
        return this.hotspots.add(hotspot);
    }

    public boolean removeHotspot(HotspotInformation hotspot)
    {
        return this.hotspots.remove(hotspot);
    }

    public void removeHotspot(int index)
    {
        this.hotspots.remove(index);
    }
}
