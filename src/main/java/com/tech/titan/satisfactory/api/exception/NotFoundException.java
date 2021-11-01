package com.tech.titan.satisfactory.api.exception;



public abstract class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("The requested resource was not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
