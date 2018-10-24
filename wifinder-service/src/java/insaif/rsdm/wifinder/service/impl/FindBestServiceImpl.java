package insaif.rsdm.wifinder.service.impl;


import org.springframework.stereotype.Component;

import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;
import insaif.rsdm.wifinder.model.front.HotspotInformation;
import insaif.rsdm.wifinder.service.FindBestService;

@Component
public class FindBestServiceImpl implements FindBestService {

    public FindOutput findBestHotspot(FindInput input) {
        HotspotInformation hotspot = input.getHotspots().get(0);
        return new FindOutput(hotspot.getSsid(), hotspot.getBssid());
    }
}
