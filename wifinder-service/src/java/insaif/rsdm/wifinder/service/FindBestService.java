package insaif.rsdm.wifinder.service;

import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;

public interface FindBestService {

    FindOutput findBestHotspot(FindInput input);
}
