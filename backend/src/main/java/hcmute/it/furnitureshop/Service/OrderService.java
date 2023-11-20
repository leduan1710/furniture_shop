package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.DTO.OrderRequestDTO;
import hcmute.it.furnitureshop.Entity.Order;
import hcmute.it.furnitureshop.Entity.User;

import java.util.Optional;

public interface OrderService {
    public <S extends Order> void save(User user, OrderRequestDTO orderRequestDTO, Integer productId);
    public void CancelOrder(Integer orderId,User user);
    Order UpdateOrder(Integer orderId);

    public void RestoreOrder(Integer orderId, User user);
    public Iterable<Order> findByUser(User user);
    public Optional<Order> findById(Integer orderId);
}
