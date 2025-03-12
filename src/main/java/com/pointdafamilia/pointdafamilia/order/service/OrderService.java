package com.pointdafamilia.pointdafamilia.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.order.dtos.response.OrderDto;
import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.repository.OrderRepository;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;


@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(OrderDto data) {

        User user = userRepository.findById(data.userId())
            .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        if (data.orderItems().isEmpty()) {
            throw new IllegalArgumentException("The order must have at least one item!");
        } 

        Order order = new Order(data);
        order.setUser(user);
        return orderRepository.save(order);
    }

}
