package insaif.rsdm.wifinder.model.back;

import java.util.ArrayList;
import java.util.List;

public class Hotspot {

    private String bssid;

    private String ssid;

    private int connectionCount;

    private List<Location> locations;

    private Location computedLocation;

    public Hotspot() {
        this.bssid = "";
        this.ssid = "";
        this.connectionCount = 0;
        this.locations = new ArrayList<Location>();
        this.computedLocation = null;
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

    public Location getComputedLocation() {
        return computedLocation;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setConnectionCount(int connectionCount) {
        this.connectionCount = connectionCount;
    }

    public void setComputedLocation(Location computedLocation) throws IllegalArgumentException {

        if (this.locations.contains(computedLocation)) {

            this.computedLocation = computedLocation;

        } else {

            throw new IllegalArgumentException("Location (" + computedLocation.getLatitude() + ":" +
                    computedLocation.getLongitude() + ") not in " + this.ssid + " hotspot locations list");
        }
    }

    public boolean addLocation(Location location)
    {
        return this.locations.add(location);
    }

    public boolean removeLocation(Location location)
    {
        return this.locations.remove(location);
    }

    public void removeLocation(int index)
    {
        this.locations.remove(index);
    }
}
