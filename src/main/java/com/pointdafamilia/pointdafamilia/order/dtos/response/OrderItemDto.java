package com.pointdafamilia.pointdafamilia.order.dtos.response;

import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;

public record OrderItemDto(
    Order order,
    Food food,
    Drink drink,
    Integer quantity
) {
    OrderItemDto(OrderItem orderItem){
        this(
            orderItem.getOrder(),
            orderItem.getFood(),
            orderItem.getDrink(),
            orderItem.getQuantity()
        );
    }
}
