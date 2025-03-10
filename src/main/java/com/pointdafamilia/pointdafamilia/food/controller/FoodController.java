package com.pointdafamilia.pointdafamilia.food.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointdafamilia.pointdafamilia.food.dtos.request.FoodPatchDescription;
import com.pointdafamilia.pointdafamilia.food.dtos.request.FoodPatchImage;
import com.pointdafamilia.pointdafamilia.food.dtos.request.FoodPatchIngredients;
import com.pointdafamilia.pointdafamilia.food.dtos.request.FoodPatchName;
import com.pointdafamilia.pointdafamilia.food.dtos.request.FoodPatchPrice;
import com.pointdafamilia.pointdafamilia.food.dtos.request.FoodPatchQuantity;
import com.pointdafamilia.pointdafamilia.food.dtos.request.FoodPatchType;
import com.pointdafamilia.pointdafamilia.food.dtos.response.FoodDto;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.food.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/food")
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
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) throws Exception {
        Food food = foodService.getFoodById(id);
        return ResponseEntity.ok(food);
    }
    
    @PutMapping("/update-food-by-id/{id}")
    public ResponseEntity<FoodDto> updateFoodById(@PathVariable Long id, @RequestBody @Valid FoodDto data) throws Exception {
        FoodDto updatedFood = foodService.updateFood(id, data);
        return ResponseEntity.ok(updatedFood);
    }
    
    @DeleteMapping("/delete-food-by-id/{id}")
    public ResponseEntity<Void> deleteFoodById(@PathVariable Long id) throws Exception {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{name}/name")
    public ResponseEntity<FoodDto> updateFoodName(@RequestBody FoodPatchName foodPatchName, @PathVariable String name) throws Exception{
        FoodDto food = foodService.updateName(foodPatchName, name);
        return ResponseEntity.ok().body(food);
    }

    @PatchMapping("/{name}/description")
    public ResponseEntity<FoodDto> updateDescription(@RequestBody FoodPatchDescription foodPatchDescription, @PathVariable String name) throws Exception{
        FoodDto food = foodService.updateDescription(foodPatchDescription, name);
        return ResponseEntity.ok().body(food);
    }


    @PatchMapping("/{name}/ingredients")
    public ResponseEntity<FoodDto> updateFoodIngredients(
            @RequestBody FoodPatchIngredients foodPatchIngredients, 
            @PathVariable String name) throws Exception {
        
        FoodDto food = foodService.updateIngredients(foodPatchIngredients, name);
        return ResponseEntity.ok().body(food);
    }

    @PatchMapping("/{name}/quantity")
    public ResponseEntity<FoodDto> updateFoodQuantity(
            @RequestBody FoodPatchQuantity foodPatchQuantity, 
            @PathVariable String name) throws Exception {
        
        FoodDto food = foodService.updateQuantity(foodPatchQuantity, name);
        return ResponseEntity.ok().body(food);
    }

    @PatchMapping("/{name}/price")
    public ResponseEntity<FoodDto> updateFoodPrice(
            @RequestBody FoodPatchPrice foodPatchPrice, 
            @PathVariable String name) throws Exception {
        
        FoodDto food = foodService.updatePrice(foodPatchPrice, name);
        return ResponseEntity.ok().body(food);
    }

    @PatchMapping("/{name}/image")
    public ResponseEntity<FoodDto> updateFoodImage(
            @RequestBody FoodPatchImage foodPatchImage, 
            @PathVariable String name) throws Exception {
        
        FoodDto food = foodService.updateImage(foodPatchImage, name);
        return ResponseEntity.ok().body(food);
    }

    @PatchMapping("/{name}/type")
    public ResponseEntity<FoodDto> updateFoodType(
            @RequestBody FoodPatchType foodPatchType, 
            @PathVariable String name) throws Exception {
        
        FoodDto food = foodService.updateFoodType(foodPatchType, name);
        return ResponseEntity.ok().body(food);
    }

}
