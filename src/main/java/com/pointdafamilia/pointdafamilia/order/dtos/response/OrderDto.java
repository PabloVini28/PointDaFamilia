package com.pointdafamilia.pointdafamilia.order.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;
import com.pointdafamilia.pointdafamilia.order.enums.OrderStatus;
import com.pointdafamilia.pointdafamilia.user.entity.User;

public record OrderDto(

    User user,
    List<OrderItem> orderItems,
    OrderStatus orderStatus,
    LocalDateTime createdAt,
    BigDecimal totalAmount
) {
    public OrderDto(Order order){
        this(
            order.getUser(),
            order.getOrderItems(),
            order.getOrderStatus(),
            order.getCreatedAt(),
            order.getTotalAmount()
        );
    }
}
