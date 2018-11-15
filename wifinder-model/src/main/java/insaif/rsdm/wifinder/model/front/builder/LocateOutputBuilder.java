package insaif.rsdm.wifinder.model.front.builder;

import insaif.rsdm.wifinder.model.front.LocateOutput;
import insaif.rsdm.wifinder.model.front.Location;

import java.util.Map;

public class LocateOutputBuilder {

    private Map<String, Location> hotspots;

    public static LocateOutputBuilder get() { return new LocateOutputBuilder(); }

    public LocateOutputBuilder setHotspots(Map<String, Location> hotspots) {
        this.hotspots = hotspots;
        return this;
    }

    public LocateOutput build() {
        LocateOutput locateOutput = new LocateOutput();
        locateOutput.setHotspots(this.hotspots);
        return locateOutput;
    }

    private LocateOutputBuilder() {
        this.hotspots = null;
    }
}
