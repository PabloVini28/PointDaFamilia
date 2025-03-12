package com.pointdafamilia.pointdafamilia.drink.exceptions;

public class DrinkNameNotFoundException extends Exception{
    public DrinkNameNotFoundException(String name){
        super("Drink with name: " + name + " not found!");
    }
}
