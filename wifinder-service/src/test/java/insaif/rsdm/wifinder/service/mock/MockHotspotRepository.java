package insaif.rsdm.wifinder.service.mock;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.repository.hotspot.HotspotRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MockHotspotRepository implements HotspotRepository {

    @Override
    public <S extends Hotspot> S save(S s) {
        return null;
    }

    @Override
    public <S extends Hotspot> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Hotspot> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Hotspot> findAll() {
        return null;
    }

    @Override
    public Iterable<Hotspot> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Hotspot hotspot) {

    }

    @Override
    public void deleteAll(Iterable<? extends Hotspot> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
