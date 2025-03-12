package com.pointdafamilia.pointdafamilia.food.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pointdafamilia.pointdafamilia.food.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long>{

    boolean existsByName(String name);

    Food findItById(Long id);

    Food findByName(String name);

    boolean existsByIngredients(List<String> ingredients);
        
}
