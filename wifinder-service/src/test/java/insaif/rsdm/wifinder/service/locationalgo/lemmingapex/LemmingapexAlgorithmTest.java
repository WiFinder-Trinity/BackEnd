package insaif.rsdm.wifinder.service.locationalgo.lemmingapex;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.Location;
import insaif.rsdm.wifinder.model.back.builder.HotspotBuilder;
import insaif.rsdm.wifinder.model.back.builder.LocationBuilder;
import insaif.rsdm.wifinder.service.TestApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class LemmingapexAlgorithmTest {

    private static final Logger log = LoggerFactory.getLogger(LemmingapexAlgorithmTest.class);

    /**
     * The constant to add in the distance computation from the free space path loss, with the distance in Meters and
     * the frequency in MHz
     */
    private final static double FSPL_CONST_M_MHZ = 27.55;

    /**
     * The standard WiFi frequency in MHz
     * TODO : add this to the model and collect ot from the mobile app
     */
    private final static double WIFI_FREQ = 5000;

    @Test
    public void computeLocation_From6UsersLocations() {

        // data from https://appelsiini.net/2017/trilateration-with-n-points/
        ArrayList<Location> locations = new ArrayList<>();

        locations.add(LocationBuilder.get()
                .setLatitude(59.42606837)
                .setLongitude(24.72553151)
                .setStrength(distanceToStrength(8.0))
                .build());
        locations.add(LocationBuilder.get()
                .setLatitude(59.42610146)
                .setLongitude(24.72552969)
                .setStrength(distanceToStrength(8.0))
                .build());
        locations.add(LocationBuilder.get()
                .setLatitude(59.42654852)
                .setLongitude(24.72467492)
                .setStrength(distanceToStrength(9.0))
                .build());
        locations.add(LocationBuilder.get()
                .setLatitude(59.42609108)
                .setLongitude(24.72555759)
                .setStrength(distanceToStrength(9.0))
                .build());
        locations.add(LocationBuilder.get()
                .setLatitude(59.42603039)
                .setLongitude(24.72565661)
                .setStrength(distanceToStrength(9.0))
                .build());
        locations.add(LocationBuilder.get()
                .setLatitude(59.42666361)
                .setLongitude(24.72449149)
                .setStrength(distanceToStrength(14.0))
                .build());

        Hotspot hotspot = HotspotBuilder.get()
                .setSsid("test ssid")
                .setBssid("test bssid")
                .setConnectionCount(4)
                .setLocations(locations)
                .setComputedLocation(null)
                .build();

        LemmingapexAlgorithm lemmingapexAlgorithm = new LemmingapexAlgorithm();
        Location location = lemmingapexAlgorithm.computeLocation(hotspot);

        Location computedLocation = hotspot.getComputedLocation();

        assertThat(computedLocation).isNotNull();
        assertThat(location).isNotNull();
        assertThat(computedLocation).isEqualTo(location);
        assertThat(computedLocation.getLatitude()).isBetween(59.42625, 59.42627);
        log.info("latitude : " + computedLocation.getLatitude());
        assertThat(computedLocation.getLongitude()).isBetween(24.72522, 24.72525);
        log.info("longitude : " + computedLocation.getLongitude());
    }

    private int distanceToStrength(double distance) {
        return (int)(20 * (Math.log10(distance) + Math.log10(WIFI_FREQ)) - FSPL_CONST_M_MHZ);
    }
}
