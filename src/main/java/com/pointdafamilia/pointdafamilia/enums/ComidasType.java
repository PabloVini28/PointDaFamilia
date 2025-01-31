package com.pointdafamilia.pointdafamilia.enums;

public enum ComidasType {
    SALGADOS("Salgados"),
    PIZZAS("Pizzas"),
    SANDUICHES("Sanduíches"),
    BATATAS("Batatas");

    private final String displayName;

    //Constructor para armazenar o nome legivel
    ComidasType(String displayName) {
        this.displayName = displayName;
    }

    //Getter para retornar o nome legivel
    public String getDisplayName() {
        return displayName;
    }
}
