package com.pointdafamilia.pointdafamilia.order.dtos.response;

import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;

public record OrderItemDto(
    Long orderId,
    Long foodId,
    String foodName,
    Long drinkId,
    String drinkName,
    Integer quantity
) {
    public OrderItemDto(OrderItem orderItem){
        this(
            orderItem.getOrder().getId(),
            orderItem.getFood() != null ? orderItem.getFood().getId() : null,
            orderItem.getFood() != null ? orderItem.getFood().getName() : null,
            orderItem.getDrink() != null ? orderItem.getDrink().getId() : null,
            orderItem.getDrink() != null ? orderItem.getDrink().getName() : null,
            orderItem.getQuantity()
        );
    }
}
