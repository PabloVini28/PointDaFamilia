package com.pointdafamilia.pointdafamilia.exceptions;

public class BebidaAlreadyRegisteredException extends RuntimeException{
    public BebidaAlreadyRegisteredException(String BebidaName){
        super("Drink with name " + BebidaName + " already registered");
    }

}
