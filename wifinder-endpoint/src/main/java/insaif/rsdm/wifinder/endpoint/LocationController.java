package insaif.rsdm.wifinder.endpoint;

import insaif.rsdm.wifinder.model.front.FindInput;
import insaif.rsdm.wifinder.model.front.LocateOutput;
import insaif.rsdm.wifinder.service.LocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LocationController {

    private static final Logger log = LoggerFactory.getLogger(LocationController.class);

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(path = "/locate", method = POST)
    public LocateOutput locate(@Valid @RequestBody FindInput input) {

        log.debug("locate service is called");

        return locationService.getHotspotsLocation(input);
    }
}
