package insaif.rsdm.wifinder.service.locationalgo;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.Location;

public interface LocationAlgorithm {

    Location computeLocation(Hotspot hotspot);
}
