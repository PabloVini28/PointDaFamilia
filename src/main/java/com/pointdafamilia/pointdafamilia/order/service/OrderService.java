package com.pointdafamilia.pointdafamilia.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

}
