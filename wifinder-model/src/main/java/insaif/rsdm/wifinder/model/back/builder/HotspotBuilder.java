package insaif.rsdm.wifinder.model.back.builder;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.Location;

import java.util.List;

public class HotspotBuilder {

    private String bssid = null;
    private String ssid = null;
    private int connectionCount = 0;
    private Location computedLocation = null;
    private List<Location> locations = null;

    public static HotspotBuilder get() {
        return new HotspotBuilder();
    }

    public HotspotBuilder setBssid(String bssid) {
        this.bssid = bssid;
        return this;
    }

    public HotspotBuilder setSsid(String ssid) {
        this.ssid = ssid;
        return this;
    }

    public HotspotBuilder setConnectionCount(int connectionCount) {
        this.connectionCount = connectionCount;
        return this;
    }

    public HotspotBuilder setComputedLocation(Location computedLocation) {
        this.computedLocation = computedLocation;
        return this;
    }

    public HotspotBuilder setLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }

    public Hotspot build() {
        Hotspot hotspot = new Hotspot();
        hotspot.setBssid(bssid);
        hotspot.setSsid(ssid);
        hotspot.setConnectionCount(connectionCount);
        hotspot.setComputedLocation(computedLocation);
        hotspot.setLocations(locations);

        return hotspot;
    }
}
