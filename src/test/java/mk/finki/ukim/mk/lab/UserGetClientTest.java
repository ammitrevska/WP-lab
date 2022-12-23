package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullName;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.mk.lab.service.Implementation.UserServiceImp;
import mk.finki.ukim.mk.lab.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserGetClientTest {

    @Mock
    private UserRepositoryJpa userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("clientname");
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);

        this.service = Mockito.spy(new UserServiceImp(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void ClientNameNull(){
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> this.service.getCient("clientname"));
        Mockito.verify(this.service.getCient("clientname"));
    }
    @Test
    public void ClientNameEmpty(){
        String clientName = " ";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> this.service.getCient(clientName));
        Mockito.verify(this.service.getCient(clientName));
    }
}
