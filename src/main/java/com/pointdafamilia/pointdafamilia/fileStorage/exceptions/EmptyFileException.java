package com.pointdafamilia.pointdafamilia.fileStorage.exceptions;

public class EmptyFileException extends RuntimeException{
    public EmptyFileException(){
        super();
    }
    public EmptyFileException(String message){
        super(message);
    }
}
