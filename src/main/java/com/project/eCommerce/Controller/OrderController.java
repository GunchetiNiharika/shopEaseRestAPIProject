package com.project.eCommerce.Controller;

import com.project.eCommerce.Service.OrderService;
import com.project.eCommerce.Service.ProductService;
import com.project.eCommerce.Service.UserService;
import com.project.eCommerce.entity.Order;
import com.project.eCommerce.entity.Product;
import com.project.eCommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Order createOrder(@PathVariable Long userId, @RequestBody List<Product> products){
        return orderService.createOrder(userId, products);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId,
                                     @RequestParam(defaultValue="0") int page,
                                     @RequestParam(defaultValue="10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return orderService.getOrdersByUser(userId, pageable);

    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

}
