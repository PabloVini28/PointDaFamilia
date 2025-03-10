package com.pointdafamilia.pointdafamilia.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
    
}
