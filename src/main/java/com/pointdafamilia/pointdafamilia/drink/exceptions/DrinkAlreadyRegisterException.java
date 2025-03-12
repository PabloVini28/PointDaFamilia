package com.pointdafamilia.pointdafamilia.drink.exceptions;

public class DrinkAlreadyRegisterException extends Exception{
    public DrinkAlreadyRegisterException(String drinkName){
        super("Drink with name: " + drinkName + " already registered in the system!");
    }
}
