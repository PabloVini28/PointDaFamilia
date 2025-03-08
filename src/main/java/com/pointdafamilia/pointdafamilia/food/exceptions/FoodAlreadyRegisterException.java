package com.pointdafamilia.pointdafamilia.food.exceptions;

public class FoodAlreadyRegisterException extends Exception {
    public FoodAlreadyRegisterException(String foodName) {
        super(String.format("Food with name %s already registered in the system.", foodName));
    }
    
}
