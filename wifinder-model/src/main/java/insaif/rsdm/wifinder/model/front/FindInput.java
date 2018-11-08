package insaif.rsdm.wifinder.model.front;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FindInput {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @Valid
    private List<HotspotInformation> hotspots;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public List<HotspotInformation> getHotspots() {
        return hotspots;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
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
