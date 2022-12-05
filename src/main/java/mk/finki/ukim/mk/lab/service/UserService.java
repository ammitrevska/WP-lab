package mk.finki.ukim.mk.lab.service;


import mk.finki.ukim.mk.lab.model.User;

import java.util.Optional;

public interface UserService{

    User getCient(String clientName);
}
