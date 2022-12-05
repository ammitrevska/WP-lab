package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "balloon_user")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String username;
   // private String name;
    @Convert(converter = UserFullnameConverter.class)
    private UserFullName userFullName;
  //  private String surname;
    private String password;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public UserFullName getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(UserFullName userFullName) {
        this.userFullName = userFullName;
    }

//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @ManyToMany
    private List<ShoppingCart> carts;

    public User(Long id, String username, String password, LocalDate dateOfBirth, List<ShoppingCart> carts) {
        this.id = id;
        this.username = username;
//        this.name = name;
//        this.surname = surname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.carts = carts;
    }


    public User() {

    }

    public User(String username) {
        this.username = username;
    }
}
