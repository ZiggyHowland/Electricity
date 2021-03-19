package nu.hovland.electricity.repositories;

import nu.hovland.electricity.models.Meeter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeeterRepository extends CrudRepository<Meeter, Long> {
}
