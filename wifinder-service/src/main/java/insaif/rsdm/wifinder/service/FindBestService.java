package insaif.rsdm.wifinder.service;

import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;

import org.springframework.stereotype.Service;

@Service
public interface FindBestService {

    FindOutput findBestHotspot(FindInput input);
}
