package nu.hovland.electricity.repositories;

import nu.hovland.electricity.models.Meter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface MeterRepository extends CrudRepository<Meter, Long> {

    @Query("SELECT m FROM Meter m WHERE m.location.id = ?1")
    Iterable<Meter> findAllByLocationId(Long locationId);
}
