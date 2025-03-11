package com.pointdafamilia.pointdafamilia.order.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pointdafamilia.pointdafamilia.order.dtos.response.OrderItemDto;
import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;
import com.pointdafamilia.pointdafamilia.order.service.OrderItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/order-item")
public class OrderItemController {
    
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/create-order-item")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDto data) {
        OrderItem newOrderItem = orderItemService.createOrderItem(data.orderId()
        , data.foodId(), data.drinkId(), data.quantity());
        return ResponseEntity.ok().body(newOrderItem);
    }

    @GetMapping("/get-order-item-by-id")
    public ResponseEntity<OrderItem> getOrderItemById(Long id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok().body(orderItem);
    }

    @GetMapping("/get-order-items")
    public ResponseEntity<List<OrderItem>> getItemsByOrder(Long id) {
        List<OrderItem> orderItemList = orderItemService.getItemsByOrder(id);
        return ResponseEntity.ok(orderItemList);
    }
    
    
    

}
