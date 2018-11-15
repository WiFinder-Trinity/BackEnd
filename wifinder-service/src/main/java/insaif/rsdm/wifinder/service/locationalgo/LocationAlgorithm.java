package insaif.rsdm.wifinder.service.locationalgo;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.Location;

import javax.validation.constraints.NotNull;

/**
 * Provides the algorithm to compute WiFi hotspot location from its users' locations and the signals strength
 */
public interface LocationAlgorithm {

    /**
     * Compute the location of a WiFi Hotspot from its users' locations and the signals strength, if they are at least
     * three. If it the case, stores it in the computedLocation property of the Hotspots
     * @param hotspot the Hotspot whose location is computed
     * @return the computed location of the hotspot, null if there are less than 3 location in its locations list
     */
    Location computeLocation(@NotNull Hotspot hotspot);
}
