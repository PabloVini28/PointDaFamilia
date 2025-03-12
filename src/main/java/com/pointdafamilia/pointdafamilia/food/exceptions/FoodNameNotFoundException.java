package com.pointdafamilia.pointdafamilia.food.exceptions;

public class FoodNameNotFoundException extends Exception{
    public FoodNameNotFoundException(String name){
        super("Food with name: " + name + " not found!");
    }
}
