package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepositoryJpa extends JpaRepository<Balloon, Long> {


    void deleteByName(String name);
    List<Balloon> findAllByNameOrDescription(String name, String description);

}
