package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Order;

public interface OrderService {
    public <S extends Order> void save(Order order);
}
