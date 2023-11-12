package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Order;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.OrderRepository;
import hcmute.it.furnitureshop.Service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public <S extends Order> void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Iterable<Order> findByUser(User user) {
        return orderRepository.findOrdersByUser(user);
    }


}
