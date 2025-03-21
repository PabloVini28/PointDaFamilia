package com.pointdafamilia.pointdafamilia.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pointdafamilia.pointdafamilia.order.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

    List<OrderItem> findByOrderId(Long orderId);

}
