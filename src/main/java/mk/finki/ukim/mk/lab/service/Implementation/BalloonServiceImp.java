package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.impl.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.impl.ManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepositoryJpa;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImp implements BalloonService {

    private final BalloonRepositoryJpa balloonRepositoryJpa;
    private final ManufacturerRepositoryJpa manufacturerRepositoryJpa;

    //constructor based dependency injection
    public BalloonServiceImp( BalloonRepositoryJpa balloonRepositoryJpa, ManufacturerRepositoryJpa manufacturerRepositoryJpa){
        this.balloonRepositoryJpa = balloonRepositoryJpa;
        this.manufacturerRepositoryJpa = manufacturerRepositoryJpa;
    }


    @Override
    public List<Balloon> listAll() {
        return balloonRepositoryJpa.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepositoryJpa.findAllByNameOrDescription(text, text);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufecturerId) {
        Manufacturer manufacturer = this.manufacturerRepositoryJpa.findById(manufecturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufecturerId));

        this.balloonRepositoryJpa.deleteByName(name);
        return Optional.of(this.balloonRepositoryJpa.save(new Balloon(name,description,manufacturer)));
//        return this.balloonRepositoryJpa.save(name,description,manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepositoryJpa.findById(id);
    }
}
