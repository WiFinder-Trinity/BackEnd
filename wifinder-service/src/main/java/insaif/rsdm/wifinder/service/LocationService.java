package insaif.rsdm.wifinder.service;

import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.LocateOutput;

public interface LocationService {

    /**
     * Get the location of the location-computed hotspots and save the location of the user for each hotspot
     * @param input the request of the user which contains his location and the hotspots he detects
     * @return the list of the location-computed hotspots in the asked hotspots.
     */
    LocateOutput getHotspotsLocation(FindInput input);
}
