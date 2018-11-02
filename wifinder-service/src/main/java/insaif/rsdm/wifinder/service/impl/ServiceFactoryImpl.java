package insaif.rsdm.wifinder.service.impl;


import insaif.rsdm.wifinder.service.FindBestService;
import insaif.rsdm.wifinder.service.ServiceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFactoryImpl implements ServiceFactory {

    private FindBestService findBestService;

    public FindBestService getFindBestService() {
        return findBestService;
    }

    @Autowired
    public void setFindBestService(FindBestService findBestService) {
        this.findBestService = findBestService;
    }
}
