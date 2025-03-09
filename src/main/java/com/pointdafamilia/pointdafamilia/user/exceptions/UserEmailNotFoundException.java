package com.pointdafamilia.pointdafamilia.user.exceptions;

public class UserEmailNotFoundException extends Exception{
    public UserEmailNotFoundException(String email){
        super("User with email: " + email + " not found!");
    }
}
