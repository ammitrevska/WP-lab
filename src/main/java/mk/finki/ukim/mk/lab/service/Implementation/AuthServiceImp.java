package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.Exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab.model.Exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.Exceptions.UsernameExistsException;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullName;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {
    private final UserRepositoryJpa userRepositoryJpa;

    public AuthServiceImp(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }


    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        return userRepositoryJpa.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
