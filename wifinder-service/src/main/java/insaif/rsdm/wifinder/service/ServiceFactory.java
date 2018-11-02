package insaif.rsdm.wifinder.service;

import org.springframework.stereotype.Service;

@Service
public interface ServiceFactory {

    FindBestService getFindBestService();
}
