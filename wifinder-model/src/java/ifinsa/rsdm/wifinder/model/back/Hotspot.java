package ifinsa.rsdm.wifinder.model.back;

import java.util.List;

public class Hotspot {

    private String bssid;

    private String ssid;

    private int connectionCount;

    private List<Location> locations;

    public Hotspot(String bssid, String ssid, int connectionCount, List<Location> locations) {
        this.bssid = bssid;
        this.ssid = ssid;
        this.connectionCount = connectionCount;
        this.locations = locations;
    }

    public String getBssid() {
        return bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public int getConnectionCount() {
        return connectionCount;
    }

    public List<Location> getLocations() {
        return locations;
    }
}
