package insaif.rsdm.wifinder.service.impl;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.LocateOutput;
import insaif.rsdm.wifinder.model.front.Location;
import insaif.rsdm.wifinder.model.front.builder.LocateOutputBuilder;
import insaif.rsdm.wifinder.model.front.builder.LocationBuilder;
import insaif.rsdm.wifinder.service.HotspotPersistence;
import insaif.rsdm.wifinder.service.LocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

    private HotspotPersistence hotspotPersistence;

    @Autowired
    public LocationServiceImpl(HotspotPersistence hotspotPersistence) {
        this.hotspotPersistence = hotspotPersistence;
    }


    @Override
    public LocateOutput getHotspotsLocation(FindInput input) {

        log.debug("getHotspotsLocation service called with {} hotspots", input.getHotspots().size());

        LocateOutput locateOutput = LocateOutputBuilder.get().setHotspots(inputToHotspotsList(input)).build();

        log.debug("{} hotspots location location successfully computed and returned",
                locateOutput.getHotspots().size());

        return locateOutput;
    }

    private Map<String, Location> inputToHotspotsList(FindInput input) {

        return input.getHotspots().stream()
                .map(hotspotInformation -> hotspotPersistence.addOrUpdateHotspot(input, hotspotInformation))
                .filter(hotspot -> (hotspot.getComputedLocation() != null))
                .collect(Collectors.toMap(Hotspot::getBssid, hotspot -> LocationBuilder.get()
                        .setLatitude(hotspot.getComputedLocation().getLatitude())
                        .setLongitude(hotspot.getComputedLocation().getLongitude())
                        .build()));
    }
}
