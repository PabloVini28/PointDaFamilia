package com.pointdafamilia.pointdafamilia.food.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodDto;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.food.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/food")
public class FoodController {
    
    @Autowired
    private FoodService foodService;

    @PostMapping("/create-food")
    public ResponseEntity<Food> createFood(@RequestBody @Valid FoodDto data) throws Exception {
        Food newFood = foodService.createFood(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFood);
    }

    @GetMapping("/get-all-foods")
    public ResponseEntity<List<FoodDto>> getAllFoods() {
        List<FoodDto> foodList = foodService.getAllFoodsRegistered();
        return ResponseEntity.ok(foodList);
    }

    @GetMapping("/get-food-by-id/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable @Valid Long id) throws Exception {
        Food food = foodService.getFoodById(id);
        return ResponseEntity.ok(food);
    }
    
    @PutMapping("update-food-by-id/{id}")
    public ResponseEntity<FoodDto> updateFoodById(@PathVariable Long id, @RequestBody @Valid FoodDto data) throws Exception {
        FoodDto updatedFood = foodService.updateFood(id, data);
        return ResponseEntity.ok(updatedFood);
    }
    
    @DeleteMapping("delete-food-by-id/{id}")
    public ResponseEntity<Void> deleteFoodById(@PathVariable Long id) throws Exception {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

}
