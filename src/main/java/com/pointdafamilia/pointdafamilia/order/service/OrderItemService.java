package com.pointdafamilia.pointdafamilia.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    
    @Autowired
    private OrderItemRepository orderItemRepository;

}
