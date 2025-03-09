package com.pointdafamilia.pointdafamilia.food.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pointdafamilia.pointdafamilia.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food,Long>{

    boolean existsByName(String name);

    Food findItById(Long id);

    Food findByName(String name);

    boolean existsByIngredients(Map<String,String> ingredients);

    Food findByIngredients();
        
}
