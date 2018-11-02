package insaif.rsdm.wifinder.repository.hotspot;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.builder.HotspotBuilder;
import insaif.rsdm.wifinder.repository.TestApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Iterator;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class HotspotRepositoryTest {

    @Autowired
    HotspotRepository hotspotRepository;

    @Test
    public void shouldCreateHotspotWithNoLocation() {
        Hotspot hotspot = HotspotBuilder.get()
                                        .setBssid("bssid")
                                        .setSsid("ssid")
                                        .setConnectionCount(2)
                                        .build();

        hotspotRepository.save(hotspot);

        Optional<Hotspot> optionalHotspot = hotspotRepository.findById("bssid");

        assertThat(optionalHotspot.isPresent()).isTrue();

        Hotspot response = optionalHotspot.get();

        assertThat(response.getBssid()).isEqualTo("bssid");
        assertThat(response.getComputedLocation()).isNull();
        assertThat(response.getLocations().isEmpty()).isTrue();
    }

    @Test
    public void shouldUpdateEntity() {
        Hotspot hotspot = HotspotBuilder.get()
                                        .setBssid("bssid")
                                        .setSsid("ssid")
                                        .setConnectionCount(2)
                                        .build();

        hotspotRepository.save(hotspot);

        Optional<Hotspot> optionalHotspot = hotspotRepository.findById("bssid");

        assertThat(optionalHotspot.isPresent()).isTrue();

        Hotspot response = optionalHotspot.get();
        response.setConnectionCount(response.getConnectionCount() + 1);

        hotspotRepository.save(response);

        Iterator<Hotspot> hotspots = hotspotRepository.findAll().iterator();

        assertThat(hotspots.next().getConnectionCount()).isEqualTo(3);
        assertThat(hotspots.hasNext()).isFalse();
    }
}
