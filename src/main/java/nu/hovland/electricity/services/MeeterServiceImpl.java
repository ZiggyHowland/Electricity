package nu.hovland.electricity.services;

import nu.hovland.electricity.models.Meeter;
import nu.hovland.electricity.repositories.MeeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MeeterServiceImpl implements MeeterService  {
    private MeeterRepository meeterRepository;

    @Autowired
    public MeeterServiceImpl(MeeterRepository meeterRepository) {
        this.meeterRepository = meeterRepository;
    }

    @Override
    public Meeter addNewMeeter(Meeter m) {
        return meeterRepository.save(m);
    }

    @Override
    public Collection<Meeter> findByLocation(Long locationId) {
        return (Collection<Meeter>)meeterRepository.findAllByLocationId(locationId);
    }

    @Override
    public Collection<Meeter> findAll() {
        return (Collection<Meeter>)meeterRepository.findAll();
    }

    @Override
    public Meeter findById(Long id) {
        return meeterRepository.findById(id).orElse(null);
    }



}
