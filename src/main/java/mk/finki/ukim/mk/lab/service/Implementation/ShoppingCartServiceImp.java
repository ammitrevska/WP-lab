package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.Exceptions.OrderAlreadyInShoppingCart;
import mk.finki.ukim.mk.lab.model.Exceptions.OrderNotFoundException;
import mk.finki.ukim.mk.lab.model.Exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.mk.lab.model.Exceptions.UserNotFoundException;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    private final ShoppingCartRepositoryJpa shoppingCartRepositoryJpa;
    private final UserRepositoryJpa userRepositoryJpa;
    private final OrderRepositoryJpa orderRepositoryJpa;

    public ShoppingCartServiceImp(ShoppingCartRepositoryJpa shoppingCartRepositoryJpa, UserRepositoryJpa userRepositoryJpa, OrderRepositoryJpa orderRepositoryJpa) {
        this.shoppingCartRepositoryJpa = shoppingCartRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
        this.orderRepositoryJpa = orderRepositoryJpa;
    }


    @Override
    public List<Order> listAllOrdersInShoppingCart(Long cardId) {
        if(!this.shoppingCartRepositoryJpa.findById(cardId).isPresent())
            throw new ShoppingCartNotFoundException(cardId);
        return this.shoppingCartRepositoryJpa.findById(cardId).get().getOrder();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepositoryJpa.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException(username));

        return this.shoppingCartRepositoryJpa.findByUser(user)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepositoryJpa.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Order order = this.orderRepositoryJpa.findById(productId).orElseThrow(
                () -> new OrderNotFoundException(productId));
        if(shoppingCart.getOrder().stream().filter(i -> i.getOrderId().equals(productId)).collect(Collectors.toList())
                .size() > 0)
            throw new OrderAlreadyInShoppingCart(productId, username);
        shoppingCart.getOrder().add(order);
        return this.shoppingCartRepositoryJpa.save(shoppingCart);
    }
}
