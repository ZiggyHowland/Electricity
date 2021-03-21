package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Meeter;

import java.util.Collection;

public interface MeeterService {
    public void addNew(Meeter m);
    public Collection<Meeter> findByLocation(Long locationId);
}
