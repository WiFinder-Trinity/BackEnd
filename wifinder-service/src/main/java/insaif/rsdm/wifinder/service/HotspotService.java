package insaif.rsdm.wifinder.service;

import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;

public interface HotspotService {

    FindOutput findBestHotspot(FindInput input) throws Exception;

    void signalHotspotConnection(String input);

    void signalHotspotDisconnection(String input);
}
