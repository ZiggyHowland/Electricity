package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Meter;
import nu.hovland.electricity.repositories.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MeterServiceImpl implements MeterService {
    private MeterRepository meterRepository;

    @Autowired
    public MeterServiceImpl(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    @Override
    public Meter addNewMeter(Meter m) {
        return meterRepository.save(m);
    }

    @Override
    public void updateMeter(Meter m) {
        meterRepository.save(m);
    }

    @Override
    public void deleteMeter(Long id) {
        meterRepository.deleteById(id);
    }

    @Override
    public Collection<Meter> findByLocation(Long locationId) {
        return (Collection<Meter>)meterRepository.findAllByLocationId(locationId);
    }

    @Override
    public Collection<Meter> findAll() {
        return (Collection<Meter>)meterRepository.findAll();
    }

    @Override
    public Meter findById(Long id) {
        return meterRepository.findById(id).orElse(null);
    }



}
