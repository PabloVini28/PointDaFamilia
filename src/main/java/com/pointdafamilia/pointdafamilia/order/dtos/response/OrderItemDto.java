package com.pointdafamilia.pointdafamilia.order.dtos.response;

import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;

public record OrderItemDto(
    Long orderId,
    Long foodId,
    Long drinkId,
    Integer quantity
) {
    public OrderItemDto(OrderItem orderItem){
        this(
            orderItem.getOrder().getId(),
            orderItem.getFood().getId(),
            orderItem.getDrink().getId(),
            orderItem.getQuantity()
        );
    }
}
