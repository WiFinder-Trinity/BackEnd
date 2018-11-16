package insaif.rsdm.wifinder.model.front;

import java.util.Map;

import javax.validation.Valid;

public class LocateOutput {

    @Valid
    private Map<String, Location> hotspots;

    public Map<String, Location> getHotspots() {
        return hotspots;
    }

    public void setHotspots(Map<String, Location> hotspots) {
        this.hotspots = hotspots;
    }

}
