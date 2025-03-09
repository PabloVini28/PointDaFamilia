package com.pointdafamilia.pointdafamilia.user.exceptions;

public class NamePatchAlreadyExists extends Exception{
    public NamePatchAlreadyExists(String name){
        super("User with the name: " + name + " already exists");
    }
}
