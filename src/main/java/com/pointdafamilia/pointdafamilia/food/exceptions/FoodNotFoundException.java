package com.pointdafamilia.pointdafamilia.food.exceptions;

public class FoodNotFoundException extends Exception{
    public FoodNotFoundException(Long id){
        super("Food with id " + id + " not found");
    }
}
