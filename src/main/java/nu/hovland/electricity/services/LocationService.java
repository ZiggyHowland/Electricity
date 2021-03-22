package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Location;

public interface LocationService {
    public void addNew(Location l);
    public Location findLocationById(Long id);
}
