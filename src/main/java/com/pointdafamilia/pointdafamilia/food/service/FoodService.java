package com.pointdafamilia.pointdafamilia.food.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodDto;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodPatchDescription;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodPatchImage;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodPatchIngredients;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodPatchName;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodPatchPrice;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodPatchQuantity;
import com.pointdafamilia.pointdafamilia.food.dtos.FoodPatchType;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.food.exceptions.FoodAlreadyRegisterException;
import com.pointdafamilia.pointdafamilia.food.exceptions.FoodNameNotFoundException;
import com.pointdafamilia.pointdafamilia.food.exceptions.FoodNotFoundException;
import com.pointdafamilia.pointdafamilia.food.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {
    
    @Autowired
    private FoodRepository foodRepository;

    public Food createFood(FoodDto data) throws Exception{
        if(foodRepository.existsByName(data.name())){
            throw new FoodAlreadyRegisterException(data.name());
        }
        Food newFood = new Food(data);
        return foodRepository.save(newFood);
    }

    public List<FoodDto> getAllFoodsRegistered(){
        List<Food> foodList = foodRepository.findAll();
        return foodList.stream().map(food -> new FoodDto(food)).collect(Collectors.toList());
    }

    public Food getFoodById(Long id) throws Exception{
        if(!foodRepository.existsById(id)){
            throw new FoodNotFoundException(id);
        }
        Food returnedFood = foodRepository.findItById(id);
        return returnedFood;
    }

    public FoodDto updateFood(Long id, FoodDto data) throws Exception{
        if(!foodRepository.existsById(id)){
            throw new FoodNotFoundException(id);
        }
        
        Food updatedFood = foodRepository.findItById(id);
        updatedFood = new Food(data);
        updatedFood.setId(id);
        foodRepository.save(updatedFood);
        return data;
    }

    public void deleteFood(Long id) throws Exception{
        if(!foodRepository.existsById(id)){
            throw new FoodNotFoundException(id);
        }
        foodRepository.deleteById(id);
    }

    public FoodDto updateName(FoodPatchName foodPatchName, String name) throws Exception{
        if(!foodRepository.existsByName(name)){
            throw new FoodNameNotFoundException(name);
        }   
        Food food = foodRepository.findByName(name);
        food.setName(foodPatchName.name());
        foodRepository.save(food);
        return new FoodDto(food.getName(),food.getDescription(),food.getIngredients()
        ,food.getQuantity(),food.getPrice(),food.getUrlImage(),food.getType());
    }

    public FoodDto updateDescription(FoodPatchDescription foodPatchDescription, String name) throws Exception{
        if(!foodRepository.existsByName(name)){
            throw new FoodNameNotFoundException(name);
        }
        Food food = foodRepository.findByName(name);
        food.setDescription(foodPatchDescription.description());
        foodRepository.save(food);
        return new FoodDto(food.getName(),food.getDescription(),food.getIngredients()
        ,food.getQuantity(),food.getPrice(),food.getUrlImage(),food.getType());
    }

    public FoodDto updateIngredients(FoodPatchIngredients foodPatchIngredients, String name) throws Exception {
        
        if(!foodRepository.existsByName(name)){
            throw new FoodNameNotFoundException(name);
        }
        Food food = foodRepository.findByName(name);
        food.setIngredients(foodPatchIngredients.ingredients());
        foodRepository.save(food);
    
        return new FoodDto(food.getName(),food.getDescription(),food.getIngredients()
        ,food.getQuantity(),food.getPrice(),food.getUrlImage(),food.getType());
    }
    

    public FoodDto updatePrice(FoodPatchPrice foodPatchPrice, String name) throws Exception{
        if(!foodRepository.existsByName(name)){
            throw new FoodNameNotFoundException(name);
        }
        Food food = foodRepository.findByName(name);
        food.setPrice(foodPatchPrice.price());
        foodRepository.save(food);
        return new FoodDto(food.getName(),food.getDescription(),food.getIngredients()
        ,food.getQuantity(),food.getPrice(),food.getUrlImage(),food.getType());
    }

    public FoodDto updateQuantity(FoodPatchQuantity foodPatchQuantity, String name) throws Exception{
        if(!foodRepository.existsByName(name)){
            throw new FoodNameNotFoundException(name);
        }
        Food food = foodRepository.findByName(name);
        food.setQuantity(foodPatchQuantity.quantity());
        foodRepository.save(food);
        return new FoodDto(food.getName(),food.getDescription(),food.getIngredients()
        ,food.getQuantity(),food.getPrice(),food.getUrlImage(),food.getType());
    }

    public FoodDto updateImage(FoodPatchImage foodPatchImage, String name) throws Exception{
        if(!foodRepository.existsByName(name)){
            throw new FoodNameNotFoundException(name);
        }
        Food food = foodRepository.findByName(name);
        food.setUrlImage(foodPatchImage.imagePath());
        foodRepository.save(food);
        return new FoodDto(food.getName(),food.getDescription(),food.getIngredients()
        ,food.getQuantity(),food.getPrice(),food.getUrlImage(),food.getType());
    }

    public FoodDto updateFoodType(FoodPatchType foodPatchType, String name) throws Exception{
        if(!foodRepository.existsByName(name)){
            throw new FoodNameNotFoundException(name);
        }
        Food food = foodRepository.findByName(name);
        food.setType(foodPatchType.type());
        foodRepository.save(food);
        return new FoodDto(food.getName(),food.getDescription(),food.getIngredients()
        ,food.getQuantity(),food.getPrice(),food.getUrlImage(),food.getType());
    }


}
