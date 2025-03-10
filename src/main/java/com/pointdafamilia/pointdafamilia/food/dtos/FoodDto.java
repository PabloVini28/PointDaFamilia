package com.pointdafamilia.pointdafamilia.food.dtos;

import java.util.List;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.food.enums.FoodType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record FoodDto(
    String name,
    String description,
    List<String> ingredients,
    Integer quantity,
    String price,
    String urlImage,
    @Enumerated(EnumType.STRING)FoodType type
) {
    public FoodDto(Food food){
        this(food.getName(), 
            food.getDescription(), 
            food.getIngredients(), 
            food.getQuantity(), 
            food.getPrice(), 
            food.getUrlImage(), 
            food.getType());
    }
}
