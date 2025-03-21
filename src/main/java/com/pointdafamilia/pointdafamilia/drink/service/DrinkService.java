package com.pointdafamilia.pointdafamilia.drink.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchImage;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchName;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchPrice;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchQuantity;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchType;
import com.pointdafamilia.pointdafamilia.drink.dtos.request.DrinkPatchVolume;
import com.pointdafamilia.pointdafamilia.drink.dtos.response.DrinkDto;
import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.drink.exceptions.DrinkAlreadyRegisterException;
import com.pointdafamilia.pointdafamilia.drink.exceptions.DrinkNameNotFoundException;
import com.pointdafamilia.pointdafamilia.drink.exceptions.DrinkNotFoundException;
import com.pointdafamilia.pointdafamilia.drink.repository.DrinkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrinkService {
    
    @Autowired
    private DrinkRepository drinkRepository;

    public Drink createDrink(DrinkDto data) throws Exception{
        if(drinkRepository.existsByName(data.name())){
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
        Drink updatedDrink = new Drink(data);
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

    @Transactional
    public DrinkDto updateName(DrinkPatchName drinkPatchName, String name) throws Exception{
        if(!drinkRepository.existsByName(name)){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setName(drinkPatchName.name()); 
        drink = drinkRepository.save(drink);  
        return new DrinkDto(drink.getName(), drink.getPrice(), drink.getQuantity(), drink.getUrlImage()
        ,drink.getVolumeType(),drink.getDrinkType());
    }

    public DrinkDto updateVolume(DrinkPatchVolume drinkPatchVolume, String name) throws Exception{
        if(!drinkRepository.existsByName(name)){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setVolumeType(drinkPatchVolume.volumeType());
        drink = drinkRepository.save(drink);
        return new DrinkDto(drink.getName(), drink.getPrice(), drink.getQuantity(), drink.getUrlImage()
        ,drink.getVolumeType(),drink.getDrinkType());
    }

    public DrinkDto updateDrinkType(DrinkPatchType drinkPatchType, String name) throws Exception{
        if(!drinkRepository.existsByName(name)){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setDrinkType(drinkPatchType.drinkType());
        drink = drinkRepository.save(drink);
        return new DrinkDto(drink.getName(), drink.getPrice(), drink.getQuantity(), drink.getUrlImage()
        ,drink.getVolumeType(),drink.getDrinkType());
    }

    public DrinkDto updatePrice(DrinkPatchPrice drinkPatchPrice, String name) throws Exception{
        if(!drinkRepository.existsByName(name)){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setPrice(drinkPatchPrice.price());
        drink = drinkRepository.save(drink);
        return new DrinkDto(drink.getName(), drink.getPrice(), drink.getQuantity(), drink.getUrlImage()
        ,drink.getVolumeType(),drink.getDrinkType());
    }

    public DrinkDto updateQuantity(DrinkPatchQuantity drinkPatchQuantity, String name) throws Exception{
        if(!drinkRepository.existsByName(name)){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setQuantity(drinkPatchQuantity.quantity());
        drink = drinkRepository.save(drink);
        return new DrinkDto(drink.getName(), drink.getPrice(), drink.getQuantity(), drink.getUrlImage()
        ,drink.getVolumeType(),drink.getDrinkType());
    }

    public DrinkDto updateUrlImage(DrinkPatchImage drinkPatchImage, String name) throws Exception{
        if(!drinkRepository.existsByName(name)){
            throw new DrinkNameNotFoundException(name);
        }
        Drink drink = drinkRepository.findByName(name);
        drink.setUrlImage(drinkPatchImage.imagePath());
        drink = drinkRepository.save(drink);
        return new DrinkDto(drink.getName(), drink.getPrice(), drink.getQuantity(), drink.getUrlImage()
        ,drink.getVolumeType(),drink.getDrinkType());
    }

}
