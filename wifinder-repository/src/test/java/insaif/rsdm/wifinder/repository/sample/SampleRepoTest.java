package insaif.rsdm.wifinder.repository.sample;

import insaif.rsdm.wifinder.model.sample.Sample;
import insaif.rsdm.wifinder.repository.TestApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class SampleRepoTest {

    private static final Logger log = LoggerFactory.getLogger(SampleRepoTest.class);

    @Autowired
    SampleRepository repository;

    @Test
    public void shouldFindTheEntity() {
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setName("Name");

        log.debug("Testing saving to database");
        repository.save(sample);

        Optional<Sample> response = repository.findById(1L);

        assertThat(response.isPresent()).isTrue();

        assertThat(response.get().getName()).isEqualTo("Name");
    }
}


