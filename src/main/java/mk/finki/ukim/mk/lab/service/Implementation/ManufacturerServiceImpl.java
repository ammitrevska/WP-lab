package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepositoryJpa;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepositoryJpa manufacturerRepositoryJpa;

    public ManufacturerServiceImpl(ManufacturerRepositoryJpa manufacturerRepositoryJpa) {
        this.manufacturerRepositoryJpa = manufacturerRepositoryJpa;
    }


    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepositoryJpa.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name, String country, String address) {
        return Optional.of(this.manufacturerRepositoryJpa.save(new Manufacturer(name,country, address)));

    }
}
