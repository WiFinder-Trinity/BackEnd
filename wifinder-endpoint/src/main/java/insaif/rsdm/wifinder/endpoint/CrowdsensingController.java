package insaif.rsdm.wifinder.endpoint;

import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;
import insaif.rsdm.wifinder.model.sample.Sample;
import insaif.rsdm.wifinder.service.HotspotService;
import insaif.rsdm.wifinder.service.SampleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CrowdsensingController {

    private static final Logger log = LoggerFactory.getLogger(CrowdsensingController.class);

    private SampleService sampleService;
    private HotspotService hotspotService;

    @Autowired
    public CrowdsensingController(SampleService sampleService, HotspotService hotspotService) {
        this.sampleService = sampleService;
        this.hotspotService = hotspotService;
    }

    @RequestMapping(path = "/find", method = POST)
    public FindOutput find(@Valid @RequestBody FindInput input) throws Exception {

        log.debug("find service is called");

        return hotspotService.findBestHotspot(input);
    }

    @RequestMapping(path = "/connect", method = POST)
    public void connect(@Valid @RequestBody String input) {
        log.debug("connect service called");
        hotspotService.signalHotspotConnection(input);
    }

    @RequestMapping(path = "/disconnect", method = POST)
    public void disconnect(@Valid @RequestBody String input) {
        log.debug("disconnect service called");
        hotspotService.signalHotspotDisconnection(input);
    }

    @RequestMapping(path = "/test/{name}", method = GET)
    public Sample test(@PathVariable String name) {
        log.debug("test is called with {}", name);
        return sampleService.createSample(name);
    }
}
