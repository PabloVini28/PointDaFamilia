package com.pointdafamilia.pointdafamilia.food.exceptions;

public class FoodNotFoundException extends Exception{
    public FoodNotFoundException(String foodName){
        super("Food with name " + foodName + " not found");
    }
}
