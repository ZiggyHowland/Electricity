package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Location;
import nu.hovland.electricity.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void addNew(Location l) {
        locationRepository.save(l);
    }

    @Override
    public Location findLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }
}
