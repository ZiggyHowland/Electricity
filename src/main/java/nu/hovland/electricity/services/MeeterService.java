package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Meeter;

import java.util.Collection;

public interface MeeterService {
    public Meeter addNewMeeter(Meeter m);
    public Collection<Meeter> findByLocation(Long locationId);
    public Collection<Meeter> findAll();
    public Meeter findById(Long id);
}
