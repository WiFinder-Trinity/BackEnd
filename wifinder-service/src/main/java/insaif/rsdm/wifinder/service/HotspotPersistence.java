package insaif.rsdm.wifinder.service;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.HotspotInformation;

import java.util.List;

public interface HotspotPersistence {

    /**
     * Update a hotspot persistence with the location of the input given in parameter or add the hotspot to the
     * persistence if it is not already in, still with the location.
     * @param hotspotInformation the information for identifying the hotspot and give the strength of the signal at the
     *                           location
     * @param input contains in properties the information of the location
     * @return the concerned persisted hotspot
     */
    Hotspot addOrUpdateHotspot(FindInput input, HotspotInformation hotspotInformation);
}
