package com.pointdafamilia.pointdafamilia.order.dtos.response;

import java.util.List;
import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;

public record OrderDto(

    Long userId,
    List<OrderItem> orderItems

) {
    public OrderDto(Order order){
        this(
            order.getUser().getId(),
            order.getOrderItems()
        );
    }
}
