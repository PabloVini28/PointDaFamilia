package com.pointdafamilia.pointdafamilia.user.exceptions;

public class UserNameNotFoundException extends Exception {
    public UserNameNotFoundException(String username){
        super("User with username: " + username +" not found!");
    }
}
