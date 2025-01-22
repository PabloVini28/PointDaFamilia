package com.pointdafamilia.pointdafamilia.exceptions;

public class ComidaAlreadyRegisteredException extends RuntimeException{
    public ComidaAlreadyRegisteredException(String comidaName){
        super("Food with name " + comidaName+ " already registered");
    }

}
