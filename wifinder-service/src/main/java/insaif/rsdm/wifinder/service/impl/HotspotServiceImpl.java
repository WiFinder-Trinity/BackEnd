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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotspotServiceImpl implements HotspotService {

    private static final Logger log = LoggerFactory.getLogger(HotspotService.class);

    private HotspotRepository hotspotRepository;

    @Autowired
    public HotspotServiceImpl(HotspotRepository hotspotRepository) {
        this.hotspotRepository = hotspotRepository;
    }

    @Override
    public FindOutput findBestHotspot(FindInput input) throws Exception {

        log.debug("findBestHotspot service is called with {} hotspots", input.getHotspots().size());

        List<Hotspot> hotspots = inputToHotspotList(input);

        HotspotInformation bestHotspot = pickBestHotspotFromList(hotspots, input);

        log.debug("Best Hotspot was found successfully, bssid : {}", bestHotspot.getBssid());

        return hotspotInfoToOutput(bestHotspot);
    }

    @Override
    public void signalHotspotConnection(String input) {

        log.debug("signalHotspotConnection service is called for the hotspot with BSSID : " + input);

        Optional<Hotspot> optionalHotspot = hotspotRepository.findById(input);

        // if Hotspot in the database, adds a connection count to it
        if (optionalHotspot.isPresent()) {
            Hotspot hotspot = optionalHotspot.get();
            hotspot.setConnectionCount(hotspot.getConnectionCount() + 1);

            hotspotRepository.save(hotspot);
            log.debug("connection added, hotspot with BSSID : " + input + " has now " + hotspot.getConnectionCount() +
                    " connections");
            // else does nothing, TODO: improve it for instance by asking for Hotspot information
        } else {
            log.error("Hotspot with BSSID : " + input + " not found in database");
        }
    }

    @Override
    public void signalHotspotDisconnection(String input) {

        log.debug("signalHotspotConnection service is called for the hotspot with BSSID : " + input);

        Optional<Hotspot> optionalHotspot = hotspotRepository.findById(input);

        // if Hotspot in the database, removes a connection count to it
        if (optionalHotspot.isPresent()) {
            Hotspot hotspot = optionalHotspot.get();

            if (hotspot.getConnectionCount() > 0) {
                hotspot.setConnectionCount(hotspot.getConnectionCount() - 1);
                hotspotRepository.save(hotspot);
                log.debug("Connection removed, hotspot with BSSID : " + input + " has now " +
                        hotspot.getConnectionCount() + " connections");
            } else {
                log.debug("Connection not removed because hotspot with BSSID : " + input + " has already " +
                        hotspot.getConnectionCount() + " connections");
            }

            // else does nothing, TODO: improve it for instance by asking for Hotspot information
        } else {
            log.error("Hotspot with BSSID : " + input + " not found in database");
        }
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

    private HotspotInformation pickBestHotspotFromList(List<Hotspot> hotspots, FindInput input) throws Exception {
        List<HotspotInformation> inputInfo = input.getHotspots();

        List<HotspotInformation> sortedInfo = inputInfo.stream()
                                                       .sorted(Comparator.comparing(HotspotInformation::getStrength)
                                                                         .reversed())
                                                       .collect(Collectors.toList());

        // we take the less crowded hotpots
        Hotspot minCrowded = Collections.min(hotspots, Comparator.comparing(Hotspot::getConnectionCount));
        List<Hotspot> lessCrowded = hotspots.stream()
                                            .filter(h -> h.getConnectionCount() <= minCrowded.getConnectionCount())
                                            .collect(Collectors.toList());


        // we take the strongest among them
        HotspotInformation strongest = picktTheStrongest(lessCrowded, sortedInfo);

        if (strongest == null) {
            throw new Exception("Something bad happened !");
        }

        return strongest;
    }

    private HotspotInformation picktTheStrongest(List<Hotspot> hotspotList, List<HotspotInformation> sortedInfo) {
        for (HotspotInformation info : sortedInfo) {
            for (Hotspot hotspot : hotspotList) {
                if (info.getBssid().equals(hotspot.getBssid())) {
                    return info;
                }
            }
        }
        return null;
    }

    private FindOutput hotspotInfoToOutput(HotspotInformation hotspot) {
        FindOutput output = new FindOutput();
        output.setBssid(hotspot.getBssid());
        output.setSsid(hotspot.getSsid());

        return output;
    }
}
