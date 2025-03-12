package com.pointdafamilia.pointdafamilia.drink.exceptions;

public class DrinkNotFoundException extends Exception{
    public DrinkNotFoundException(Long id){
        super("Drink with the id: " + id + " not found!");
    }
}
