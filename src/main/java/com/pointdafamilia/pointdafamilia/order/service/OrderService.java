package com.pointdafamilia.pointdafamilia.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.food.repository.FoodRepository;
import com.pointdafamilia.pointdafamilia.order.dtos.response.OrderDto;
import com.pointdafamilia.pointdafamilia.order.dtos.response.OrderItemDto;
import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;
import com.pointdafamilia.pointdafamilia.order.enums.OrderStatus;
import com.pointdafamilia.pointdafamilia.order.repository.OrderRepository;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;


@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(OrderDto data) {
        Order order = new Order();
        order.setUser(userRepository.findById(data.userId()).orElseThrow(() -> new RuntimeException("User not found")));
        order.setOrderStatus(OrderStatus.PENDENTE);

        List<OrderItem> orderItems = new ArrayList<>();
            for (OrderItemDto itemDto : data.orderItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setFood(foodRepository.findById(itemDto.foodId()).orElseThrow(() -> new RuntimeException("Food not found")));
                orderItem.setQuantity(itemDto.quantity());
                orderItem.setOrder(order); // Associa o OrderItem ao Order
                orderItems.add(orderItem);
            }

        order.setOrderItems(orderItems);
        order.setTotalAmount(order.calculateAmout(orderItems));

        return orderRepository.save(order); // Salva o Order e os OrderItems
    }

}
