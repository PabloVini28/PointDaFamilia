package com.pointdafamilia.pointdafamilia.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pointdafamilia.pointdafamilia.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

    
}
