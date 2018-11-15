package insaif.rsdm.wifinder.service.impl;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.Location;
import insaif.rsdm.wifinder.model.back.builder.HotspotBuilder;
import insaif.rsdm.wifinder.model.back.builder.LocationBuilder;
import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.HotspotInformation;
import insaif.rsdm.wifinder.repository.hotspot.HotspotRepository;
import insaif.rsdm.wifinder.service.HotspotPersistence;
import insaif.rsdm.wifinder.service.locationalgo.LocationAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class HotspotPersistenceImpl implements HotspotPersistence {

    private HotspotRepository hotspotRepository;

    private LocationAlgorithm locationAlgorithm;

    @Autowired
    public HotspotPersistenceImpl(HotspotRepository hotspotRepository, LocationAlgorithm locationAlgorithm) {
        this.hotspotRepository = hotspotRepository;
        this.locationAlgorithm = locationAlgorithm;
    }

    @Override
    public Hotspot addOrUpdateHotspot(FindInput input, HotspotInformation hotspotInformation) {
        Location location = LocationBuilder.get()
                .setLatitude(input.getLatitude())
                .setLongitude(input.getLongitude())
                .setStrength(hotspotInformation.getStrength())
                .build();

        Optional<Hotspot> optionalHotspot = hotspotRepository.findById(hotspotInformation.getBssid());

        // TODO: better verfification of hotspot presence
        if (optionalHotspot.isPresent()) {

            Hotspot hotspot = optionalHotspot.get();
            hotspot.getLocations().add(location);

            locationAlgorithm.computeLocation(hotspot);

            return hotspotRepository.save(hotspot);

        } else {

            Hotspot hotspot = HotspotBuilder.get()
                    .setBssid(hotspotInformation.getBssid())
                    .setSsid(hotspotInformation.getSsid())
                    .setLocations(Collections.singletonList(location))
                    .build();
            return hotspotRepository.save(hotspot);
        }
    }
}
