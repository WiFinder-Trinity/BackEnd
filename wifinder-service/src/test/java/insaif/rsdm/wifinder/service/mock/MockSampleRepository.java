package insaif.rsdm.wifinder.service.mock;

import insaif.rsdm.wifinder.model.sample.Sample;
import insaif.rsdm.wifinder.repository.sample.SampleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MockSampleRepository implements SampleRepository {

    @Override
    public List<Sample> findSamplesByName(String name) {
        return null;
    }

    @Override
    public <S extends Sample> S save(S s) {
        return null;
    }

    @Override
    public <S extends Sample> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Sample> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Sample> findAll() {
        return null;
    }

    @Override
    public Iterable<Sample> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Sample sample) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sample> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
