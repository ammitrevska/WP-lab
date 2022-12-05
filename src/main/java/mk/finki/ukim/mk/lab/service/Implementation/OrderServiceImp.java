package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.Bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepositoryJpa;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepositoryJpa orderRepositoryJpa;

    public OrderServiceImp(OrderRepositoryJpa orderRepositoryJpa) {
        this.orderRepositoryJpa = orderRepositoryJpa;
    }


//    @Override
//    public Order placeOrder(String balloonColor) {
//        Order order = new Order(balloonColor);
//        DataHolder.orderList.add(order);
//        return order;
//    }

//    @Override
//    public Order placeOrder(String balloonColor, String clientName, String address) {
//        return null;
//    }

    @Override
    public Order placeOrder(String balloonColor){

        Order order = new Order(balloonColor);
       // orderRepositoryJpa.createOrder(order);

        return order;
    }

    @Override
    public List<Order> listAll() {
        return this.orderRepositoryJpa.findAll();
    }

    @Override
    public Order createOrder(String ballooncolor, String size) {
        Order order = new Order(ballooncolor, size);

        return orderRepositoryJpa.save(order);
    }

}
