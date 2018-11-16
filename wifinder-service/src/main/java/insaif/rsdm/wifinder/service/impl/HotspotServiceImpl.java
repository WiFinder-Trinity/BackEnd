package insaif.rsdm.wifinder.service.impl;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.front.ConnectionInput;
import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;
import insaif.rsdm.wifinder.model.front.HotspotInformation;
import insaif.rsdm.wifinder.repository.hotspot.HotspotRepository;
import insaif.rsdm.wifinder.service.HotspotPersistence;
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

    private HotspotPersistence hotspotPersistence;

    @Autowired
    public HotspotServiceImpl(HotspotRepository hotspotRepository, HotspotPersistence hotspotPersistence) {
        this.hotspotRepository = hotspotRepository;
        this.hotspotPersistence = hotspotPersistence;
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
    public void signalHotspotConnection(ConnectionInput input) {

        log.debug("signalHotspotConnection service is called for the hotspot with BSSID : {}", input.getBssid());

        Optional<Hotspot> optionalHotspot = hotspotRepository.findById(input.getBssid());

        // if Hotspot in the database, adds a connection count to it
        if (optionalHotspot.isPresent()) {
            Hotspot hotspot = optionalHotspot.get();
            hotspot.setConnectionCount(hotspot.getConnectionCount() + 1);

            hotspotRepository.save(hotspot);
            log.debug("Connection added, hotspot with BSSID : {} has now {} connection(s)",
                    input.getBssid(),
                    hotspot.getConnectionCount());
        // else does nothing, TODO: improve it by asking the front-end for instance for Hotspot information
        } else {
            log.error("Hotspot with BSSID : {} not found in database", input.getBssid());
        }
    }

    @Override
    public void signalHotspotDisconnection(ConnectionInput input) {

        log.debug("signalHotspotConnection service is called for the hotspot with BSSID : {}", input.getBssid());

        Optional<Hotspot> optionalHotspot = hotspotRepository.findById(input.getBssid());

        // if Hotspot in the database, removes a connection count to it
        if (optionalHotspot.isPresent()) {
            Hotspot hotspot = optionalHotspot.get();

            if (hotspot.getConnectionCount() > 0) {
                hotspot.setConnectionCount(hotspot.getConnectionCount() - 1);
                hotspotRepository.save(hotspot);
                log.debug("Connection removed, hotspot with BSSID : {} has now {} connection(s)",
                        input.getBssid(),
                        hotspot.getConnectionCount());
            } else {
                log.debug("Connection not removed because hotspot with BSSID : {} has already no connection",
                        input.getBssid());
            }

        // else does nothing, TODO: improve it for instance by asking for Hotspot information
        } else {
            log.error("Hotspot with BSSID : {} not found in database", input.getBssid());
        }
    }

    /**
     * Persists location of an input from the front-end and give the corresponding hotspots list
     * @param input the input from the front-end, containing the location to persist and the hotspots with their
     *              respective strength
     * @return the list of the well-persisted hotspots
     */
    private List<Hotspot> inputToHotspotList(FindInput input) {
        List<HotspotInformation> hotspotInformations = input.getHotspots();

        return hotspotInformations.stream().map(hotspotInformation ->
                hotspotPersistence.addOrUpdateHotspot(input, hotspotInformation))
                .collect(Collectors.toList());
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
                                            .filter(h -> (h.getConnectionCount() <= minCrowded.getConnectionCount()))
                                            .collect(Collectors.toList());


        // we take the strongest among them
        HotspotInformation strongest = pickTheStrongest(lessCrowded, sortedInfo);

        if (strongest == null) {
            throw new Exception("Something bad happened !");
        }

        return strongest;
    }

    private HotspotInformation pickTheStrongest(List<Hotspot> hotspotList, List<HotspotInformation> sortedInfo) {
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
