package com.pointdafamilia.pointdafamilia.exceptions;

public class ComidaNotFoundException extends RuntimeException{
    public ComidaNotFoundException(Long id){
        super("Food with id " + id + " not found.");
    }
}
