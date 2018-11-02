package insaif.rsdm.wifinder.model.front;

import java.util.List;

public class FindInput {

    private double latitude;

    private double longitude;

    private List<HotspotInformation> hotspots;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<HotspotInformation> getHotspots() {
        return hotspots;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setHotspots(List<HotspotInformation> hotspots) {
        this.hotspots = hotspots;
    }

    public HotspotInformation getHotspot(int index)
    {
        return this.hotspots.get(index);
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
