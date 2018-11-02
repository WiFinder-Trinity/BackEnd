package insaif.rsdm.wifinder.endpoint;

import insaif.rsdm.wifinder.model.sample.Sample;
import insaif.rsdm.wifinder.service.SampleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CrowdsensingController {

    private static final Logger log = LoggerFactory.getLogger(CrowdsensingController.class);

    private SampleService sampleService;

    @Autowired
    public CrowdsensingController(SampleService SampleService) {
        this.sampleService = SampleService;
    }

//    @RequestMapping(path="/find", method=POST)
//    public FindOutput find(@RequestBody FindInput input) {
//
//        log.info("find service is called");
//
//        return sampleService.getFindBestService().findBestHotspot(input);
//    }

    @RequestMapping(path = "/test/{name}", method = GET)
    public Sample test(@PathVariable String name) {
        log.debug("test is called with {}", name);
        return sampleService.createSample(name);
    }
}
