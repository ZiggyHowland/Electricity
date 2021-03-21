package nu.hovland.electricity.repositories;

import nu.hovland.electricity.models.Meeter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeeterRepository extends CrudRepository<Meeter, Long> {

    // TODO: WHY THIS DOESN'T WORK???
    @Query("SELECT m FROM Meeter m WHERE m.location.id = ?1")
    Iterable<Meeter> findAllByLocationId(Long locationId);
}
