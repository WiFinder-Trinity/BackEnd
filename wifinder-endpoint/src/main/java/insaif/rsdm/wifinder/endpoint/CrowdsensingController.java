package insaif.rsdm.wifinder.endpoint;

import insaif.rsdm.wifinder.model.front.ConnectionInput;
import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.FindOutput;
import insaif.rsdm.wifinder.service.HotspotService;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CrowdsensingController {

    private static final Logger log = LoggerFactory.getLogger(CrowdsensingController.class);

    private HotspotService hotspotService;

    @Autowired
    public CrowdsensingController(HotspotService hotspotService) {
        this.hotspotService = hotspotService;
    }

    @RequestMapping(path = "/find", method = POST)
    public FindOutput find(@Valid @RequestBody FindInput input) throws Exception {

        log.debug("find service is called");

        return hotspotService.findBestHotspot(input);
    }

    @RequestMapping(path = "/connect", method = POST)
    public void connect(@Valid @RequestBody ConnectionInput input) {
        log.debug("connect service called");
        hotspotService.signalHotspotConnection(input);
    }

    @RequestMapping(path = "/disconnect", method = POST)
    public void disconnect(@Valid @RequestBody ConnectionInput input) {
        log.debug("disconnect service called");
        hotspotService.signalHotspotDisconnection(input);
    }

    @RequestMapping(path = "/test/{text}", method = GET)
    public String test(@PathVariable String text) {
        log.debug("test is called with {}", text);
        return "<h1>" + text + "</h1>";
    }
}
