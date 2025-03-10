package com.pointdafamilia.pointdafamilia.food.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pointdafamilia.pointdafamilia.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food,Long>{

    boolean existsByName(String name);

    Food findItById(Long id);

    Food findByName(String name);

    boolean existsByIngredients(List<String> ingredients);
        
}
