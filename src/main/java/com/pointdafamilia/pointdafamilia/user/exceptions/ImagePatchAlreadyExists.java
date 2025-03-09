package com.pointdafamilia.pointdafamilia.user.exceptions;

public class ImagePatchAlreadyExists extends Exception{
    public ImagePatchAlreadyExists(String image){
        super("The image with path " + image + " already exists!");
    }
}
