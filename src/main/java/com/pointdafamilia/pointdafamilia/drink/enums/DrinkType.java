package com.pointdafamilia.pointdafamilia.drink.enums;

public enum DrinkType {
    REFRI("Refrigerantes"),
    SUCOS("Sucos"),
    ALCOOLICA("Alcoolicas");

    private final String displayName;

    DrinkType(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return this.displayName;
    }
}
