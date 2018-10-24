package insaif.rsdm.wifinder.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import insaif.rsdm.wifinder.model.front.FindOutput;
import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.service.ServiceFactory;

@RestController
@ComponentScan(basePackages = {"insaif.rsdm.wifinder.service.impl"})
public class CrowdsensingController {

    @Autowired
    private ServiceFactory serviceFactory;

    @RequestMapping(path="/find", method=POST)
    public FindOutput find(@RequestBody FindInput input) {
        return serviceFactory.getFindBestService().findBestHotspot(input);
    }
}
