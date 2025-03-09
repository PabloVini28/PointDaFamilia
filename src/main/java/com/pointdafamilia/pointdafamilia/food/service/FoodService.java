package com.pointdafamilia.pointdafamilia.food.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.food.dtos.FoodDto;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.food.exceptions.FoodAlreadyRegisterException;
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
}
