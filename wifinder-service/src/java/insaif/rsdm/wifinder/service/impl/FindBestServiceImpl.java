package insaif.rsdm.wifinder.service.impl;

import org.springframework.stereotype.Service;

import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;
import insaif.rsdm.wifinder.model.front.HotspotInformation;
import insaif.rsdm.wifinder.service.FindBestService;

@Service
public class FindBestServiceImpl implements FindBestService {

    public FindOutput findBestHotspot(FindInput input) {
        HotspotInformation hotspot = input.getHotspot(0);

        FindOutput findOutput = new FindOutput();
        findOutput.setBssid(hotspot.getBssid());
        findOutput.setSsid(hotspot.getSsid());

        return findOutput;
    }
}
