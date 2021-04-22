package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Location;
import nu.hovland.electricity.models.Meter;
import nu.hovland.electricity.repositories.LocationRepository;
import nu.hovland.electricity.repositories.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;
    private MeterRepository meterRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, MeterRepository meterRepository) {
        this.locationRepository = locationRepository;
        this.meterRepository = meterRepository;
    }


    @Override
    public Collection<Location> findAll() {
        return (Collection<Location>) locationRepository.findAll();
    }

    @Override
    public void addNew(Location l) {
        locationRepository.save(l);
    }

    @Override
    public Location findById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    /**
     *
     * @param locationId
     * @param meter
     * @return Meter or null if location not found or on Meter insert failure
     */
    @Override
    public Meter addNewMeterToLocation(Long locationId, Meter meter) {
        Location location = findById(locationId);
        if (location != null) {
            meter.setLocation(location);
            return meterRepository.save(meter);
        }
        else {
            return null;
        }
    }
}
