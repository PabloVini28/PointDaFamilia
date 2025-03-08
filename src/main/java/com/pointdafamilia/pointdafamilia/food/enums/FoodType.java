package com.pointdafamilia.pointdafamilia.food.enums;

public enum FoodType {

    SALGADOS("Salgados"),
    PIZZAS("Pizzas"),
    SANDUICHES("Sanduíches"),
    BATATAS("Batatas");

    private final String displayName;

    FoodType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
