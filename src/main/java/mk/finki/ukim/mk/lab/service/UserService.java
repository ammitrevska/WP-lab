package mk.finki.ukim.mk.lab.service;


import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullName;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User getCient(String clientName);
    User register(String username, String password, String repeatPassword, UserFullName userFullName, Role role);
}
