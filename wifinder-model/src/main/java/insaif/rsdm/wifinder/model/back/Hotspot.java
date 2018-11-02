package insaif.rsdm.wifinder.model.back;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Hotspot {

    @Id
    private String bssid;

    private String ssid;

    private int connectionCount;

    @Embedded
    @OneToMany
    private List<Location> locations;

    @Embedded
    private Location computedLocation;

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
        this.computedLocation = computedLocation;
    }

    public Location getLocation(int index) {
        return this.locations.get(index);
    }

    public boolean addLocation(Location location) {
        return this.locations.add(location);
    }

    public boolean removeLocation(Location location) {
        return this.locations.remove(location);
    }

    public void removeLocation(int index) {
        this.locations.remove(index);
    }
}
