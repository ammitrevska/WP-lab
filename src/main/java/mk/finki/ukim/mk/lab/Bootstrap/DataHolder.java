package mk.finki.ukim.mk.lab.Bootstrap;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloonList = new ArrayList<>(10);
    public static List<Order> orderList = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<User> users = new ArrayList<>();



//    @PostConstruct
//    public void init(){
//
//    Manufacturer manufacturer1 = new Manufacturer("MSI", "Italy", "Rome");
//    Manufacturer manufacturer2 = new Manufacturer("Samsung", "Spain", "Barcelona");
//    Manufacturer manufacturer3 = new Manufacturer("Apple", "Russia", "Moscow");
//    Manufacturer manufacturer4 = new Manufacturer("Lenovo", "France", "Paris");
//    Manufacturer manufacturer5 = new Manufacturer("HP", "The Netherlands", "Amsterdam");
//
//    manufacturers.add(manufacturer1);
//    manufacturers.add(manufacturer2);
//    manufacturers.add(manufacturer3);
//    manufacturers.add(manufacturer4);
//    manufacturers.add(manufacturer5);
//
//
//        balloonList.add(new Balloon("prv", "Blue Balloon", manufacturer1));
//        balloonList.add(new Balloon("vtor", "Red Balloon", manufacturer1));
//        balloonList.add(new Balloon("tret", "Violet Balloon", manufacturer2));
//        balloonList.add(new Balloon("chetvrt", "Yellow Balloon", manufacturer2));
//        balloonList.add(new Balloon("petti", "Green Balloon", manufacturer2));
//        balloonList.add(new Balloon("shesti", "Cyan Balloon", manufacturer3));
//        balloonList.add(new Balloon("sedmi", "White Balloon", manufacturer5));
//        balloonList.add(new Balloon("osmi", "Black Balloon", manufacturer4));
//        balloonList.add(new Balloon("deveti", "Brown Balloon", manufacturer5));
//        balloonList.add(new Balloon("deseti", "Orange Balloon", manufacturer4));
//
////        Order order = new Order()
//
//    }
}
