package nu.hovland.electricity.repositories;

import nu.hovland.electricity.models.Location;
import org.springframework.data.repository.CrudRepository;


public interface LocationRepository extends CrudRepository<Location, Long> {

}
