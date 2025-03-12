package com.pointdafamilia.pointdafamilia.fileStorage.exceptions;


public class FileStorageException extends RuntimeException{
    public FileStorageException(String message){
        super(message);
    }

    public FileStorageException(String string, RuntimeException e) {
        super(string,e);
    }

    public FileStorageException(String string, Exception e) {
        super(string,e);
    }
}
