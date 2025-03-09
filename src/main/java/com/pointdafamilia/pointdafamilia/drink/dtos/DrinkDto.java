package com.pointdafamilia.pointdafamilia.drink.dtos;

import com.pointdafamilia.pointdafamilia.drink.entity.Drink;
import com.pointdafamilia.pointdafamilia.drink.enums.DrinkType;
import com.pointdafamilia.pointdafamilia.drink.enums.VolumeType;


public record DrinkDto(
    String name,
    String price,
    Integer quantity,
    String urlImage,
    VolumeType volumeType,
    DrinkType drinkType
) {
    public DrinkDto(Drink drink){
        this(drink.getName(),
            drink.getPrice(),
            drink.getQuantity(),
            drink.getUrlImage(),
            drink.getVolumeType(),
            drink.getDrinkType());
    }
}
