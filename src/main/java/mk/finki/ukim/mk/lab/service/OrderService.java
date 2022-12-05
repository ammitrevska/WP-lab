package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

import java.util.List;

public interface OrderService {

    //Order placeOrder(String balloonColor, String clientName, String address);

   Order placeOrder(String balloonColor);

    List<Order> listAll();

    Order createOrder(String ballooncolor, String size);
}
