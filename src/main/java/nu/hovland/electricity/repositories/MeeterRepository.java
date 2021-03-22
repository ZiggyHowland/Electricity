package nu.hovland.electricity.repositories;

import nu.hovland.electricity.models.Meeter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface MeeterRepository extends CrudRepository<Meeter, Long> {

    @Query("SELECT m FROM Meeter m WHERE m.location.id = ?1")
    Iterable<Meeter> findAllByLocationId(Long locationId);
}
