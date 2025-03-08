package com.pointdafamilia.pointdafamilia.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.food.dtos.FoodDto;
import com.pointdafamilia.pointdafamilia.food.entity.Food;
import com.pointdafamilia.pointdafamilia.food.exceptions.FoodAlreadyRegisterException;
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
}
