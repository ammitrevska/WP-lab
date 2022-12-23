package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.Exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.Exceptions.UsernameExistsException;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullName;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {


    private final UserRepositoryJpa userRepositoryJpa;

    public UserServiceImp(UserRepositoryJpa userRepositoryJpa, PasswordEncoder passwordEncoder) {
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



    @Override
    public User register(String username, String password, String repeatPassword, UserFullName fullname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepositoryJpa.findByUsername(username).isPresent() || !this.userRepositoryJpa.findByUsername(username).isEmpty()){
            throw new UsernameExistsException(username);
        }


        User user = new User(username, password,fullname, role);
        return userRepositoryJpa.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositoryJpa.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));

    }
}