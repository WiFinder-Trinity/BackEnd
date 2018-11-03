package insaif.rsdm.wifinder.service.impl;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.Location;
import insaif.rsdm.wifinder.model.back.builder.HotspotBuilder;
import insaif.rsdm.wifinder.model.back.builder.LocationBuilder;
import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;
import insaif.rsdm.wifinder.model.front.HotspotInformation;
import insaif.rsdm.wifinder.repository.hotspot.HotspotRepository;
import insaif.rsdm.wifinder.service.HotspotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotspotServiceImpl implements HotspotService {

    private HotspotRepository hotspotRepository;

    @Autowired
    public HotspotServiceImpl(HotspotRepository hotspotRepository) {
        this.hotspotRepository = hotspotRepository;
    }

    public FindOutput findBestHotspot(FindInput input) {
//        HotspotInformation hotspot = input.getHotspot(0);
//
//        FindOutput findOutput = new FindOutput();
//        findOutput.setBssid(hotspot.getBssid());
//        findOutput.setSsid(hotspot.getSsid());

        //TODO: input must be converted into list of Hotspot : search into database if exist, else create it
        List<Hotspot> hotspots = inputToHotspotList(input);

        //TODO: choose the best Hotspot among the previously created list
        Hotspot bestHotspot = pickBestHotspotFromList(hotspots);

        //TODO: create a FindOutput
        return hotspotToOutput(bestHotspot);
    }

    private List<Hotspot> inputToHotspotList(FindInput input) {
        List<HotspotInformation> hotspotInformations = input.getHotspots();

        return hotspotInformations.stream().map(hotspotInformation -> {
            if (hotspotRepository.existsById(hotspotInformation.getBssid()))
                return updateExistingHotspot(hotspotInformation, input);
            else
                return addNewHotspot(hotspotInformation, input);
        }).collect(Collectors.toList());
    }

    private Hotspot updateExistingHotspot(HotspotInformation hotspotInformation, FindInput input) {
        Location location = LocationBuilder.get()
                                           .setLatitude(input.getLatitude())
                                           .setLongitude(input.getLongitude())
                                           .setStrength(hotspotInformation.getStrength())
                                           .build();
        Hotspot hotspot = hotspotRepository.findById(hotspotInformation.getBssid()).get();
        hotspot.getLocations().add(location);
        //TODO: Recompute "real" location

        return hotspotRepository.save(hotspot);
    }

    private Hotspot addNewHotspot(HotspotInformation hotspotInformation, FindInput input) {
        Location location = LocationBuilder.get()
                                           .setLatitude(input.getLatitude())
                                           .setLongitude(input.getLongitude())
                                           .setStrength(hotspotInformation.getStrength())
                                           .build();
        Hotspot hotspot = HotspotBuilder.get()
                                        .setBssid(hotspotInformation.getBssid())
                                        .setSsid(hotspotInformation.getSsid())
                                        .setLocations(Collections.singletonList(location))
                                        .build();
        return hotspotRepository.save(hotspot);
    }

    private Hotspot pickBestHotspotFromList(List<Hotspot> hotspots) {
        return hotspots.stream().max(Comparator.comparing(Hotspot::getConnectionCount)).get();
    }

    private FindOutput hotspotToOutput(Hotspot hotspot) {
        FindOutput output = new FindOutput();
        output.setBssid(hotspot.getBssid());
        output.setSsid(hotspot.getSsid());

        return output;
    }
}
