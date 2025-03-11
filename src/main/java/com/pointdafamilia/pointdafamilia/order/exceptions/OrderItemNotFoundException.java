package com.pointdafamilia.pointdafamilia.order.exceptions;

public class OrderItemNotFoundException extends Exception{
    public OrderItemNotFoundException(Long id){
        super("OrderItem: " + id + " not found");
    }
}
