package nu.hovland.electricity.repositories;

import nu.hovland.electricity.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface LocationRepository extends CrudRepository<Location, Long> {

}
