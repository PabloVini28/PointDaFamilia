package com.pointdafamilia.pointdafamilia.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pointdafamilia.pointdafamilia.order.dtos.response.OrderDto;
import com.pointdafamilia.pointdafamilia.order.entity.Order;
import com.pointdafamilia.pointdafamilia.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto data) {
        Order newOrder = orderService.createOrder(data);
        return ResponseEntity.ok().body(newOrder);
    }
    

}
