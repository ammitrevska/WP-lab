package mk.finki.ukim.mk.lab.repository.impl;

//Adapter

import mk.finki.ukim.mk.lab.Bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class BalloonRepository {

    //List<Balloon> balloonList = new ArrayList<>(10);

    public List<Balloon> findAllBalloons(){
        return DataHolder.balloonList;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
       return DataHolder.balloonList.stream().filter(r-> r.getName().contains(text)  || r.getDescription().contains(text)).
                collect(Collectors.toList());
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufecturer){
        DataHolder.balloonList.removeIf(i -> i.getName().equals(name));
        Balloon balloon = new Balloon(name, description, manufecturer);
        DataHolder.balloonList.add(balloon);
        return Optional.of(balloon);
    }

    public void deleteById(Long id) {
        DataHolder.balloonList.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloonList.stream()
                .filter(i -> i.getId().equals(id)).findFirst();
    }
}
