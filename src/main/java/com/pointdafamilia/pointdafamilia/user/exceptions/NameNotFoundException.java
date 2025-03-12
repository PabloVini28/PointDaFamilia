package com.pointdafamilia.pointdafamilia.user.exceptions;

public class NameNotFoundException extends Exception{
    public NameNotFoundException(String name){
        super("User with name: " + name + " not found!");
    }
}
