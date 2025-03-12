package com.pointdafamilia.pointdafamilia.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pointdafamilia.pointdafamilia.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

    List<Order> findByUserId(Long userId);

    
}
