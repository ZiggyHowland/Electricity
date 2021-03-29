package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Location;

import java.util.Collection;

public interface LocationService {
    public void addNew(Location l);
    public Location findById(Long id);
    public Collection<Location> findAll();
}
