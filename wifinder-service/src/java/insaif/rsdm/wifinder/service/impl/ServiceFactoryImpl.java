package insaif.rsdm.wifinder.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import insaif.rsdm.wifinder.service.ServiceFactory;
import insaif.rsdm.wifinder.service.FindBestService;

@Component
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
