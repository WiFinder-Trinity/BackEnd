package insaif.rsdm.wifinder.service.locationalgo.lemmingapex;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.Location;
import insaif.rsdm.wifinder.model.back.builder.HotspotBuilder;
import insaif.rsdm.wifinder.model.back.builder.LocationBuilder;
import insaif.rsdm.wifinder.service.TestApplication;
import insaif.rsdm.wifinder.service.locationalgo.LocationAlgorithm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    LocationAlgorithm locationAlgorithm;

    @Test
    public void computeLocation_From2UsersLocations_Null() {
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

        Hotspot hotspot = HotspotBuilder.get()
                                        .setSsid("test ssid")
                                        .setBssid("test bssid")
                                        .setConnectionCount(4)
                                        .setLocations(locations)
                                        .setFrequency((int) WIFI_FREQ)
                                        .setComputedLocation(null)
                                        .build();

        Location location = locationAlgorithm.computeLocation(hotspot);

        Location computedLocation = hotspot.getComputedLocation();

        assertThat(computedLocation).isNull();
        assertThat(location).isNull();
        assertThat(computedLocation).isEqualTo(location);
    }

    @Test
    public void computeLocation_From6UsersLocations_GoodCoordinatesResult() {

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
                                        .setFrequency((int) WIFI_FREQ)
                                        .setComputedLocation(null)
                                        .build();

        Location location = locationAlgorithm.computeLocation(hotspot);

        Location computedLocation = hotspot.getComputedLocation();

        assertThat(computedLocation).isNotNull();
        assertThat(location).isNotNull();
        assertThat(computedLocation).isEqualTo(location);
        assertThat(computedLocation.getLatitude()).isBetween(59.42625, 59.42627);
        log.info("latitude : " + computedLocation.getLatitude());
        assertThat(computedLocation.getLongitude()).isBetween(24.72522, 24.72525);
        log.info("longitude : " + computedLocation.getLongitude());
    }

    @Test
    public void shouldReturnBullshit() {
        ArrayList<Location> locations = new ArrayList<>();

        locations.add(LocationBuilder.get()
                                     .setLatitude(45.782369)
                                     .setLongitude(4.872553)
                                     .setStrength(distanceToStrength(-39))
                                     .build());
        locations.add(LocationBuilder.get()
                                     .setLatitude(45.781856)
                                     .setLongitude(4.872803)
                                     .setStrength(distanceToStrength(-100))
                                     .build());
        locations.add(LocationBuilder.get()
                                     .setLatitude(45.78211)
                                     .setLongitude(4.872229)
                                     .setStrength(distanceToStrength(-40))
                                     .build());

        Hotspot hotspot = HotspotBuilder.get()
                                        .setSsid("test ssid")
                                        .setBssid("test bssid")
                                        .setConnectionCount(0)
                                        .setLocations(locations)
                                        .setFrequency(2400)
                                        .build();

        Location location = locationAlgorithm.computeLocation(hotspot);
        log.info("computed location is lat : {} and lng : {}", location.getLatitude(), location.getLongitude());
    }

    private int distanceToStrength(double distance) {
        return (int) (20 * (Math.log10(distance) + Math.log10(WIFI_FREQ)) - FSPL_CONST_M_MHZ);
    }
}
