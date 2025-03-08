package com.pointdafamilia.pointdafamilia.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pointdafamilia.pointdafamilia.food.entity.Food;

public interface FoodRepository extends JpaRepository<Long,Food>{
        
}
