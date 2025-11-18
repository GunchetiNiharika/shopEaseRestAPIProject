package com.project.eCommerce.Service;

import com.project.eCommerce.entity.Order;
import com.project.eCommerce.entity.User;
import com.project.eCommerce.entity.Product;
import com.project.eCommerce.repository.OrderRepository;
import com.project.eCommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(Long userId, List<Product> products) {
        User user = userRepository.findById(userId).orElseThrow();
        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId, Pageable pageable){
        return orderRepository.findByUserId(userId,pageable);

    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId){
        return orderRepository.findById(orderId).orElseThrow();
    }
}
