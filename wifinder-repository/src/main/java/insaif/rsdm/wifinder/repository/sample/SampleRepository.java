package insaif.rsdm.wifinder.repository.sample;

import insaif.rsdm.wifinder.model.sample.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends CrudRepository<Sample, Long> {

    List<Sample> findSamplesByName(String name);
}
