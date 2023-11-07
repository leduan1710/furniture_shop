package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Order;
import hcmute.it.furnitureshop.Entity.User;

import java.util.Optional;

public interface OrderService {
    public <S extends Order> void save(Order order);

    public Iterable<Order> findByUser(User user);
}
