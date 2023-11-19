package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.DTO.OrderRequestDTO;
import hcmute.it.furnitureshop.Entity.Notification;
import hcmute.it.furnitureshop.Entity.Order;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.NotificationRepository;
import hcmute.it.furnitureshop.Repository.OrderRepository;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import hcmute.it.furnitureshop.Service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public <S extends Order> void save(User user, OrderRequestDTO orderRequestDTO,Integer productId) {
        Order order=new Order();
        Optional<Product> product=productRepository.findById(productId);
        order.setUser(user);
        order.setProduct(product.get());
        order.setState("processing");
        order.setDate(new Date());
        order.setCount(orderRequestDTO.getCount());
        order.setPaid(orderRequestDTO.getPaid());
        order.setNowDelivery(orderRequestDTO.getNowDelivery());
        ///
        Notification notification=new Notification();
        notification.setState(false);
        notification.setDescription("Đặt hàng thành công");
        notification.setUser(user);
        notification.setDate(new Date());
        List<Notification> notifications=new ArrayList<>();
        notifications.add(notification);
        //// trừ số lượng khi đặt hàng
        product.get().setQuantity(product.get().getQuantity()-orderRequestDTO.getCount());
        productRepository.save(product.get());
        //
        order.setNotification(notifications);
        orderRepository.save(order);
        notification.setOrder(order);
        notificationRepository.save(notification);
    }

    @Override
    public void CancelOrder(Integer orderId,User user) {
        Optional<Order> order=orderRepository.findById(orderId);
        order.get().setState("canceled");
        ///
        Notification notification=new Notification();
        notification.setState(false);
        notification.setDescription("Huỷ đơn hàng thành công");
        notification.setUser(user);
        notification.setDate(new Date());
        //
        List<Notification> notifications=order.get().getNotification();
        notifications.add(notification);
        order.get().setNotification(notifications);
        //
        orderRepository.save(order.get());
        notification.setOrder(order.get());
        notificationRepository.save(notification);
        //// cộng số lượng khi huỷ
        Product product= order.get().getProduct();
        product.setQuantity(product.getQuantity()+order.get().getCount());
        productRepository.save(product);
    }

    @Override
    public void RestoreOrder(Integer orderId, User user) {
        Optional<Order> order=orderRepository.findById(orderId);
        order.get().setState("processing");
        order.get().setDate(new Date());
        ///
        Notification notification=new Notification();
        notification.setState(false);
        notification.setDescription("Đặt lại đơn hàng thành công");
        notification.setUser(user);
        notification.setDate(new Date());
        //
        List<Notification> notifications=order.get().getNotification();
        notifications.add(notification);
        order.get().setNotification(notifications);
        //
        orderRepository.save(order.get());
        notification.setOrder(order.get());
        notificationRepository.save(notification);
        //// trừ số lượng khi đặt lại
        Product product= order.get().getProduct();
        product.setQuantity(product.getQuantity()-order.get().getCount());
        productRepository.save(product);
    }

    @Override
    public Iterable<Order> findByUser(User user) {
        return orderRepository.findOrdersByUserOrderByDateDesc(user);
    }

    @Override
    public Optional<Order> findById(Integer orderId) {
        return orderRepository.findById(orderId);
    }


}
