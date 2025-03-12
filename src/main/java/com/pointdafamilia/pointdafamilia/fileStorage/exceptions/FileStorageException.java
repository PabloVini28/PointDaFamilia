package com.pointdafamilia.pointdafamilia.fileStorage.exceptions;

import java.io.IOException;

public class FileStorageException extends RuntimeException{
    public FileStorageException(String message){
        super(message);
    }

    public FileStorageException(String message, IOException e){
        super(message,e);
    }
}
