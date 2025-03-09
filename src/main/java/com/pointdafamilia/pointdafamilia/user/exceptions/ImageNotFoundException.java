package com.pointdafamilia.pointdafamilia.user.exceptions;

public class ImageNotFoundException extends Exception{
    public ImageNotFoundException(String image){
        super("The image with path: " + image + " not found!");
    }
}
