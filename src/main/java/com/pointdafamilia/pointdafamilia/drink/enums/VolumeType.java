package com.pointdafamilia.pointdafamilia.drink.enums;

public enum VolumeType {
    GARRAFA_1L("Garrafas_1L"),
    GARRAFA_2L("Garrafas_2L"),
    GARRAFA_LITROEMEIO("Garrafas_1.5L"),
    LATA("Latas");

    private final String displayName;

    VolumeType(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return this.displayName;
    }
}
