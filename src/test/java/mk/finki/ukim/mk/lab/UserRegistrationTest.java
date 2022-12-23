package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.Exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.mk.lab.model.Exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.Exceptions.UsernameExistsException;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullName;
import mk.finki.ukim.mk.lab.repository.impl.UserRepository;
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

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepositoryJpa userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService service;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        UserFullName userFullName = new UserFullName("name", "surname");
        User user = new User("username", "password", userFullName , Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");


        this.service = Mockito.spy(new UserServiceImp(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister() {
        UserFullName userFullName = new UserFullName("name", "surname");

        User user = this.service.register("username", "password", "password", userFullName, Role.ROLE_USER);

        Mockito.verify(this.service).register("username", "password", "password", userFullName, Role.ROLE_USER);


        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("name do not mach", "name", userFullName.getName());
        Assert.assertEquals("role do not mach", Role.ROLE_USER, user.getRole());
        Assert.assertEquals("surname do not mach", "surname", userFullName.getSurname());
        Assert.assertEquals("password do not mach", "password", user.getPassword());
        Assert.assertEquals("username do not mach", "username", user.getUsername());
    }


    @Test
    public void testNullUsername() {
        UserFullName userFullName = new UserFullName("name", "surname");
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> this.service.register(null, "password", "password", userFullName, Role.ROLE_USER));
        Mockito.verify(this.service).register(null, "password", "password", userFullName, Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        UserFullName userFullName = new UserFullName("name", "surname");
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> this.service.register(username, "password", "password", userFullName, Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", userFullName, Role.ROLE_USER);
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        UserFullName userFullName = new UserFullName("name", "surname");
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> this.service.register(username, password, "password", userFullName, Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password", userFullName, Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        UserFullName userFullName = new UserFullName("name", "surname");
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> this.service.register(username, password, "password", userFullName, Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password", userFullName, Role.ROLE_USER);
    }


    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        UserFullName userFullName = new UserFullName("name", "surname");

        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username, password, confirmPassword, userFullName, Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, confirmPassword, userFullName, Role.ROLE_USER);
    }


    @Test
    public void testDuplicateUsername() {
        UserFullName userFullName = new UserFullName("name", "surname");

        User user = new User("username", "password",userFullName, Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameExistsException.class,
                () -> this.service.register(username, "password", "password", userFullName, Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", userFullName, Role.ROLE_USER);
    }

}
