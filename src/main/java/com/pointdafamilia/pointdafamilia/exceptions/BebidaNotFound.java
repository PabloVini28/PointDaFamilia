package com.pointdafamilia.pointdafamilia.exceptions;

public class BebidaNotFound extends RuntimeException {
    public BebidaNotFound(Long id) {
        super("Bebida não encontrada com o id: " + id);
    }   

}
