package com.pointdafamilia.pointdafamilia.order.exceptions;

public class OrderAlreadyExistException extends Exception{
    public OrderAlreadyExistException(Long id){
        super("Order with id: " + id + " already exist!");
    }
}
