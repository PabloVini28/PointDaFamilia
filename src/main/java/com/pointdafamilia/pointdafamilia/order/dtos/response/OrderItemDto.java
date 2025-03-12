package com.pointdafamilia.pointdafamilia.order.dtos.response;

import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;

public record OrderItemDto(
    Long foodId,
    Long drinkId,
    Integer quantity
) {
    public OrderItemDto(OrderItem orderItem){
        this(
            orderItem.getFood().getId(),
            orderItem.getDrink().getId(),
            orderItem.getQuantity()
        );
    }
}
