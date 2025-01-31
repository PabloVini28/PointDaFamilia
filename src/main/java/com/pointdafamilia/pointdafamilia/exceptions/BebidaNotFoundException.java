package com.pointdafamilia.pointdafamilia.exceptions;

public class BebidaNotFoundException extends RuntimeException {
    public BebidaNotFoundException(Long id) {
        super("Drink is not found with id: " + id);
    }   

}
