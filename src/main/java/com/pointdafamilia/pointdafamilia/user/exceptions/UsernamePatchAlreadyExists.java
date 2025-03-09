package com.pointdafamilia.pointdafamilia.user.exceptions;

public class UsernamePatchAlreadyExists extends Exception{
    public UsernamePatchAlreadyExists(String username){
        super("The UsernamePatch: " + username + " already registered!");
    }
}
