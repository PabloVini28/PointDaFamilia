package com.pointdafamilia.pointdafamilia.auth.exceptions;

public class UserEmailOrUsernameNotFoundException extends Exception{
    public UserEmailOrUsernameNotFoundException(String data){
        super("User with username or email like: " + data + " does not exist!");
    }
}
