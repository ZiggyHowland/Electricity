package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Location;
import nu.hovland.electricity.models.Meter;

import java.util.Collection;

public interface LocationService {
    public void addNew(Location l);
    public Location findById(Long id);
    public Collection<Location> findAll();
    public Meter addNewMeterToLocation(Long locationId, Meter meter);
}
