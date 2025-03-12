package com.pointdafamilia.pointdafamilia.user.exceptions;

public class UserIdNotFoundException extends Exception{
    public UserIdNotFoundException(Long id){
        super("User with id: " + id + " not found!");
    }
}
