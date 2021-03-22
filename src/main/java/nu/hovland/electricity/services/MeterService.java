package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Meter;

import java.util.Collection;

public interface MeterService {
    public Meter addNewMeter(Meter m);
    public void updateMeter(Meter m);
    public void deleteMeter(Long id);
    public Collection<Meter> findByLocation(Long locationId);
    public Collection<Meter> findAll();
    public Meter findById(Long id);
}
