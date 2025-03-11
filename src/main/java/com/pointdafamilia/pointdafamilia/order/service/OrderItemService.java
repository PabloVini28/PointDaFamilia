package com.pointdafamilia.pointdafamilia.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.drink.repository.DrinkRepository;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.food.repository.FoodRepository;
import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;
import com.pointdafamilia.pointdafamilia.order.repository.OrderItemRepository;
import com.pointdafamilia.pointdafamilia.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    
    private final OrderItemRepository orderItemRepository;

    private final OrderRepository orderRepository;

    private final FoodRepository foodRepository;

    private final DrinkRepository drinkRepository;

    public OrderItem createOrderItem(Long orderId, Long foodId, Long drinkId, Integer quantity) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new RuntimeException("Order not found!");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order.get());
        orderItem.setQuantity(quantity);

        if (foodId != null) {
            Optional<Food> food = foodRepository.findById(foodId);
            if (food.isEmpty()) {
                throw new RuntimeException("Food not found!");
            }
            orderItem.setFood(food.get());
        }

        if (drinkId != null) {
            Optional<Drink> drink = drinkRepository.findById(drinkId);
            if (drink.isEmpty()) {
                throw new RuntimeException("Drink not found!");
            }
            orderItem.setDrink(drink.get());
        }

        return orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Item não encontrado!"));
    }

    public List<OrderItem> getItemsByOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Transactional
    public OrderItem updateOrderItem(Long id, Integer newQuantity) {
        OrderItem orderItem = getOrderItemById(id);
        if (newQuantity == 0) {
            orderItemRepository.delete(orderItem);
            return null;
        }
        orderItem.setQuantity(newQuantity);
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item não encontrado!");
        }
        orderItemRepository.deleteById(id);
    }
}
