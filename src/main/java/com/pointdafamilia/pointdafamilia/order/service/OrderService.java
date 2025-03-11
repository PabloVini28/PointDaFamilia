package com.pointdafamilia.pointdafamilia.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pointdafamilia.pointdafamilia.order.dtos.response.OrderDto;
import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.enums.OrderStatus;
import com.pointdafamilia.pointdafamilia.order.repository.OrderRepository;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;


@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Order createOrder(OrderDto data) {
        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        if (data.orderItems().isEmpty()) {
            throw new IllegalArgumentException("The order must have at least one item!");
        } 

        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDENTE);

        data.orderItems().forEach(item -> item.setOrder(order));
        
        order.setOrderItems(data.orderItems());

        BigDecimal valor = new BigDecimal(10);

        order.setTotalAmount(valor);

        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }

    public List<OrderDto> getAllOrders(){
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(order -> new OrderDto(order)).collect(Collectors.toList());
    }
}
