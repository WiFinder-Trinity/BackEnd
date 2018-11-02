package insaif.rsdm.wifinder.repository.hotspot;

import insaif.rsdm.wifinder.model.back.Hotspot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotspotRepository extends CrudRepository<Hotspot, String> {
}
