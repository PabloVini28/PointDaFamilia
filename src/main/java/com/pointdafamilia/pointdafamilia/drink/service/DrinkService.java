package com.pointdafamilia.pointdafamilia.drink.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkDto;
import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.drink.exceptions.DrinkAlreadyRegisterException;
import com.pointdafamilia.pointdafamilia.drink.exceptions.DrinkNotFoundException;
import com.pointdafamilia.pointdafamilia.drink.repository.DrinkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrinkService {
    
    @Autowired
    private DrinkRepository drinkRepository;

    public Drink createDrink(DrinkDto data) throws Exception{
        if(drinkRepository.existByName()){
            throw new DrinkAlreadyRegisterException(data.name());
        }
        Drink newDrink = new Drink(data);
        return drinkRepository.save(newDrink);
    }

    public List<DrinkDto> getAllDrink(){
        List<Drink> DrinkList = drinkRepository.findAll();
        return DrinkList.stream().map(drink -> new DrinkDto(drink)).collect(Collectors.toList());
    }

    public Drink getDrinkById(Long id) throws Exception{
        if(!drinkRepository.existsById(id)){
            throw new DrinkNotFoundException(id);
        }
        Drink registeredDrink = drinkRepository.findItById(id);
        return registeredDrink;
    }

    public DrinkDto updateDrink(Long id, DrinkDto data) throws Exception{
        if(!drinkRepository.existsById(id)){
            throw new DrinkNotFoundException(id);
        }
        Drink updatedDrink = drinkRepository.findItById(id);
        updatedDrink = new Drink(data);
        updatedDrink.setId(id);
        drinkRepository.save(updatedDrink);
        return data;
    }

    public void deleteDrink(Long id) throws Exception{
        if(!drinkRepository.existsById(id)){
            throw new DrinkNotFoundException(id);
        }
        drinkRepository.deleteById(id);
    }

}
