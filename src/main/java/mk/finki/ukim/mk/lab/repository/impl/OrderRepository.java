package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.Bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

        public List<Order> findAll(){
            return DataHolder.orderList;
        }
        public Order createOrder(Order order){
            DataHolder.orderList.add(order);
            return order;
        }

    public Optional<List> findAllByDate(LocalDateTime from, LocalDateTime to) {
        return null;
    }
}

