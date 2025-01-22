package com.pointdafamilia.pointdafamilia.exceptions;

public class ComidaAlreadyOnDatabankException extends RuntimeException{
    public ComidaAlreadyOnDatabankException(String comidaName){
        super("Food with name " + comidaName+ " already registered");
    }

}
