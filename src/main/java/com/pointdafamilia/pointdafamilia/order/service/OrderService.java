package com.pointdafamilia.pointdafamilia.order.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;
import com.pointdafamilia.pointdafamilia.order.enums.OrderStatus;
import com.pointdafamilia.pointdafamilia.order.repository.OrderRepository;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order createOrder(Long userId, List<OrderItem> orderItems) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException("The order must have at least one item!");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDENTE);

        orderItems.forEach(item -> item.setOrder(order));
        order.setOrderItems(orderItems);

        BigDecimal totalAmount = orderItems.stream()
        .map(item -> {
            String priceStr = item.getFood() != null ? item.getFood().getPrice() : item.getDrink().getPrice();
            return new BigDecimal(priceStr); // Converte String para BigDecimal
        })
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    

        order.setTotalAmount(totalAmount);

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
}
