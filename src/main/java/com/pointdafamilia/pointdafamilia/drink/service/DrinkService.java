package com.pointdafamilia.pointdafamilia.drink.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkDto;
import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkPatchImage;
import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkPatchName;
import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkPatchPrice;
import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkPatchQuantity;
import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkPatchType;
import com.pointdafamilia.pointdafamilia.drink.dtos.DrinkPatchVolume;
import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.drink.exceptions.DrinkAlreadyRegisterException;
import com.pointdafamilia.pointdafamilia.drink.exceptions.DrinkNameNotFoundException;
import com.pointdafamilia.pointdafamilia.drink.exceptions.DrinkNotFoundException;
import com.pointdafamilia.pointdafamilia.drink.repository.DrinkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrinkService {
    
    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    public DrinkDto updateName(DrinkPatchName drinkPatchName, String name) throws Exception{
        if(!drinkRepository.existByName()){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setName(drinkPatchName.name());
        return modelMapper.map(drink, DrinkDto.class);
    }

    public DrinkDto updateVolume(DrinkPatchVolume drinkPatchVolume, String name) throws Exception{
        if(!drinkRepository.existByName()){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setVolumeType(drinkPatchVolume.volumeType());
        return modelMapper.map(drink, DrinkDto.class);
    }

    public DrinkDto updateDrinkType(DrinkPatchType drinkPatchType, String name) throws Exception{
        if(!drinkRepository.existByName()){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setDrinkType(drinkPatchType.drinkType());
        return modelMapper.map(drink, DrinkDto.class);
    }

    public DrinkDto updatePrice(DrinkPatchPrice drinkPatchPrice, String name) throws Exception{
        if(!drinkRepository.existByName()){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setPrice(drinkPatchPrice.price());
        return modelMapper.map(drink, DrinkDto.class);
    }

    public DrinkDto updateQuantity(DrinkPatchQuantity drinkPatchQuantity, String name) throws Exception{
        if(!drinkRepository.existByName()){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setQuantity(drinkPatchQuantity.quantity());
        return modelMapper.map(drink, DrinkDto.class);
    }

    public DrinkDto updateUrlImage(DrinkPatchImage drinkPatchImage, String name) throws Exception{
        if(!drinkRepository.existByName()){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setUrlImage(drinkPatchImage.imagePath());
        return modelMapper.map(drink, DrinkDto.class);
    }

}
