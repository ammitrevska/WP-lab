package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {


    private final UserRepositoryJpa userRepositoryJpa;

    public UserServiceImp(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public User getCient(String clientName) {
        if (clientName == null || clientName.isEmpty()) {
            throw new InvalidArgumentException();
        }

        User user = new User(clientName);
        return userRepositoryJpa.save(user);
    }

}