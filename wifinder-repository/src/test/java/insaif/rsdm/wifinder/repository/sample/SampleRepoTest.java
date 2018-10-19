package insaif.rsdm.wifinder.repository.sample;

import insaif.rsdm.wifinder.model.sample.Sample;
import insaif.rsdm.wifinder.repository.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class SampleRepoTest {
    @Autowired
    SampleRepository repository;

    @Test
    public void shouldFindTheEntity() {
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setName("Name");

        repository.save(sample);

        Optional<Sample> response = repository.findById(1L);

        assertThat(response.isPresent()).isTrue();

        assertThat(response.get().getName()).isEqualTo("Name");
    }
}
