package com.pointdafamilia.pointdafamilia.order.dtos.response;

import java.util.List;
import com.pointdafamilia.pointdafamilia.order.entity.Order;

public record OrderDto(

    Long userId,
    List<OrderItemDto> orderItems

) {
    public OrderDto(Order order){
        this(
            order.getUser().getId(),
            order.getOrderItems().stream().map(OrderItemDto::new).toList()
        );
    }
}
