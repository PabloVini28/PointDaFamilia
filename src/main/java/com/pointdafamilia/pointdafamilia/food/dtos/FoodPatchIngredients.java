package com.pointdafamilia.pointdafamilia.food.dtos;

import java.util.Map;

public record FoodPatchIngredients(
    Map<String,String> ingredients
) {
} 
